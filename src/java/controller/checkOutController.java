package controller;

import dal.BatchProductDAO;
import dal.OrdersDAO;
import dal.checkOutDAO;
import feature.sendEmail.Email;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import model.OrderDetail;
import model.Users;
import util.Validate;

public class checkOutController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checkOutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkOutController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        //xử lý cập nhật paymentStatus khi khách hàng thanh toán qua VnPay
//        HttpSession session = request.getSession();
//        //Lấy về userID từ account trong sesion khi đăng nhập
//        Users account = (Users) session.getAttribute("account");
//        int orderConfirm = Integer.parseInt((String) session.getAttribute("orderConfirm"));
//
//        OrdersDAO odao = new OrdersDAO();
//        if ("00".equals(request.getParameter("isPaySuccess"))) {
//            //Xử lý/Cập nhật tình trạng giao dịch thanh toán "Thành công"
//            odao.updatePaymentStatusById(orderConfirm, "Paid");
//        }
//        // Xóa attribute "orderConfirm" khỏi session
//        session.removeAttribute("orderConfirm");
        //chuyển hướng trang
        request.getRequestDispatcher("/customer/OrderHistory").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        switch (action) {
            case "listCart":
                handleListCart(request, response);
                break;
            case "updateCartInfo":
                handleUpdateInfo(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void handleListCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkOutDAO dao = new checkOutDAO();
        HttpSession session = request.getSession();
        BatchProductDAO bpdao = new BatchProductDAO();
        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");
        int orderId = (int) session.getAttribute("giohangOrderId");
        ArrayList<OrderDetail> listCarts = dao.listAllCart(account.getUserId(), orderId);//tạm thời để orderId là 11 để test

        if (!listCarts.isEmpty()) {
            // Lấy shippingFee từ một sản phẩm trong listCart (giá trị shippingFee giống nhau cho toàn đơn hàng)
            double shippingFee = listCarts.get(0).getShippingFee();
            // Set giá trị shippingFee dạng tiền tệ (chuỗi định dạng tiền)
            String shippingFeeString = Validate.doubleToMoney(shippingFee);
            request.setAttribute("shippingFee", shippingFeeString);
        }
        for (OrderDetail sp : listCarts) {
            sp.setUnitPriceOutString(Validate.doubleToMoney(sp.getUnitPriceOut()));
            sp.setDiscountString(Validate.doubleToMoney(sp.getDiscount()));
            if (sp.getQuantity() > sp.getBatchQuantity() || sp.getStatus() == "Out of Stock") {
                bpdao.deleteOrderDetail(orderId);
            }
        }
        ArrayList<OrderDetail> listCart = dao.listAllCart(account.getUserId(), orderId);;
        for (OrderDetail sp : listCart) {
            sp.setUnitPriceOutString(Validate.doubleToMoney(sp.getUnitPriceOut()));
            sp.setDiscountString(Validate.doubleToMoney(sp.getDiscount()));
            if (sp.getQuantity() > sp.getBatchQuantity()) {
                sp.setQuantity(sp.getBatchQuantity());
                dao.updateQuantityAfterBuy(sp.getQuantity(),sp.getOrderDetailId());
            }
        }
        request.setAttribute("listCart", listCart);
        request.getRequestDispatcher("/customer/CheckOut.jsp").forward(request, response);
    }

    private void handleUpdateInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkOutDAO dao = new checkOutDAO();
        BatchProductDAO bpdao = new BatchProductDAO();
        HttpSession session = request.getSession();

        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");
        String receiverName = request.getParameter("fullName");
        String deliveryLocation = request.getParameter("address");
        String receiverPhone = request.getParameter("receiverPhone");
        String paymentType = request.getParameter("payment");
        String paymentStatus = "Waiting";//tạm thời để cứng
        String deliveryStatus = "Waiting";
        int isConfirmed = Integer.parseInt(request.getParameter("isConfirmed"));
        int orderId = (int) session.getAttribute("giohangOrderId");

        if (paymentType.equals("Cash")) {
            isConfirmed = 1;//set về thành order

            dao.updateCartInfo(paymentStatus, paymentType, deliveryLocation, deliveryStatus, receiverName, receiverPhone, isConfirmed, account.getUserId(), orderId);
            bpdao.updateBatch(account.getUserId());
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //Gửi email thông báo cho người dùng
            handleSendEmail(orderId, deliveryLocation, account);
            request.setAttribute("notifyOrderSuccess", "success");
            request.getRequestDispatcher("/customer/OrderHistory").forward(request, response);
        } else {
            //Xử lý thanh toán qua cổng VnPay
            //Lấy các giá trị đơn hàng và người mua hàng để gửi sang trang vnpay_pay.jsp để chọn phương thức thanh toán
            // đã có account trong session cần truyền truyền giá trị đơn hàng (sẽ lấy giá trị từ db để tránh lỗi phát sinh từ giao diện)
            // tạo hàm truy vấn trong OrdersDAO
            OrdersDAO odao = new OrdersDAO();
            int totalOrderValue = (int) odao.findOrderValueById(orderId);       //chuyển sang int
            if (totalOrderValue != -1) {
                System.out.println("Tổng giá trị đơn hàng có orderId " + orderId + " là: " + totalOrderValue);
            } else {
                System.out.println("Có lỗi xảy ra khi tìm tổng giá trị đơn hàng với orderId " + orderId);
            }
            request.setAttribute("totalOrderValue", totalOrderValue);
            //set orderId vào session
            session.setAttribute("orderConfirm", orderId);
            
            //gửi thông tin để sendEmail
            session.setAttribute("deliveryLocation", deliveryLocation);
            //update để chuyển giỏ hàng thành order, trạng thái chưa thanh toán
            isConfirmed = 1;//set về thành order
//            dao.updateCartInfo(paymentStatus, paymentType, deliveryLocation, receiverName, receiverPhone, isConfirmed, account.getUserId(), orderId);
            dao.updateCartInfo(paymentStatus, paymentType, deliveryLocation, deliveryStatus, receiverName, receiverPhone, isConfirmed, account.getUserId(), orderId);
            //chuyển đến trang vnpay_pay.jsp
            request.getRequestDispatcher("/vnpay_pay.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static void handleSendEmail(int orderId, String deliveryLocation, Users account) throws UnsupportedEncodingException {
        checkOutDAO dao = new checkOutDAO();
        BatchProductDAO bpdao = new BatchProductDAO();
//        HttpSession session = request.getSession();
        //Lấy về userID từ account trong sesion khi đăng nhập
//        Users account = (Users) session.getAttribute("account");
//        int orderId = (int) session.getAttribute("giohangOrderId");
//        String deliveryLocation = request.getParameter("address");

        //Gửi email thông báo đến người dùng
        String fullName1 = Validate.removeVietnameseAccents(account.getFullName());

        String tieuDe = "Thank you, " + fullName1 + " for purchasing at our FreshFoodStore!";
        //Lấy những vật phẩm họ đã đặt hàng
//            List<gioHang> allProductShopping = dao.showShoppingCartByID(orderID); // In ra console để kiểm tra danh sách sản phẩm
        ArrayList<OrderDetail> listCart = dao.listAllCart(account.getUserId(), orderId);
        for (OrderDetail hang : listCart) {
            hang.setShippingFeeString(Validate.doubleToMoney(hang.getShippingFee()));             //set tiền dạng String cho price
            hang.setUnitPriceOutString(Validate.doubleToMoney(hang.getUnitPriceOut()));     //set tiền dạng String cho total
            hang.setDiscountString(Validate.doubleToMoney(hang.getDiscount()));
        }
        String fullName = account.getFullName();
        String phone = account.getPhone();
        double sumMoney = 0;
        StringBuilder detail = new StringBuilder();
        detail.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("<head>\n")
                .append("<meta charset=\"UTF-8\">\n")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
                .append("<title>Document</title>\n")
                // Thêm các style khác
                .append("<style>")
                .append(".text-head th { color: grey; text-transform: uppercase; font-size: 15px; text-align: center; } ")
                .append(".body td:nth-child(2), .body td:nth-child(3), .body td:nth-child(4),.body td:nth-child(5) { vertical-align: middle; text-align: center; } ")
                .append("table { border-collapse: collapse; } ")
                .append("table td { padding: 10px; border: 1px solid #ccc; } ")
                .append("table th { padding: 10px; text-transform: uppercase; letter-spacing: 1px; border: 1px solid #ccc; } ")
                .append("</style>\n")
                .append("</head>\n")
                // Bắt đầu phần body
                .append("<body>\n");
        // Thêm phần tiêu đề của bảng (thead)
        detail.append("<table>");
        detail.append("<thead>");
        detail.append("<tr class=\"text-head\">");  // Áp dụng class "text-head" vào thẻ <tr>
        detail.append("<th style=\"width: 55%;\">Sản phẩm</th>");
        detail.append("<th>Giá bán</th>");
        detail.append("<th>Số lượng</th>");
        detail.append("<th>Giảm giá</th>");
        detail.append("<th>Thành tiền</th>");
        detail.append("</tr>");
        detail.append("</thead>");

        // Phần thân bảng (tbody)
        detail.append("<tbody>");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        for (int i = 0; i < listCart.size(); i++) {
            detail.append("<tr class=\"body\">");  // Áp dụng class "body" vào thẻ <tr>

            // Sản phẩm và hình ảnh
            detail.append("<td>");
            detail.append("<img src=\"").append(listCart.get(i).getProductImage()).append("\" ")
                    .append("width=\"70\" height=\"50\" class=\"me-2\" style=\"mix-blend-mode: multiply;\" alt=\"\">");
            detail.append("<span>").append(listCart.get(i).getProductName()).append("</span>");
            detail.append("</td>");

            // Giá bán
            detail.append("<td>").append(listCart.get(i).getUnitPriceOutString()).append("</td>");

            // Số lượng
            detail.append("<td>").append(listCart.get(i).getQuantity()).append("</td>");
            // Giảm giá
            detail.append("<td>").append(listCart.get(i).getDiscountString()).append("</td>");
            double unitPrice = listCart.get(i).getUnitPriceOut();  // Giá bán thực tế (dạng số)
            double discount = listCart.get(i).getDiscount();       // Giá trị giảm giá (dạng số)
            int quantity = listCart.get(i).getQuantity();          // Số lượng sản phẩm

            // Thành tiền = (giá bán - (giá bán * tỷ lệ giảm giá / 100)) * số lượng
            double discountAmount = unitPrice * (discount / 100);
            double total = (unitPrice - discountAmount) * quantity;
            // Thành tiền
            detail.append("<td>").append(currencyVN.format(total)).append("</td>");

            detail.append("</tr>");
            sumMoney += total;

            // Tính tổng tiền
        }
        String shippingFeeString = listCart.get(0).getShippingFeeString();//để hiển thị
        double shippingFee = listCart.get(0).getShippingFee();//để tính
        // Tính tổng tiền bao gồm phí ship
        double totalWithShipping = sumMoney + shippingFee;
        // Thêm hàng mới vào detail
        detail.append("<tr>");

// Thêm cột Thành tiền
        detail.append("<td>Thành tiền: ").append(currencyVN.format(sumMoney)).append("</td>");
// Thêm cột Phí vận chuyển
        detail.append("<td colspan='2'>Phí vận chuyển: ").append(shippingFeeString).append("đ</td>");
// Thêm cột Tổng tiền hóa đơn với colspan="2"
        detail.append("<td colspan='2'>Tổng tiền hóa đơn: ").append(currencyVN.format(totalWithShipping)).append("</td>");
// Đóng hàng
        detail.append("</tr>");

        detail.append("</tbody>");
        detail.append("</table>");
        detail.append("</body>\n")
                .append("</html>\n");

        // Tạo chuỗi HTML hoàn chỉnh
        String noiDung = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Document</title>\n"
                + "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n"
                + "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n"
                + "    <link href=\"https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400..700&display=swap\" rel=\"stylesheet\">\n"
                + "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n"
                + "        integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n"
                + "    <style>\n"
                + "        h2 {\n"
                + "            font-family: \"Dancing Script\", cursive;\n"
                + "            font-optical-sizing: auto;\n"
                + "            font-weight: bold;\n"
                + "            font-style: normal;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "\n"
                + "        p {\n"
                + "            /* font-family: \"Dancing Script\", cursive; */\n"
                + "            /* font-weight: bold; */\n"
                + "            font-style: normal;\n"
                + "            /* font-size: 20px; */\n"
                + "            text-indent: 30px;\n"
                + "        }\n"
                + "    </style>"
                + "</head>\n"
                + "<body>\n"
                //                    + "<h1> Cảm ơn bạn vì đã lựa chọn HexTech! </h1>\n"
                //                    + "<h5> Tên người nhận:    " + nameOrder + "</h5>\n"
                //                    + "<h5> Số điện thoại:     " + phone + "</h5>\n"
                //                    + "<h5> Địa chỉ giao hàng: " + deliveryLocation + "</h5>\n"
                //                    + "<hr>\n"
                //                    + "<h5> Các sản phẩm bạn đã đặt: </h5>\n"
                + "    <div class=\"container-fluid \">\n"
                + "        <h2>Cảm ơn quý khách vì đã tin tưởng lựa chọn cửa hàng FreshFoodStore!</h2>\n"
                + "        <p>Kính gửi: Quý khách hàng, " + fullName + ".</p>\n"
                + "        <p>Số điện thoại:" + phone + "</p>\n"
                + "        <p>Địa chỉ giao hàng: " + deliveryLocation + "</p>\n"
                + "        <p>Thay mặt cho cửa hàng thực phẩm tươi sống FreshFoodStore Việt Nam xin được gửi lời cảm ơn chân thành tới quý khách hàng\n"
                + "            <strong style=\"color: blue; font-style: italic;\">" + fullName + "</strong> vì đã mua hàng của chúng tôi.\n"
                + "        </p>\n"
                + "        <hr>\n"
                + "        <p>Các sản phẩm mà quý khách đã lựa chọn đặt hàng: </p>\n"
                + detail.toString()
                + "          "
                + "        <p>Sự tin tưởng của quý khách là vinh dự và là nguồn động lực lớn để chúng\n"
                + "            tôi phát triển và cung cấp những mặt hàng, dịch vụ tốt nhất đến cho quý khách. Chúng tôi mong muốn nhận\n"
                + "            được những phản hồi, góp ý của quý khách để góp phần nâng cao chất lượng</p>\n"
                + "        <p style=\"font-style: italic; color: red;\">Chúng tôi xin cảm ơn quý khách vì đã tin tưởng lựa chọn FreshFoodStore!</p>\n"
                + "        <p style=\"font-style: italic; color: red;\">Mọi thắc mắc, phản hồi xin hãy liên hệ theo số điện thoại 0582647644\n"
                + "            để được hỗ trợ từ FreshFoodStore! Chúc quý\n"
                + "            khách một ngày tốt lành! &#128522;</p>\n"
                + "    </div>"
                //                    + "<h4> Hãy liên hệ 0582647644 để được hỗ trợ từ HexTech! Chúc bạn một ngày tốt lành! </h4>\n"
                + "</body>\n"
                + "</html>";

        Email.sendEmail("tamclone123@gmail.com", tieuDe, noiDung);//chỉnh email gửi về tại đây đang để tạm test
    }

}
//done

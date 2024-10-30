<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="feature.vnPay.Config"%>     <%-- lỗi chỗ này --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<%@page import="jakarta.servlet.http.HttpSession" %>
<%@page import="java.util.*" %>
<%@page import="dal.OrdersDAO" %>
<%@page import="model.Users" %>
<%@page import="dal.BatchProductDAO" %>
<%@page import="controller.checkOutController" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>KẾT QUẢ THANH TOÁN</title>
        <link href="/FreshFoodStore/assets/bootstrap.min.css" rel="stylesheet"/>
        <link href="/FreshFoodStore/assets/jumbotron-narrow.css" rel="stylesheet"> 
        <script src="/FreshFoodStore/assets/jquery-1.11.3.min.js"></script>
<!--        <link rel="stylesheet" href="./assets/bootstrap.min.css"/>
        <link rel="stylesheet" href="./assets/jumbotron-narrow.css"/>
        <script src="./assets/jquery-1.11.3.min.js"></script>-->
    </head>
    <body>
        <%
            //Begin process return from VNPAY
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }

            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = Config.hashAllFields(fields);
        %>
        
        <%
//            //lấy code từ doGet của checkOutController, đã comment lại code bên đó
//            //xử lý cập nhật paymentStatus khi khách hàng thanh toán qua VnPay
//            int giohangOrderId = (int) session.getAttribute("giohangOrderId");
//            String newPaymentStatus = "Paid";
//            
//            OrdersDAO ordersDAO = new OrdersDAO();
//            if ("00".equals(request.getParameter("isPaySuccess"))) {
//                //Xử lý/Cập nhật tình trạng giao dịch thanh toán "Thành công"
//                ordersDAO.updatePaymentStatusById(giohangOrderId, newPaymentStatus);
//            }
//            // Xóa attribute "giohangOrderId" khỏi session
//            session.removeAttribute("giohangOrderId");
//            
//            //Lấy về userID từ account trong sesion khi đăng nhập
//            Users account = (Users) session.getAttribute("account");
//            //Update lại quantity trong batchProduct
//            BatchProductDAO bpdao = new BatchProductDAO();
//            bpdao.updateBatch(account.getUserId());
        %>

        <div class="container">
            <div class="header clearfix">
                <h3 class="text-muted">KẾT QUẢ THANH TOÁN</h3>
            </div>
            <div class="table-responsive">
                <!-- Hiển thị các thông tin thanh toán -->
                <div class="form-group">
                    <label >Mã giao dịch thanh toán:</label>
                    <label><%=request.getParameter("vnp_TxnRef")%></label>
                </div>    
                <div class="form-group">
                    <label >Số tiền:</label>
                    <label><%=request.getParameter("vnp_Amount")%></label>
                </div>  
                <div class="form-group">
                    <label >Mô tả giao dịch:</label>
                    <label><%=request.getParameter("vnp_OrderInfo")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã lỗi thanh toán:</label>
                    <label><%=request.getParameter("vnp_ResponseCode")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã giao dịch tại CTT VNPAY-QR:</label>
                    <label><%=request.getParameter("vnp_TransactionNo")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã ngân hàng thanh toán:</label>
                    <label><%=request.getParameter("vnp_BankCode")%></label>
                </div> 
                <div class="form-group">
                    <label >Thời gian thanh toán:</label>
                    <label><%=request.getParameter("vnp_PayDate")%></label>
                </div> 
                <div class="form-group">
                    <label >Tình trạng giao dịch:</label>
                    <label>
                        <%
                            if (signValue.equals(vnp_SecureHash)) {
                                if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                                    out.print("Thành công");
                                    
                                    //lấy code từ doGet của checkOutController, đã comment lại code bên đó
                                    //xử lý cập nhật paymentStatus khi khách hàng thanh toán qua VnPay
                                    int giohangOrderId = (int) session.getAttribute("giohangOrderId");
                                    String newPaymentStatus = "Paid";

                                    OrdersDAO ordersDAO = new OrdersDAO();
                                    //Xử lý/Cập nhật tình trạng giao dịch thanh toán "Thành công"
                                    ordersDAO.updatePaymentStatusById(giohangOrderId, newPaymentStatus);
                       
                                    //Lấy về userID từ account trong sesion khi đăng nhập
                                    Users account = (Users) session.getAttribute("account");
                                    //Update lại quantity trong batchProduct
                                    BatchProductDAO bpdao = new BatchProductDAO();
                                    bpdao.updateBatch(account.getUserId());
                                    
                                    //Lấy thông tin vận chuyển được gửi qua session từ checkOutController.java
                                    String deliveryLocation = (String) session.getAttribute("deliveryLocation");
                                    //Gửi email về cho khách hàng
                                    checkOutController.handleSendEmail(giohangOrderId, deliveryLocation, account);
                                    
                                    // Xóa attribute "giohangOrderId" khỏi session
                                    session.removeAttribute("giohangOrderId");
                                    session.removeAttribute("deliveryLocation");
                                    
                                    session.setAttribute ("notifyOrderSuccess", "success");
                                    
                                } else {// Xóa attribute "giohangOrderId" khỏi session
                                    session.removeAttribute("giohangOrderId");
                                    out.print("Không thành công");
                                }
                            } else {
                                out.print("Invalid signature");
                            }
                        %>
                    </label>
                </div>
                <!-- Nút và xử lý chuyển hướng -->
                <a href="#" id="paymentLink" onclick="document.getElementById('redirectForm').submit(); return false;">
                    <button class="btn btn-primary">Trở về</button>
                </a>
            </div>

            <!-- Form ẩn để submit yêu cầu sau 10 giây hoặc khi bấm nút -->
            <form id="redirectForm" action="${pageContext.request.contextPath}/customer/checkOut" method="get" style="display:none;">
                <input type="hidden" name="isPaySuccess" value="00"/>
            </form>

            <!-- Tự động chuyển hướng sau 10 giây -->
            <script type="text/javascript">
                setTimeout(function() {
                    document.getElementById("redirectForm").submit();
                }, 10000); // 10 giây
            </script>

            <footer class="footer">
                <p>&copy; VNPAY 2020</p>
            </footer>
        </div>  
    </body>
</html>

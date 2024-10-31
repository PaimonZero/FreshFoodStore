/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DeliveryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Delivery;
import model.Users;

/**
 *
 * @author HoangNam
 */
@WebServlet(name = "DeliveryController", urlPatterns = {"/admin/DeliveryList"})
public class DeliveryController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeliveryController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeliveryController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DeliveryDAO dao = new DeliveryDAO();

        // Get the user account from the session
        Users account = (Users) session.getAttribute("account");

        if (account != null) {
            String role = account.getRole();

            // Redirect if the role is not manager, staff, or shipper
            if (!role.equals("manager") && !role.equals("staff") && !role.equals("shipper")) {
                session.setAttribute("notifyAuth", "notAuthorized");
                String targetURL = request.getContextPath() + "/customer/Homepage";
                String encodedURL = response.encodeRedirectURL(targetURL);
                response.sendRedirect(encodedURL);
                return;
            } else if (role.equals("shipper")) {
                //- Lấy giá trị action về
                String action = request.getParameter("action") == null ? "" : request.getParameter("action");
                //- switch case cac action
                switch (action) {
                    case "search":
                        handleSearch(request, response);
                        break;
                    default:
                        //Lấy shipperId để lấy listDelivery cho shipper đó
                        int shipperId = dao.getShipperIdByUserId(account.getUserId());
                        List<Delivery> deliveryList = dao.getAllDeliveryForShipper(shipperId);
                        request.setAttribute("deliveryList", deliveryList);

                        // Chuyển tiếp đến trang Orders.jsp
                        request.getRequestDispatcher("Delivery.jsp").forward(request, response);
                }

            }
        } else {
            // If no account is found in the session, redirect to login or another appropriate page
            String loginURL = request.getContextPath() + "/SignIn.jsp";
            String encodedURL = response.encodeRedirectURL(loginURL);
            response.sendRedirect(encodedURL);
            return;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void handleSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");

        HttpSession session = request.getSession();
        DeliveryDAO dao = new DeliveryDAO();

        // Get the user account from the session
        Users account = (Users) session.getAttribute("account");

        //Lấy shipperId để lấy listDelivery cho shipper đó
        int shipperId = dao.getShipperIdByUserId(account.getUserId());
        
        List<Delivery> deliveryList = dao.searchDeliveries(shipperId, searchQuery);
        request.setAttribute("deliveryList", deliveryList);

        // Chuyển tiếp đến trang Orders.jsp
        request.getRequestDispatcher("Delivery.jsp").forward(request, response);
    }

}

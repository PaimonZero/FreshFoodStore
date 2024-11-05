/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.AdDashboardDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author PC
 */
@WebServlet(name = "AdChartSalesServlet", urlPatterns = {"/admin/AdChartSalesServlet"})
public class AdChartSalesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdDashboardDAO calculator = new AdDashboardDAO();
        Map<Date, Double> revenueData = calculator.calculateTotalRevenueByDate();
        Map<Date, Double> costData = calculator.calculateTotalCostByDate();

        // Tạo JSON Object và Array để giữ thứ tự ngày
        JSONObject json = new JSONObject();
        JSONArray revenueArray = new JSONArray();
        JSONArray costArray = new JSONArray();

        // Chuyển đổi dữ liệu doanh thu thành JSONArray giữ thứ tự
        for (Map.Entry<Date, Double> entry : revenueData.entrySet()) {
            JSONObject revenueEntry = new JSONObject();
            revenueEntry.put("date", entry.getKey().toString());
            revenueEntry.put("value", entry.getValue());
            revenueArray.put(revenueEntry);
        }

        // Chuyển đổi dữ liệu giá vốn thành JSONArray giữ thứ tự
        for (Map.Entry<Date, Double> entry : costData.entrySet()) {
            JSONObject costEntry = new JSONObject();
            costEntry.put("date", entry.getKey().toString());
            costEntry.put("value", entry.getValue());
            costArray.put(costEntry);
        }
        
        // Đưa dữ liệu đã được sắp xếp vào JSON Object
        json.put("revenue", revenueArray);
        json.put("cost", costArray);

        // Thiết lập kiểu nội dung là JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

}

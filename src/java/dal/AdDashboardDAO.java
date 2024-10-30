/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author PC
 */
public class AdDashboardDAO extends DBContext {

    public int calculateTotalProductsSold() {
        int totalSold = 0;
        String sql = "SELECT SUM(od.quantity) AS totalSold "
                + "FROM Orders o "
                + "INNER JOIN OrderDetails od ON o.orderId = od.orderId "
                + "WHERE o.paymentStatus = 'Paid'"; // Chỉ tính các đơn hàng đã thanh toán

        try {
            connection = getConnection(); // Sử dụng phương thức kết nối database có sẵn
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Lấy tổng sản phẩm đã bán
            if (resultSet.next()) {
                totalSold = resultSet.getInt("totalSold");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total products sold: " + e.getMessage());
        } finally {
            closeConnection(); // Đóng kết nối database sau khi hoàn thành
        }

        return totalSold; // Trả về tổng sản phẩm đã bán
    }

    public double calculateTotalRevenue() {
        double totalRevenue = 0.0;
        String sql = "SELECT SUM(orderRevenue + 10000) AS totalRevenue " // Add shipping fee for each order
                + "FROM ( "
                + "    SELECT SUM(od.unitPriceOut * od.quantity * "
                + "           (1 - COALESCE(p.discount, 0) / 100)) AS orderRevenue " // Calculate revenue after applying discount
                + "    FROM Orders o "
                + "    INNER JOIN OrderDetails od ON o.orderId = od.orderId "
                + "    INNER JOIN BatchesProduct bp ON od.batchId = bp.batchId " // Join with BatchesProduct to get productId
                + "    LEFT JOIN Promos p ON bp.productId = p.productId " // Join Promos table to get discount
                + "    WHERE o.paymentStatus = 'Paid' "
                + "    GROUP BY o.orderId " // Group by orderId to calculate revenue for each order
                + ") AS orderTotals"; // Subquery to get total revenue per order

        try {
            connection = getConnection(); // Use the existing method to connect to the database
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Get total revenue
            if (resultSet.next()) {
                totalRevenue = resultSet.getDouble("totalRevenue");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total revenue with discount: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection after execution
        }

        return totalRevenue; // Return the total revenue
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        String sql = "SELECT SUM(rd.inputPrice * od.quantity) AS totalCost "
                + "FROM Orders o "
                + "INNER JOIN OrderDetails od ON o.orderId = od.orderId "
                + "INNER JOIN BatchesProduct bp ON od.batchId = bp.batchId "
                + "INNER JOIN ReceiptDetails rd ON bp.receiptDetailId = rd.receiptDetailId "
                + "WHERE o.paymentStatus = 'Paid'";

        try {
            connection = getConnection(); // Sử dụng phương thức kết nối database có sẵn
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Lấy tổng chi phí vốn
            if (resultSet.next()) {
                totalCost = resultSet.getDouble("totalCost");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total cost: " + e.getMessage());
        } finally {
            closeConnection(); // Đóng kết nối sau khi thực hiện
        }

        return totalCost; // Trả về tổng chi phí vốn
    }

    public double calculateProfit() {
        double totalRevenue = calculateTotalRevenue();
        double totalCost = calculateTotalCost();
        return totalRevenue - totalCost;
    }
/////////////////////////////////////////////////////////////

    public int calculateTotalProducts() {
        int totalProducts = 0;
        String sql = "SELECT COUNT(*) AS totalProducts FROM Products";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the count of products
            if (resultSet.next()) {
                totalProducts = resultSet.getInt("totalProducts");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total products: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalProducts;
    }

    public int calculateTotalQuantityInReceiptDetails() {
        int totalQuantity = 0;
        String sql = "SELECT SUM(quantity) AS totalQuantity FROM ReceiptDetails";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the total quantity
            if (resultSet.next()) {
                totalQuantity = resultSet.getInt("totalQuantity");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total quantity in ReceiptDetails: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalQuantity;
    }

    public double calculateTotalInputPrice() {
        double totalInputPrice = 0.0;
        String sql = "SELECT SUM(inputPrice * quantity) AS totalInputPrice FROM ReceiptDetails";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the total input price
            if (resultSet.next()) {
                totalInputPrice = resultSet.getDouble("totalInputPrice");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total input price: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalInputPrice;
    }

    public int calculateTotalReceipts() {
        int totalReceipts = 0;
        String sql = "SELECT COUNT(*) AS totalReceipts FROM Receipts";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the count of receipts
            if (resultSet.next()) {
                totalReceipts = resultSet.getInt("totalReceipts");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total receipts: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalReceipts;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    public int calculateTotalDeliveredOrders() {
        int totalPaidOrders = 0;
        String sql = "SELECT COUNT(*) AS totalDeliveredOrders FROM Orders WHERE deliveryStatus = 'Delivered'";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the count of paid orders
            if (resultSet.next()) {
                totalPaidOrders = resultSet.getInt("totalDeliveredOrders");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total paid orders: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalPaidOrders;
    }

    public int calculateTotalCanceledOrders() {
        int totalCanceledOrders = 0;
        String sql = "SELECT COUNT(*) AS totalCanceledOrders FROM Orders WHERE deliveryStatus = 'Cancel'";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the count of canceled orders
            if (resultSet.next()) {
                totalCanceledOrders = resultSet.getInt("totalCanceledOrders");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total canceled orders: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalCanceledOrders;
    }

///////////////////////////////////////////////////////////////////////////////////////////////
    public int calculateTotalSuppliers() {
        int totalSuppliers = 0;
        String sql = "SELECT COUNT(*) AS totalSuppliers FROM Suppliers";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the count of suppliers
            if (resultSet.next()) {
                totalSuppliers = resultSet.getInt("totalSuppliers");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total suppliers: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalSuppliers;
    }

    public int calculateTotalCategories() {
        int totalCategories = 0;
        String sql = "SELECT COUNT(*) AS totalCategories FROM Category";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the count of categories
            if (resultSet.next()) {
                totalCategories = resultSet.getInt("totalCategories");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total categories: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalCategories;
    }
///////////////////////////////////////////////////////////////////////////////

    public Map<Date, Double> calculateTotalRevenueByDate() {
        Map<Date, Double> revenueByDate = new TreeMap<>();  // TreeMap để tự động sắp xếp theo Date
        String sql = "SELECT CONVERT(VARCHAR, orderDate, 23) AS orderDate, "
                + "SUM(orderRevenue) AS totalRevenue "
                + "FROM ( "
                + "    SELECT o.orderId, "
                + "           CONVERT(VARCHAR, o.orderCreatedAt, 23) AS orderDate, "
                + "           SUM(od.unitPriceOut * od.quantity * (1 - COALESCE(p.discount, 0) / 100)) + 10000 AS orderRevenue "
                + "    FROM Orders o "
                + "    INNER JOIN OrderDetails od ON o.orderId = od.orderId "
                + "    INNER JOIN BatchesProduct bp ON od.batchId = bp.batchId "
                + "    LEFT JOIN Promos p ON bp.productId = p.productId "
                + "    WHERE o.paymentStatus = 'Paid' "
                + "    GROUP BY o.orderId, o.orderCreatedAt "
                + ") AS OrderRevenueByOrder "
                + "GROUP BY orderDate "
                + "ORDER BY orderDate ASC; ";

        try {
            connection = getConnection();
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                Date orderDate = resultSet.getDate("orderDate");
                double totalRevenue = resultSet.getDouble("totalRevenue");
                revenueByDate.put(orderDate, totalRevenue);  // TreeMap sẽ tự động sắp xếp theo Date
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total revenue by date: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return revenueByDate;
    }

    public Map<Date, Double> calculateTotalCostByDate() {
        Map<Date, Double> costByDate = new TreeMap<>();  // TreeMap để tự động sắp xếp theo ngày
        String sql = "SELECT CONVERT(VARCHAR, o.orderCreatedAt, 23) AS orderDate, "
                + "SUM(rd.inputPrice * od.quantity) AS totalCost "
                + "FROM Orders o "
                + "INNER JOIN OrderDetails od ON o.orderId = od.orderId "
                + "INNER JOIN BatchesProduct bp ON od.batchId = bp.batchId "
                + "INNER JOIN ReceiptDetails rd ON bp.receiptDetailId = rd.receiptDetailId "
                + "WHERE o.paymentStatus = 'Paid' "
                + "GROUP BY CONVERT(VARCHAR, o.orderCreatedAt, 23) "
                + "ORDER BY orderDate ASC";  // Sắp xếp theo ngày

        try {
            connection = getConnection(); // Kết nối tới cơ sở dữ liệu
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào map
            while (resultSet.next()) {
                Date orderDate = resultSet.getDate("orderDate");
                double totalCost = resultSet.getDouble("totalCost");
                costByDate.put(orderDate, totalCost);
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total cost by date: " + e.getMessage());
        } finally {
            closeConnection(); // Đóng kết nối sau khi thực hiện
        }

        return costByDate; // Trả về map chứa tổng chi phí theo ngày
    }

////////////////////////////////////////////////////////////////////////////
    public Map<Date, Integer> calculateOrderCountByDate() {
        Map<Date, Integer> orderCountByDate = new TreeMap<>(); // TreeMap để tự động sắp xếp theo Date
        String sql = "SELECT CONVERT(VARCHAR, o.orderCreatedAt, 23) AS orderDate, "
                + "COUNT(o.orderId) AS orderCount "
                + "FROM Orders o "
                + "GROUP BY CONVERT(VARCHAR, o.orderCreatedAt, 23) "
                + "ORDER BY orderDate ASC;";

        try {
            connection = getConnection();
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                Date orderDate = resultSet.getDate("orderDate");
                int orderCount = resultSet.getInt("orderCount");
                orderCountByDate.put(orderDate, orderCount); // TreeMap sẽ tự động sắp xếp theo Date
            }
        } catch (SQLException e) {
            System.err.println("Error calculating order count by date: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return orderCountByDate;
    }

    public Map<Date, Integer> calculateDeliveredCountByDate() {
        Map<Date, Integer> deliveredOrdersByDate = new TreeMap<>();  // TreeMap để tự động sắp xếp theo Date
        String sql = "SELECT orderDate, "
                + "CASE "
                + "    WHEN COUNT(CASE WHEN deliveryStatus = 'Delivered' THEN 1 END) = 0 "
                + "         AND COUNT(CASE WHEN deliveryStatus != 'Delivered' THEN 1 END) > 0 "
                + "    THEN 0 "
                + "    ELSE COUNT(CASE WHEN deliveryStatus = 'Delivered' THEN 1 END) "
                + "END AS deliveredOrderCount "
                + "FROM ( "
                + "    SELECT CONVERT(VARCHAR, o.orderCreatedAt, 23) AS orderDate, "
                + "           o.deliveryStatus "
                + "    FROM Orders o "
                + ") AS OrdersWithDate "
                + "GROUP BY orderDate "
                + "HAVING COUNT(*) > 0 "
                + "ORDER BY orderDate ASC;";

        try {
            connection = getConnection();
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                Date orderDate = resultSet.getDate("orderDate");
                int deliveredOrderCount = resultSet.getInt("deliveredOrderCount");
                deliveredOrdersByDate.put(orderDate, deliveredOrderCount);
            }
        } catch (SQLException e) {
            System.err.println("Error calculating delivered orders by date: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return deliveredOrdersByDate;
    }

////////////////////////////////////////////////////////////////////////////
    public List<Map<String, Object>> getTopSellingProducts() {
        List<Map<String, Object>> topSellingProducts = new ArrayList<>();
        String sql = "SELECT p.productId, p.name AS productName, SUM(od.quantity) AS totalQuantitySold, "
                + "(SELECT SUM(bp.quantity) FROM BatchesProduct bp WHERE bp.productId = p.productId) AS remainingQuantity, "
                + "od.unitPriceOut AS price "
                + "FROM Products p "
                + "INNER JOIN BatchesProduct bp ON p.productId = bp.productId "
                + "INNER JOIN OrderDetails od ON bp.batchId = od.batchId "
                + "INNER JOIN Orders o ON od.orderId = o.orderId "
                + "WHERE o.paymentStatus = 'Paid' " // Chỉ tính các đơn hàng đã thanh toán
                + "GROUP BY p.productId, p.name, od.unitPriceOut "
                + "ORDER BY totalQuantitySold DESC";

        try {
            connection = getConnection(); // Sử dụng phương thức kết nối database có sẵn
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Lặp qua từng kết quả và lưu vào danh sách
            while (resultSet.next()) {
                Map<String, Object> productData = new HashMap<>();
                productData.put("productId", resultSet.getInt("productId"));
                productData.put("productName", resultSet.getString("productName"));
                productData.put("totalQuantitySold", resultSet.getInt("totalQuantitySold"));
                productData.put("remainingQuantity", resultSet.getInt("remainingQuantity"));
                productData.put("price", resultSet.getBigDecimal("price"));

                topSellingProducts.add(productData); // Thêm bản ghi vào danh sách
            }
        } catch (SQLException e) {
            System.err.println("Error fetching top selling products: " + e.getMessage());
        } finally {
            closeConnection(); // Đóng kết nối database sau khi hoàn thành
        }

        return topSellingProducts; // Trả về danh sách sản phẩm bán chạy nhất
    }

    public List<Map<String, Object>> getLeastStockedProducts() {
        List<Map<String, Object>> leastStockedProducts = new ArrayList<>();
        String sql = "SELECT p.productId, p.name AS productName, bp.quantity AS quantityRemaining, "
                + "p.image AS productImage " // Assuming you have a column for the image URL
                + "FROM Products p "
                + "INNER JOIN BatchesProduct bp ON p.productId = bp.productId "
                + "WHERE bp.quantity > 0 " // Only consider products with positive stock
                + "ORDER BY bp.quantity ASC "; // Order by least quantity remaining

        try {
            connection = getConnection(); // Use the existing method to connect to the database
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Loop through each result and store it in the list
            while (resultSet.next()) {
                Map<String, Object> productData = new HashMap<>();
                productData.put("productId", resultSet.getInt("productId"));
                productData.put("productName", resultSet.getString("productName"));
                productData.put("quantityRemaining", resultSet.getInt("quantityRemaining"));
                productData.put("productImage", resultSet.getString("productImage")); // Assuming this is a URL or path to the image

                leastStockedProducts.add(productData); // Add the record to the list
            }
        } catch (SQLException e) {
            System.err.println("Error fetching least stocked products: " + e.getMessage());
        } finally {
            closeConnection(); // Close the database connection after completion
        }

        return leastStockedProducts; // Return the list of least stocked products
    }

////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        // Create an instance of the class containing the method
        AdDashboardDAO calculator = new AdDashboardDAO();

        // Call the method to calculate the total number of products sold
        int totalSold = calculator.calculateTotalProductsSold();

        // Print out the total number of products sold
        System.out.println("Total number of products sold: " + totalSold);

        // Call the method to calculate 
        double totalRevenue = calculator.calculateTotalRevenue();
        double totalCost = calculator.calculateTotalCost();
        double profit = calculator.calculateProfit();
        // Format the total revenue without trailing zeros
        DecimalFormat df = new DecimalFormat("#,###.##");
        System.out.println("Total sales revenue: " + df.format(totalRevenue));
        System.out.println("Total profit: " + df.format(profit));
        System.out.println("Total cost of sold products: " + df.format(totalCost));

        int totalProducts = calculator.calculateTotalProducts();
        System.out.println("Total number of products: " + totalProducts);

        int totalQuantity = calculator.calculateTotalQuantityInReceiptDetails();
        System.out.println("Total quantity of products in ReceiptDetails: " + totalQuantity);

        double totalInputPrice = calculator.calculateTotalInputPrice();
        System.out.println("Total input price: " + df.format(totalInputPrice));

        int totalReceipts = calculator.calculateTotalReceipts();
        System.out.println("Total receipts: " + totalReceipts);

        int totalSuppliers = calculator.calculateTotalSuppliers();
        System.out.println("Total number of suppliers: " + totalSuppliers);

        int totalCategories = calculator.calculateTotalCategories();
        System.out.println("Total number of categories: " + totalCategories);

        int totalDeliveredOrders = calculator.calculateTotalDeliveredOrders();
        System.out.println("Total number of delivered orders: " + totalDeliveredOrders);
        int totalCanceledOrders = calculator.calculateTotalCanceledOrders();
        System.out.println("Total number of canceled orders: " + totalCanceledOrders);

        Map<Date, Double> revenueData = calculator.calculateTotalRevenueByDate();
        System.out.println("Total Revenue by Date:");
        for (Map.Entry<Date, Double> entry : revenueData.entrySet()) {
            System.out.println("Date: " + entry.getKey() + ", Total Revenue: " + df.format(entry.getValue()));
        }

        Map<Date, Double> costByDate = calculator.calculateTotalCostByDate();
        System.out.println("Total Cost by Date:");
        for (Map.Entry<Date, Double> entry : costByDate.entrySet()) {
            System.out.println("Date: " + entry.getKey() + ", Total cost: " + df.format(entry.getValue()));
        }

        Map<Date, Integer> orderCounts = calculator.calculateOrderCountByDate();
        System.out.println("Order Count by Date:");
        for (Map.Entry<Date, Integer> entry : orderCounts.entrySet()) {
            System.out.println("Date: " + entry.getKey() + " - Order Count: " + entry.getValue());
        }

        Map<Date, Integer> deliveredCounts = calculator.calculateDeliveredCountByDate();
        System.out.println("Delivered Order Count by Date:");
        for (Map.Entry<Date, Integer> entry : deliveredCounts.entrySet()) {
            System.out.println("Date: " + entry.getKey() + " - Delivered Order Count: " + entry.getValue());
        }

        List<Map<String, Object>> topSellingProducts = calculator.getTopSellingProducts();

        // In kết quả
        if (topSellingProducts.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Top Selling Products:");
            for (Map<String, Object> product : topSellingProducts) {
                System.out.println("Product Name: " + product.get("productName"));
                System.out.println("Sold Quantity: " + product.get("totalQuantitySold"));
                System.out.println("Remaining Quantity: " + product.get("remainingQuantity"));
                System.out.println("Price: " + product.get("price"));
                System.out.println("-----------------------");
            }
        }

        List<Map<String, Object>> leastStockedProducts = calculator.getLeastStockedProducts();

        // Print the results
        System.out.println("Least Stocked Products:");
        for (Map<String, Object> product : leastStockedProducts) {
            System.out.printf("Product ID: %d, Product Name: %s, Remaining Quantity: %d, Image URL: %s%n",
                    product.get("productId"),
                    product.get("productName"),
                    product.get("quantityRemaining"),
                    product.get("productImage"));
        }

    }
}

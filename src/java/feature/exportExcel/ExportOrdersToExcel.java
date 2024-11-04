
package feature.exportExcel;

import dto.OrderDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author HoangNam
 */
public class ExportOrdersToExcel {

    private static final int COLUMN_INDEX_ORDER_ID = 0;
    private static final int COLUMN_INDEX_CUSTOMER_NAME = 1;
    private static final int COLUMN_INDEX_PAYMENT_STATUS = 2;
    private static final int COLUMN_INDEX_TOTAL_PRICE = 3;
    private static final int COLUMN_INDEX_ORDER_DATE = 4;
    private static final int COLUMN_INDEX_SHIPPER_NAME = 5;
    private static final int COLUMN_INDEX_DELIVERY_STATUS = 6;

    public static void exportOrdersToExcel(List<OrderDTO> orders, OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Orders");

        // Create header row
        createHeaderRow(sheet);

        // Fill data rows
        int rowIndex = 1;
        for (OrderDTO order : orders) {
            Row row = sheet.createRow(rowIndex++);
            writeOrderData(row, order);
        }

        // Auto-size columns
        for (int i = 0; i <= COLUMN_INDEX_DELIVERY_STATUS; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write workbook to the provided output stream
        workbook.write(outputStream);
        workbook.close(); // Close workbook to free resources
    }

    private static void createHeaderRow(Sheet sheet) {
        CellStyle headerCellStyle = createHeaderCellStyle(sheet.getWorkbook());

        Row headerRow = sheet.createRow(0);
        String[] headers = {"Order ID", "Customer Name", "Payment Status", "Total Price", "Order Date", "Shipper Name", "Delivery Status"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }

    private static CellStyle createHeaderCellStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    private static void writeOrderData(Row row, OrderDTO order) {
        CellStyle currencyStyle = row.getSheet().getWorkbook().createCellStyle();
        currencyStyle.setDataFormat((short) BuiltinFormats.getBuiltinFormat("#,##0.00"));

        Cell cell = row.createCell(COLUMN_INDEX_ORDER_ID);
        cell.setCellValue(order.getOrderId());

        cell = row.createCell(COLUMN_INDEX_CUSTOMER_NAME);
        cell.setCellValue(order.getReceiverName());

        cell = row.createCell(COLUMN_INDEX_PAYMENT_STATUS);
        cell.setCellValue(order.getPaymentStatus());

        cell = row.createCell(COLUMN_INDEX_TOTAL_PRICE);
        cell.setCellValue(order.getTotalPrice());
        cell.setCellStyle(currencyStyle);

        cell = row.createCell(COLUMN_INDEX_ORDER_DATE);
        cell.setCellValue(order.getOrderCreatedAt());

        cell = row.createCell(COLUMN_INDEX_SHIPPER_NAME);
        cell.setCellValue(order.getShipperName());

        cell = row.createCell(COLUMN_INDEX_DELIVERY_STATUS);
        cell.setCellValue(order.getDeliveryStatus());
    }
}


package feature.exportExcel;

import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
/**
 *
 * @author HoangNam
 */
public class ExportReceiptToExcel {

    public static void exportReceiptToExcel(ReceiptDTO receipt, List<ReceiptDetailDTO> receiptDetails, OutputStream outputStream) throws IOException {
        // Sheet for receipt summary
        try (Workbook workbook = new XSSFWorkbook()) {
            // Sheet for receipt summary
            Sheet summarySheet = workbook.createSheet("Receipt Summary");
            createSummarySection(summarySheet, receipt);
            
            // Sheet for receipt details
            Sheet detailsSheet = workbook.createSheet("Receipt Details");
            createDetailsSection(detailsSheet, receiptDetails);
            
            // Write workbook to the provided output stream
            workbook.write(outputStream);
        }
    }

    private static void createSummarySection(Sheet sheet, ReceiptDTO receipt) {
        // Create header style
        CellStyle headerCellStyle = createHeaderCellStyle(sheet.getWorkbook());

        // Create header row for summary
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Receipt ID", "Supplier Name", "Supplier ID", "Input Date", "Total Price", "Product Types", "Total Quantity"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Fill data row with receipt summary
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(receipt.getReceiptId());
        dataRow.createCell(1).setCellValue(receipt.getSupplierName());
        dataRow.createCell(2).setCellValue(receipt.getSupplierId());
        dataRow.createCell(3).setCellValue(receipt.getInputDate().toString());
        dataRow.createCell(4).setCellValue(receipt.getTotalPrice());
        dataRow.createCell(5).setCellValue(receipt.getProductTypes());
        dataRow.createCell(6).setCellValue(receipt.getTotalQuantity());

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private static void createDetailsSection(Sheet sheet, List<ReceiptDetailDTO> receiptDetails) {
        // Create header style
        CellStyle headerCellStyle = createHeaderCellStyle(sheet.getWorkbook());

        // Create header row for details
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Receipt Detail ID", "Product ID", "Product Name", "Quantity", "Input Price", "Expiry Date", "Batch ID"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Fill data rows with receipt details
        int rowIndex = 1;
        for (ReceiptDetailDTO detail : receiptDetails) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(detail.getReceiptDetailId());
            row.createCell(1).setCellValue(detail.getProductId());
            row.createCell(2).setCellValue(detail.getProductName());
            row.createCell(3).setCellValue(detail.getQuantity());
            row.createCell(4).setCellValue(detail.getInputPrice());
            row.createCell(5).setCellValue(detail.getExpiryDate() != null ? detail.getExpiryDate().toString() : "");
            row.createCell(6).setCellValue(detail.getBatchId());
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
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
}

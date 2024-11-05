package feature.exportExcel;

import dal.ProductDAO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import model.Products;
import java.io.OutputStream;

public class ExportProductsToExcel {

    private static final int COLUMN_INDEX_ID = 0;
    private static final int COLUMN_INDEX_NAME = 1;
    private static final int COLUMN_INDEX_UNIT_MEASURE = 2;
    private static final int COLUMN_INDEX_STATUS = 3;
    private static final int COLUMN_INDEX_UNIT_PRICE = 4;
    private static final int COLUMN_INDEX_QUANTITY = 5;
    private static final int COLUMN_INDEX_PROMO_STATUS = 6;

    public static void exportProductsToExcel(List<Products> products, OutputStream outputStream) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Products");
            // Create header row
            createHeaderRow(sheet);
            // Fill data rows
            int rowIndex = 1;
            for (Products product : products) {
                Row row = sheet.createRow(rowIndex++);
                writeProductData(row, product);
            }   // Auto-size columns
            for (int i = 0; i <= COLUMN_INDEX_PROMO_STATUS; i++) {
                sheet.autoSizeColumn(i);
            }   // Write workbook to the provided output stream
            workbook.write(outputStream);
            // Close the workbook to free resources
        }
    }
    
    //Bản dùng với main
//    public static void exportProductsToExcel(List<Products> products, String excelFilePath) throws IOException {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Products");
//
//        // Create header row
//        createHeaderRow(sheet);
//
//        // Fill data rows
//        int rowIndex = 1;
//        for (Products product : products) {
//            Row row = sheet.createRow(rowIndex++);
//            writeProductData(row, product);
//        }
//
//        // Auto-size columns
//        for (int i = 0; i <= COLUMN_INDEX_PROMO_STATUS; i++) {
//            sheet.autoSizeColumn(i);
//        }
//
//        // Write to file
//        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
//            workbook.write(outputStream);
//        }
//
//        System.out.println("Excel file created at " + excelFilePath);
//    }

    private static void createHeaderRow(Sheet sheet) {
        CellStyle headerCellStyle = createHeaderCellStyle(sheet.getWorkbook());

        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Name", "Unit Measure", "Status", "Unit Price", "Quantity", "Promotion Status"};
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

    private static void writeProductData(Row row, Products product) {
        CellStyle currencyStyle = row.getSheet().getWorkbook().createCellStyle();
        currencyStyle.setDataFormat((short) BuiltinFormats.getBuiltinFormat("#,##0.00"));

        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(product.getProductId());

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(product.getName());

        cell = row.createCell(COLUMN_INDEX_UNIT_MEASURE);
        cell.setCellValue(product.getUnitMeasure());

        cell = row.createCell(COLUMN_INDEX_STATUS);
        cell.setCellValue(product.getStatus());

        cell = row.createCell(COLUMN_INDEX_UNIT_PRICE);
        cell.setCellValue(product.getUnitPrice().doubleValue());
        cell.setCellStyle(currencyStyle);

        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellValue(product.getQuantity());

        cell = row.createCell(COLUMN_INDEX_PROMO_STATUS);
        cell.setCellValue(product.getPromotionStatus());
    }

//    public static void main(String[] args) {
//        try {
//            ProductDAO dao = new ProductDAO();
//            List<Products> products = dao.getAllProducts(); // Fetch products from database
//            String excelFilePath = "E:\\All Book\\FromUniversity\\Ki_5\\SWP391\\FreshFoodStore\\test\\testExcel\\products.xlsx";
//            exportProductsToExcel(products, excelFilePath);
//        } catch (IOException e) {
//            System.err.println("Error creating Excel file: " + e.getMessage());
//        }
//    }
}

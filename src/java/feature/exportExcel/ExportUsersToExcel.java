
package feature.exportExcel;

import dto.UsersDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author HoangNam
 */
public class ExportUsersToExcel {

    private static final int COLUMN_INDEX_USER_ID = 0;
    private static final int COLUMN_INDEX_FULL_NAME = 1;
    private static final int COLUMN_INDEX_ADDRESS = 2;
    private static final int COLUMN_INDEX_PHONE = 3;
    private static final int COLUMN_INDEX_EMAIL = 4;
    private static final int COLUMN_INDEX_AVATAR = 5;
    private static final int COLUMN_INDEX_CREATED_AT = 6;
    private static final int COLUMN_INDEX_STATUS = 7;
    private static final int COLUMN_INDEX_ROLE = 8;
    private static final int COLUMN_INDEX_ROLE_ID = 9;

    public static void exportUsersToExcel(List<UsersDTO> users, OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // Create header row
        createHeaderRow(sheet);

        // Fill data rows
        int rowIndex = 1;
        for (UsersDTO user : users) {
            Row row = sheet.createRow(rowIndex++);
            writeUserData(row, user);
        }

        // Auto-size columns
        for (int i = 0; i <= COLUMN_INDEX_ROLE_ID; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write workbook to the provided output stream
        workbook.write(outputStream);
        workbook.close(); // Close workbook to free resources
    }

    private static void createHeaderRow(Sheet sheet) {
        CellStyle headerCellStyle = createHeaderCellStyle(sheet.getWorkbook());

        Row headerRow = sheet.createRow(0);
        String[] headers = {"User ID", "Full Name", "Address", "Phone", "Email", "Avatar", "Created At", "Status", "Role", "Role ID"};
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

    private static void writeUserData(Row row, UsersDTO user) {
        Cell cell = row.createCell(COLUMN_INDEX_USER_ID);
        cell.setCellValue(user.getUserId());

        cell = row.createCell(COLUMN_INDEX_FULL_NAME);
        cell.setCellValue(user.getFullName());

        cell = row.createCell(COLUMN_INDEX_ADDRESS);
        cell.setCellValue(user.getAddress());

        cell = row.createCell(COLUMN_INDEX_PHONE);
        cell.setCellValue(user.getPhone());

        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellValue(user.getEmail());

        cell = row.createCell(COLUMN_INDEX_AVATAR);
        cell.setCellValue(user.getAvatar());

        cell = row.createCell(COLUMN_INDEX_CREATED_AT);
        cell.setCellValue(user.getCreatedAt().toString());

        cell = row.createCell(COLUMN_INDEX_STATUS);
        cell.setCellValue(user.getStatus());

        cell = row.createCell(COLUMN_INDEX_ROLE);
        cell.setCellValue(user.getRoleName());

        cell = row.createCell(COLUMN_INDEX_ROLE_ID);
        cell.setCellValue(user.getRoleId());
    }
}
package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pages.UserData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelWriterUtil {

    private static final String[] HEADERS = {"username","password","firstName","lastName","email","phone","address1","address2","city","state","zip","country"};

    public static void appendUserToExcel(String filePath, UserData user) {
        try {
            File file = new File(filePath);
            Workbook workbook;
            Sheet sheet;
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = WorkbookFactory.create(fis);
                fis.close();
                sheet = workbook.getSheetAt(0);
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Users");
                // create header
                Row header = sheet.createRow(0);
                for (int i = 0; i < HEADERS.length; i++) {
                    Cell c = header.createCell(i);
                    c.setCellValue(HEADERS[i]);
                }
            }

            int lastRow = sheet.getLastRowNum();
            // If file newly created and only header row exists, lastRow = 0 -> next row = 1
            Row row = sheet.createRow(lastRow + 1);
            int c = 0;
            row.createCell(c++).setCellValue(user.username);
            row.createCell(c++).setCellValue(user.password);
            row.createCell(c++).setCellValue(user.firstName);
            row.createCell(c++).setCellValue(user.lastName);
            row.createCell(c++).setCellValue(user.email);
            row.createCell(c++).setCellValue(user.phone);
            row.createCell(c++).setCellValue(user.address1);
            row.createCell(c++).setCellValue(user.address2);
            row.createCell(c++).setCellValue(user.city);
            row.createCell(c++).setCellValue(user.state);
            row.createCell(c++).setCellValue(user.zip);
            row.createCell(c++).setCellValue(user.country);

            // autosize columns (optional)
            for (int i = 0; i < HEADERS.length; i++) sheet.autoSizeColumn(i);

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
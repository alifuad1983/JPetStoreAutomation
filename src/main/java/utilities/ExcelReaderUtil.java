package utilities;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;

public class ExcelReaderUtil {

    public static Object[][] getExcelData(String path, String sheetName) {
        Object[][] data = null;

        try (FileInputStream fis = new FileInputStream(path)) {
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) sheet = wb.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();
            if (rows <= 1) return new Object[0][0];

            int cols = sheet.getRow(0).getLastCellNum();

            data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {  // skip header
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    data[i - 1][j] = cell.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
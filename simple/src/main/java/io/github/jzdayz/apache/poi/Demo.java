package io.github.jzdayz.apache.poi;

import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Demo {

  private static final String RESOURCE_PATH = "/Users/huqingfeng/Documents/projects/java-demos/simple/src/main/resources";

  private static final String PATH = RESOURCE_PATH + "/apache/poi/poi_test.xlsx";

  public static void main(String[] args) {
    readFormula();
  }

  private static void readFormula() {

    try (InputStream inp = new FileInputStream(PATH)) {
      Workbook wb = WorkbookFactory.create(inp);
      Sheet sheet = wb.getSheetAt(0);
      Row row = sheet.getRow(2);
      Cell cell = row.getCell(0);
      System.out.println(cell.getCellFormula());
//            try (OutputStream fileOut = new FileOutputStream(PATH)) {
//                wb.write(fileOut);
//            }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

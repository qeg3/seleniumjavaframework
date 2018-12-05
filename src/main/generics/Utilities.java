package main.generics;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

public class Utilities extends BaseTest{

        //Method to read the data from excel test data sheet and write that into a hash map
        public static Map<String,  Map<String, List<String>>> readExcelData() throws IOException {

            String path = "/Users/vijayaraghavan/seleniumjavaframework/src/main/resources/sample.xlsx";
            FileInputStream fis = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);


            int columnCount = sheet.getRow(0).getLastCellNum();
            int rowCount = sheet.getLastRowNum();

            Map<String, Map<String, List<String>>> excelFileMap = new HashMap<String, Map<String,List<String>>>();

            Map<String, List<String>> dataMap = new HashMap<String, List<String>>();


            String key;

            Row keyRow = sheet.getRow(0);

            //Looping over all the columns and get each cell data
            for(int i=0; i<columnCount; i++)
            {
                Cell keyCell = keyRow.getCell(i);
                key = keyCell.getStringCellValue().trim();
                List<String> valueSet = new ArrayList<String>();
                    for (int j=1; j<=rowCount; j++)
                    {

                        Row valRow = sheet.getRow(j);
                        Cell valueCell = valRow.getCell(i);
                        String value = valueCell.getStringCellValue().trim();
                        valueSet.add(value);
                    }

                //Putting key & value in dataMap
                dataMap.put(key, valueSet);
                excelFileMap.put("DataSheet", dataMap);

            }

            //Returning excelFileMap
            return excelFileMap;

        }

        //Method to retrieve value based on the key
        public static List<String> getExcelData(String key) throws IOException{

            Map<String, List<String>> m = readExcelData().get("DataSheet");
            List<String> value = m.get(key);

            return value;

        }

    //----------------------------------------------------------------------------------------------------------------//

    public static String getCellData_OBR_PC(String path,String sheet,String key,String columnName) {
        String CellData = "";
        int ColumnNum = 1;
        if(columnName.equalsIgnoreCase("Locator Type"))
            ColumnNum = 1;
        else if (columnName.equalsIgnoreCase("Locator Value"))
            ColumnNum = 2;
        else if (columnName.equalsIgnoreCase("Value"))
            ColumnNum = 1;

        try {
            FileInputStream fis = new FileInputStream( new File(path));
            Workbook wb = new HSSFWorkbook(fis);
            Sheet sheet1 = wb.getSheet(sheet);

            int rowCount=sheet1.getLastRowNum();
            for(int row=1;row<=rowCount+1;row++) {
                String RowData =  sheet1.getRow(row).getCell(0).toString();
                if (RowData.trim().equalsIgnoreCase(key)) {
                    CellData = sheet1.getRow(row).getCell(ColumnNum).toString();
                    break;
                }
            }

        } catch (Exception e) {
            reporter.log(LogStatus.ERROR,"Data Failed to fetch, Please check the Excel Sheet Method Parameters");
            Assert.fail();
        }
        return CellData;
    }
    //----------------------------------------------------------------------------------------------------------------//


}



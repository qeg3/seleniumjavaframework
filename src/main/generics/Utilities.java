package main.generics;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class Utilities extends BaseTest{

        //Method to read the data from excel test data sheet and write that into a hash map
        public static Map<String,  Map<String, List<String>>> readExcelData(String path) throws IOException {

            Map<String, Map<String, List<String>>> excelFileMap = null;
            try {

                FileInputStream fis = new FileInputStream(path);
                Workbook workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheetAt(0);
                String key;

                int columnCount = sheet.getRow(0).getLastCellNum();
                int rowCount = sheet.getLastRowNum();

                excelFileMap = new HashMap<String, Map<String, List<String>>>();

                Map<String, List<String>> dataMap = new HashMap<String, List<String>>();

                Row keyRow = sheet.getRow(0);

                //Looping over all the columns and get each cell data
                for (int i = 0; i < columnCount; i++)
                {
                    Cell keyCell = keyRow.getCell(i);
                    key = keyCell.getStringCellValue().trim();
                    List<String> valueSet = new ArrayList<String>();
                    for (int j = 1; j <= rowCount; j++)
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


            } catch (Exception e) {
                //System.out.println(e.getMessage());
                System.out.println("The test data file is not available in the mentioned path");

                throw(e);
            }

            //Returning excelFileMap
            return excelFileMap;
        }

        //Method to retrieve value based on the key
        public static List<String> getExcelData(Map<String, Map<String, List<String>>> m, String key) throws IOException
        {

            //Map<String, List<String>> m = readExcelData(path).get("DataSheet");
            Map<String, List<String>> n = m.get("DataSheet");
            List<String> value = n.get(key);

            return value;
        }

    //----------------------------------------------------------------------------------------------------------------//

    //Method to retrieve the Locator Value and Locator Type based on the Element to set the application Objects
    public static WebElement getExcelDataOR(Map<String, Map<String, List<String>>> m, String key) throws IOException{

        Map<String, List<String>> n = m.get("DataSheet");
        //String ObjRepoMetadata[];

        String ObjRepoMetadata[] = new String[100];

        List<String> Element = n.get("Element");
        List<String> LocatorType = n.get("LocatorType");
        List<String> LocatorValue = n.get("LocatorValue");

        for(int i=0;i<Element.size();i++)
        {

            if (Element.get(i).equalsIgnoreCase(key))
            {
                ObjRepoMetadata[0] = LocatorType.get(i);
                ObjRepoMetadata[1] = LocatorValue.get(i);
                break;
            }
        }
        CommonMethods com = new CommonMethods();
         WebElement element = com.findElement(ObjRepoMetadata[0],ObjRepoMetadata[1]);

        return element;

    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * This Method is used to get element locator type and locator value Data From the Excel Sheet.
     * @param path is the path of the Excel file in which Directory it is stored.
     * @param sheet iterate to sheet Name
     * @param key iterate to key value we are trying to search
     * @param columnName iterate to columnName we are trying to search
     * @return CellData;
     */

    public static String getcellData_OBR_PC(String path,String sheet,String key,String columnName) {
        String CellData = "";
        int ColumnNum = 1;
        if(columnName.equalsIgnoreCase("LocatorType"))
            ColumnNum = 1;
        else if (columnName.equalsIgnoreCase("LocatorValue"))
            ColumnNum = 2;
        else if (columnName.equalsIgnoreCase("Value"))
            ColumnNum = 1;

        try {
            FileInputStream fis = new FileInputStream( new File(path));
            Workbook wb = new XSSFWorkbook(fis);
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
    /**
     * This Method is used to get element locator type and locator value Data From the Excel Sheet.
     * @param path is the path of the Excel file in which Directory it is stored.
     * @param sheet iterate to sheet Name
     * @param key iterate to key value we are trying to search
     * @return CellData;
     */

    public static String getcellData_Test(String path,String sheet,String key) {
        String CellData = "";
        int RowNum = 1;

        try {
            FileInputStream fis = new FileInputStream( new File(path));
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet1 = wb.getSheet(sheet);

            int columnCount=sheet1.getRow(0).getLastCellNum();
            for(int cell=0;cell<=columnCount;cell++) {
                String RowData =  sheet1.getRow(0).getCell(cell).toString();
                if (RowData.trim().equalsIgnoreCase(key)) {
                    CellData = sheet1.getRow(RowNum).getCell(cell).toString();
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



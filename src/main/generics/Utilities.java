package main.generics;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import main.generics.CommonMethods;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

public class Utilities {

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

}



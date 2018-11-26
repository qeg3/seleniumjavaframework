package main.generics;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {

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




}



package excel;

import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Iterator;

public class FromExcelToCsv {
    static FileInputStream inputStream;
    static XSSFWorkbook workbook;
    //static XSSFSheet sheet;


    public FromExcelToCsv(String excelPath) {

        try {
            inputStream = new FileInputStream(excelPath);
            workbook =new XSSFWorkbook(inputStream);

        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public static int retrieveNoOfCols(String wsName){
        int SheetIndex = workbook.getSheetIndex(wsName);
        if (SheetIndex == -1){
            return 0;
        }
        else
        {
           XSSFSheet sheet=workbook.getSheetAt(SheetIndex);
            int Colnum = sheet.getRow(0).getLastCellNum();
            return Colnum;
        }
    }

    public int retrieveNoOfRows(String wsName){
        int SheetIndex=workbook.getSheetIndex(wsName);
        if (SheetIndex == -1){
            return 0;
        }
        else
        {
            XSSFSheet sheet=workbook.getSheetAt(SheetIndex);
            int RowCount=sheet.getLastRowNum();
            return RowCount+1;
        }


    }

    public  void convertToCsv(){
        try {

           // FileInputStream inputStream;
            //inputStream = new FileInputStream(excelPath);
            //workbook = new XSSFWorkbook(inputStream);
            for (int i = 0 ;i< workbook.getNumberOfSheets();i++){
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row>rowIterator=sheet.iterator();
                FileWriter fileWriter = new FileWriter("/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/CSVfiles/"+sheet.getSheetName()+".csv");
                CSVWriter csvWriter = new CSVWriter(fileWriter);
                while (rowIterator.hasNext()){
                    Row row = rowIterator.next();
                    int s=0;
                    String [] csvData = new String[retrieveNoOfCols(sheet.getSheetName())];

                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()){
                        Cell cell = cellIterator.next();

                        switch (cell.getCellType()){
                            case Cell.CELL_TYPE_STRING:
                                csvData[s]= cell.getStringCellValue();
                                break;

                            case Cell.CELL_TYPE_NUMERIC:
                                   // csvData[s]= Double.toString(cell.getNumericCellValue());
                                csvData[s]=Integer.toString((int) cell.getNumericCellValue());
                                    break;

                        }

                        s=s+1;
                    }
                    csvWriter.writeNext(csvData);
                }
                csvWriter.close();
                inputStream.close();
            }
            System.out.printf("Sheets were converted successfully");


                //System.out.println(workbook.getNumberOfSheets());
//            for (int w=0;w<workbook.getNumberOfSheets();w++){
//                System.out.println(workbook.getSheetAt(w).getSheetName());
//
        } catch (Exception e){
            e.printStackTrace();

        }


    }

    public static void main(String[] args) {
      //  convertToCsv("/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/ControlSheet.xlsx");
        FromExcelToCsv fromExcelToCsv = new FromExcelToCsv("/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/ControlSheet.xlsx");
        fromExcelToCsv.convertToCsv();
    }
}

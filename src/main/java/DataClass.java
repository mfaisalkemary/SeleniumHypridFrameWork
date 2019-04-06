import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataClass {


    List<String> DataSheets = new ArrayList<String>();
    MyTests tests = new MyTests();
    Test test1;

    public String getTestDataSheetName(String testName) throws Exception {
        try {
            Fillo fillo = new Fillo();
            //Connection connection = fillo.getConnection("C:\\cracking_code_interview\\SeleniumFramework\\newsheet.xlsx");
            Connection connection = fillo.getConnection("/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/ControlSheet.xlsx");

            String strQuery = "Select TestDataSheetName from tests where testname = '"+ testName +"' and executionflag ='y'";
            //String strQuery = "Select Data from tests where testname = \"addition\" and executionflag =\"y\"";
            Recordset testCaseNames = connection.executeQuery(strQuery);

            /*while(testCaseNames.next()){
                DataSheets.add(testCaseNames.getField(0).value());
            }

            Iterator itl = DataSheets.iterator();
            while (itl.hasNext()) {
                System.out.println(itl.next().toString());
            }*/
            while (testCaseNames.next()) {
                return (testCaseNames.getField(0).value());
            }
            testCaseNames.close();
            connection.close();
            return null;


        } catch (Exception e) {
            throw new Exception("Test name is not correct or not flagged to be executed in the control sheet");


        }

    }

    public Object[][] getTestData(String sheetTestName) throws Exception {
        try {
            Fillo fillo = new Fillo();
            String dataSheetName = getTestDataSheetName(sheetTestName);
            Connection connection = fillo.getConnection("/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/ControlSheet.xlsx");
            String strQuery = "Select * from " + dataSheetName;
            Recordset testData = connection.executeQuery(strQuery);

            if (testData.getCount() == 0) {
                throw new Exception("Data Sheet is empty");
            }

            int rows = testData.getCount();
            int cols = testData.getFieldNames().size();

            String[][] data = new String[rows][cols];
            for (int i = 0; i < rows; i++) {
                testData.next();
                for (int j = 0; j < cols; j++) {
                    //   testData.next();
                    data[i][j] = testData.getField(j).value();
                }
            }







           /* testData.next();

            System.out.println(testData.getField(0).value());

            //testData.next();
            System.out.println(testData.getField(1).value());


            //testData.next();
            System.out.println(testData.getField(2).value());

*/






          /* Iterator<Recordset> dataIterator = (Iterator<Recordset>) testData;

           while (dataIterator.hasNext()){
               System.out.println(dataIterator.next());
           }*/

            /*System.out.println(rows);
            System.out.println(cols);
            */
          /*
          while (testData.next()){
              System.out.println(rows);
              System.out.println(cols);
          }*/

          /*for (int i=0;i<rows;i++){
              testData.next();
              System.out.println(testData.getField(0).value());
          }
*/


            testData.close();
            connection.close();
            //return null;
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Can't retrieve data sheet");

        }


    }


//    public Iterator<Object[]> getTestData2(String sheetTestName) throws Exception {
//                    try {
//                        Fillo fillo = new Fillo();
//                        String dataSheetName = getTestDataSheetName(sheetTestName);
//                        Connection connection = fillo.getConnection("C:\\cracking_code_interview\\SeleniumFramework\\newsheet.xlsx");
//                        String strQuery = "Select * from " + dataSheetName;
//                        Recordset testData = connection.executeQuery(strQuery);
//
//                        if (testData.getCount() == 0) {
//                            throw new Exception("Data Sheet is empty");
//                        }
//
//                        int rows = testData.getCount();
//                        int cols = testData.getFieldNames().size();
//                        ArrayList<Object[]> testData2 = null;
//                        Object[] objects = null;
//
//
//                        String[][] data = new String[rows][cols];
//                        for (int i = 0; i < rows; i++) {
//                            testData.next();
//                            for (int j = 0; j < cols; j++) {
//                                testData.next();
//                                //data[i][j] = testData.getField(j).value();
//                                objects[j] = testData.getField(j).value();
//                                //testData2.add(i,testData.getField(j).value());
//                            }
//                            testData2.add(objects);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    public ArrayList<String> getClassTestsNames() {
        try {
            Class methods = tests.getClass();
            Method[] methodArray = methods.getDeclaredMethods();
            //methods.getAnnotations()
            ArrayList<String> testsNames = new ArrayList<String>();

            for (int i = 0; i < methodArray.length; i++) {
                //   System.out.println(methodArray[i].getName());
                if (methodArray[i].getAnnotation(Test.class) != null) {
                    testsNames.add(methodArray[i].getName());
                    System.out.println(methodArray[i].getName());
                }


                //System.out.println(methodArray[i].getAnnotation(Test.class));
            }
            return testsNames;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public ArrayList<String> getSheetTestsNames() {
        try {
            Fillo fillo = new Fillo();
            //Connection connection = fillo.getConnection("C:\\cracking_code_interview\\SeleniumFramework\\newsheet.xlsx");
            Connection connection = fillo.getConnection("/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/ControlSheet.xlsx");
            String strQuery = "Select testname from tests where executionflag = 'y'";
            Recordset testsNames = connection.executeQuery(strQuery);
            ArrayList<String> SheetTestsNames = new ArrayList<String>();
            while (testsNames.next()) {
                SheetTestsNames.add(testsNames.getField(0).value());
                System.out.println(testsNames.getField(0).value());
            }
            testsNames.close();
            connection.close();
            return SheetTestsNames;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<String> finalTestsList() {
        try {
            ArrayList<String> sheetTestsNames = getSheetTestsNames();
            ArrayList<String> classTestNames = getClassTestsNames();
            ArrayList<String> finalTestList = new ArrayList<String>();

            for (int i = 0; i < sheetTestsNames.size(); i++) {
                for (int j = 0; j < classTestNames.size(); j++) {
                    if (sheetTestsNames.get(i).trim().toUpperCase().equals(classTestNames.get(j).toUpperCase())) {
                        finalTestList.add(sheetTestsNames.get(i).trim().toLowerCase());
                    }
                }
            }

            for (String test : finalTestList) {
                System.out.println(test.toLowerCase());
            }
            return finalTestList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


    @DataProvider(name = "numbers1")
    private Object[][] dataProvider(Method m) {
        try {
            //data=new DataClass();
            return getTestData(m.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }


//    @DataProvider(name = "numbers2")
//    private Iterator<Object[]> dataProvider2(Method m) {
//        try {
//            //data=new DataClass();
//             // return getTestData();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//
//        }
//
//
//
//    }
















    public static void main(String[] args) throws Exception {
        DataClass d= new DataClass();
        // d.getTestData("addition");

        /*List<String> tests=d.getTestsNames();
        for (int i=0;i<tests.size();i++){
            System.out.println(tests.get(i));
        }
*/


       /* List<String> names=d.getSheetTestsNames();
       for (int i=0;i<names.size();i++){
           System.out.println(names.get(i));
       }*/

        // d.testDataProviderInvoker();
        //d.getTestData("Subtraction");
        //d.finalTestsList();
       // d.getClassTestsNames();
        d.getSheetTestsNames();
        //System.out.println( d.getTestDataSheetName("subtraction"));








    }
}

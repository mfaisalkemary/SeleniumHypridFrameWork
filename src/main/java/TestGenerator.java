import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;


import java.util.ArrayList;
import java.util.List;


public  class TestGenerator {


    public void createAndRunTestNGXML() throws IOException {
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("TestSuite1");
        xmlSuite.setVerbose(10);


        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("Test1");


        XmlClass xmlClass = new XmlClass(MyTests.class);

        /* List<XmlInclude> tests= xmlClass.getIncludedMethods();

        for (XmlInclude test:tests){
            System.out.println("hello");
            System.out.println(test.toString());

        }*/

        /*List<String> included=new ArrayList<String>();
        included.add("Multiplication");
*/


        List<XmlClass> classList = new ArrayList<XmlClass>();
        classList.add(xmlClass);

        List<XmlInclude>includes = new ArrayList<XmlInclude>();

        DataClass data=new DataClass();
        ArrayList<String> finalTestsList = data.finalTestsList();
        for (int i=0;i<finalTestsList.size();i++){
            includes.add(new XmlInclude(finalTestsList.get(i)));
        }
        //XmlInclude include=new XmlInclude("addition");



        //includes.add(include);
        xmlClass.setIncludedMethods(includes);


        xmlTest.setXmlClasses(classList);
        //xmlTest.setIncludedGroups(included);

        TestNG testNG = new TestNG();

        List<XmlSuite> xmlSuites = new ArrayList<XmlSuite>();
        xmlSuites.add(xmlSuite);

        testNG.setXmlSuites(xmlSuites);

        FileWriter writer;
        writer = new FileWriter(new File("TestNg.xml"));
        writer.write(xmlSuite.toXml());
        writer.flush();
        writer.close();




        testNG.run();


    }


    public static void main(String[] args) throws IOException {
        TestGenerator run = new TestGenerator();
        run.createAndRunTestNGXML();



    }
}

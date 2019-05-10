package h2;
import org.apache.poi.util.SystemOutLogger;
import org.h2.command.ddl.CreateTable;
import org.h2.tools.Server;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDBGeneration {
   // private static Object Server;


    private static Server server = null;
//    public Server startServer() throws Exception{
//
//        server =Server.createTcpServer("-tcpAllowOthers","-ifNotExists");
//        //return server;
//    }
    public static void startServer()throws Exception{
        if(server == null) {
            server = Server.createTcpServer("-tcpAllowOthers", "-ifNotExists").start();
            System.out.println("Server has been started"+server.getStatus());
        }
    }

    public static void stopServer(){
        if(server != null) server.stop();
    }

    public static Server getServer(){
        return server;
    }


    public void  generateTestDB() throws Exception{
        TestDBGeneration.startServer();
        TestDBGeneration dataGeneration =new TestDBGeneration();
        Class.forName("org.h2.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/IdeaProjects/SeleniumHypridFrameWork/Resources/DB/testDB","root","pass");
        File csvDir = new File("/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/CSVfiles/");
        String regPattern ="(.+?)(\\.[^.]*$|$)";
        Pattern r = Pattern.compile(regPattern);

        File [] files= csvDir.listFiles();
        String [] fileNames = new String[files.length];
        for (int i = 0 ;i<fileNames.length;i++){
            fileNames[i]=files[i].getName();

            Matcher m1 =r.matcher(files[i].getName());
//                Matcher m =r.matcher(fileNames[i]);
//                 m.find();
            //System.out.print(m.group(1)+" ");
            m1.find();


            String drop = "DROP TABLE " +m1.group(1)+ " if exists" ;
            // " Create TABLE TEST AS SELECT * FROM CSVREAD('/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/ControlSheet.csv')";
            Statement stm = conn.createStatement();
            stm.execute(drop);


            String create = "Create Table "+m1.group(1)+" AS SELECT * FROM CSVREAD('/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/CSVfiles/"+m1.group(0)+"')";
            Statement createTable = conn.createStatement();
            createTable.execute(create);



        }
        conn.close();

        TestDBGeneration.stopServer();


        System.out.println("db generated successfully");
    }




    public static void main(String[] args) {
        try {


            TestDBGeneration.startServer();
            TestDBGeneration dataGeneration =new TestDBGeneration();
            //dataGeneration.startServer().start();

           // org.h2.tools.Server.createTcpServer("-tcpAllowOthers","-ifNotExists").start();
           // Server=Server.createTcpServer("-tcpAllowOthers","-ifNotExists");




           // String srartDBserver[]= new String[] {"/bin/bash" , "-c","java","-cp","/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/DataBase/h2-1.4.199.jar","org.h2.tools.Server","-tcpAllowOthers","-ifNotExists"};
          //  Process proc = new ProcessBuilder(srartDBserver).start();

          //  Runtime.getRuntime().exec("java -cp h2-1.4.199.jar org.h2.tools.Server -tcpAllowOthers -ifNotExists");


            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/IdeaProjects/SeleniumHypridFrameWork/Resources/DB/testDB","root","pass");
            File csvDir = new File("/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/CSVfiles/");
            String regPattern ="(.+?)(\\.[^.]*$|$)";
            Pattern r = Pattern.compile(regPattern);

            File [] files= csvDir.listFiles();
            String [] fileNames = new String[files.length];
            for (int i = 0 ;i<fileNames.length;i++){
                fileNames[i]=files[i].getName();

                Matcher m1 =r.matcher(files[i].getName());
//                Matcher m =r.matcher(fileNames[i]);
//                 m.find();
                //System.out.print(m.group(1)+" ");
                m1.find();


                String drop = "DROP TABLE " +m1.group(1)+ " if exists" ;
                // " Create TABLE TEST AS SELECT * FROM CSVREAD('/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/ControlSheet.csv')";
                Statement stm = conn.createStatement();
                stm.execute(drop);


                String create = "Create Table "+m1.group(1)+" AS SELECT * FROM CSVREAD('/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/CSVfiles/"+m1.group(0)+"')";
                Statement createTable = conn.createStatement();
                createTable.execute(create);




            }









           // String s3 = "Select * FROM TEST";
            //Statement createTable = conn.createStatement();
//            Statement dropTable =conn.createStatement();
//            Statement s2 = conn.createStatement();

            //ResultSet rs= s2.executeQuery(s3);

//            while (rs.next()){
//                System.out.println(rs.getArray(4));
//            }

            conn.close();

            TestDBGeneration.stopServer();


            System.out.println("db generated successfully");



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

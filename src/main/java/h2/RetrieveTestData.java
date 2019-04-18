package h2;

import java.sql.*;

public class RetrieveTestData {

    public ResultSet getData(String sql) throws Exception{
        Statement query =null;


        try {
            Class.forName("org.h2.Driver").newInstance();
             Connection conn = DriverManager.getConnection("jdbc:h2:./Resources/DataBase/testDB","root","pass");
           // Connection conn = DriverManager.getConnection("jdbc:h2:./Resources/DataBase/testDB","root","pass");

             query = conn.createStatement();

            ResultSet rs =query.executeQuery(sql);
          //  conn.close();
            return rs;
        }

        catch (Exception e){
            //conn.close();
            e.printStackTrace();
            return null;
        }

        finally {
            if(query!=null){
                query.close();
            }


        }

    }

    public static void main(String[] args) throws Exception{
        RetrieveTestData retrieveTestData = new RetrieveTestData();
        try {

                System.out.println(retrieveTestData.getData("Select * from TESTS").getString(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

import java.io.FileInputStream;
import java.util.Properties;

public class Retriveonfig {
    public  String getPropertyFile(String prptString) {
        String property = "";
        FileInputStream input;
        Properties prop= new Properties();

        try {

            input = new FileInputStream("Configuration/config.properties");
            prop.load(input);
            property = prop.getProperty(prptString);
            return property;
        } catch (Exception var6) {

            return property;
        }
    }

    public static void main(String[] args) {
        Retriveonfig retriveonfig = new Retriveonfig();
       // System.out.println(retriveonfig.getPropertyFile("csvPath"));
        System.out.println(System.getProperty("user.dir")+"/Resources");
    }

}

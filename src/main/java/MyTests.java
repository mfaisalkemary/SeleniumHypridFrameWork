import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.Calculator;

public class MyTests {
    WebDriver driver1;
    Calculator calc ;
    DataClass data;


   /* @DataProvider(name = "numbers")
    private Object [][] getTestData() {
        try {
            data=new DataClass();
            return data.getTestData("Addition");
        }

        catch (Exception e){
            e.printStackTrace();
            return null;

        }

    }*/


    @BeforeMethod
    private void Setup() throws Exception {
        try {
           // System.setProperty("webdriver.chrome.driver", "Grid_Drivers\\Chrome\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", "/Users/mfaisalkemary/IdeaProjects/SeleniumHypridFrameWork/Resources/chromedriver");
            driver1 = new ChromeDriver();
            calc = new Calculator(driver1);
            driver1.manage().window().maximize();
            driver1.get("https://web2.0calc.com/");
            calc.acceptCookies.click();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("couldn't open home page");

        }

    }

    @Test(dataProvider="numbers1" ,dataProviderClass = DataClass.class)
    public void addition(String num1,String num2,String num3){
        try {
            Assert.assertTrue(calc.addition(num1,num2,num3));
            //  calc.addition(num1,num2,num3);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test(dataProvider="numbers1" ,dataProviderClass = DataClass.class)
    public void subtraction(String num1,String num2,String num3){

        try {
            Assert.assertTrue(calc.subtraction(num1,num2,num3));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void Multiplication(){

        System.out.println("from Multiplication test");
    }

    @Test
    public void test1(){

        System.out.println("from test4");
    }


    @AfterMethod
    private void End(){
        driver1.quit();
    }


}

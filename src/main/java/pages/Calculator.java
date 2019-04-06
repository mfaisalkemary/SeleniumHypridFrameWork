package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Calculator {
    public WebDriver Driver;
    public WebDriverWait Wait;
    public Actions actions;

    public Calculator(WebDriver driver){
        this.Driver=driver;
        PageFactory.initElements(driver, this);
    }

    public Calculator(){

    }

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/form/div/button")
    WebElement acceptCookies;


    @FindBy(id= "/html/body/div[1]/form/div/div[2]/div/div[2]/input[19]")
    WebElement number0;

    @FindBy(id="Btn1")
    WebElement number1;

    @FindBy(id="Btn2")
    WebElement number2;

    @FindBy(id="Btn3")
    WebElement number3;

    @FindBy(id="Btn4")
    WebElement number4;

    @FindBy(id="/html/body/div[1]/form/div/div[2]/div/div[2]/input[7]")
    WebElement Number5;

    @FindBy(id="/html/body/div[1]/form/div/div[2]/div/div[2]/input[8]")
    WebElement Number6;

    @FindBy(id="/html/body/div[1]/form/div/div[2]/div/div[2]/input[2]")
    WebElement Number7;

    @FindBy(id="/html/body/div[1]/form/div/div[2]/div/div[2]/input[3]")
    WebElement Number8;

    @FindBy(id="/html/body/div[1]/form/div/div[2]/div/div[2]/input[4]")
    WebElement Number9;


    @FindBy(id="BtnPlus")
    WebElement sumation;


    @FindBy(id="BtnCalc")
    WebElement equals;

    @FindBy(id= "input")
    WebElement result;


    @FindBy(id="BtnClear")
    WebElement Cancel;


    @FindBy(id="BtnMinus")
    WebElement minus;

    @FindBy(id="BtnMult")
    WebElement multiply;

    @FindBy(id="BtnDiv")
    WebElement Divide;

    @FindBy(id="BtnCalc")
    WebElement Equal;

    @FindBy(id= "//*[@id='calccontainer']/div[2]")
    WebElement pageidentifier;

    @FindBy(xpath = "//*[@id=\"histframe\"]/ul/li[1]/p[1]")
    WebElement displayedResult;



    public void waitforpagetoload(){
        Wait = new WebDriverWait(Driver, 20);
        Wait.until(ExpectedConditions.visibilityOf(pageidentifier));
    }
/*

    public Boolean addition(String num1,String num2 , String num3){
        actions = new Actions(Driver);
        actions.moveToElement(Result).sendKeys(num1);
        actions.moveToElement(Sumation).click();
        actions.moveToElement(Result).sendKeys(num2);
        actions.moveToElement(Equal).click();
        actions.moveToElement(Result);
        actions.build().perform();
        Integer i= Integer.parseInt(Result.getText());
        Integer y= Integer.parseInt(num3);
        if (i==y){
            return true;
        }
        else return false;

    }
*/


    public boolean addition(String num1,String num2 , String num3) throws Exception{
        try {

            acceptCookies.click();
            Thread.sleep(2000);
            result.sendKeys(num1);
            sumation.click();
            result.sendKeys(num2);
            equals.click();
            Thread.sleep(3000);

            //System.out.println("hello"+displayedResult.getAttribute("title"));

            if(num3 .equalsIgnoreCase(displayedResult.getAttribute("title"))){
                result.clear();
                return true;

            }
            result.clear();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }

    }

    public boolean subtraction(String num1,String num2 , String num3) throws Exception{
        try {
            result.sendKeys(num1);
            minus.click();
            result.sendKeys(num2);
            equals.click();
            Thread.sleep(3000);

            //System.out.println("hello"+displayedResult.getAttribute("title"));

            if(num3 .equalsIgnoreCase(displayedResult.getAttribute("title"))){
                result.clear();
                return true;

            }
            result.clear();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }

    }




/*

    public int multiply(int num1,int num2){
        actions = new Actions(Driver);
        actions.moveToElement(Result).sendKeys(Integer.toString(num1));
        actions.moveToElement(Multiply).click();
        actions.moveToElement(Result).sendKeys(Integer.toString(num2));
        actions.moveToElement(Equal).click();
        actions.moveToElement(Result);
        actions.build().perform();
        int I=Integer.parseInt(Result.getText());
        return I;
    }



*/

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        try {
            cal.addition("1","1","2");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



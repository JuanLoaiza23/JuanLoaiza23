package web;

import org.openqa.selenium.*;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class OptionProject {

    private WebDriver webDriver;
    private final int WAIT_SECONDS = 3000;

    @Before
    public void getIntoTestWeb() {
        //Browser Options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        //Get Web page
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver(options);
        webDriver.get("https://www.utest.com");

        WebElement logo = webDriver.findElement(By.xpath("//unauthenticated-header/div[1]/div[1]/a[1]/img[1]"));
        WebDriverWait wait = new WebDriverWait(webDriver, WAIT_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(logo));
        Assert.assertTrue(logo.isDisplayed());
        webDriver.findElement(By.linkText("Projects")).click();
        webDriver.manage().timeouts().implicitlyWait(WAIT_SECONDS , TimeUnit.SECONDS);

    }

    @Test
    public void validateDescription() {
        //Options
        WebElement description=  webDriver.findElement(By.className("section-desc-unauth"));

        Assert.assertTrue(description.isDisplayed());
    }

    @Test
    public void ValidateOptions(){

        WebElement suggested = webDriver.findElement(By.xpath("//a[contains(text(),'suggested')]"));
        WebElement all= webDriver.findElement(By.xpath("//a[contains(text(),'all')]"));
        WebElement urgent= webDriver.findElement(By.xpath("//a[contains(text(),'urgent')]"));

        Assert.assertTrue(suggested.isSelected());
        Assert.assertTrue(all.isEnabled());
        Assert.assertTrue(urgent.isDisplayed());
    }




    @Test
    public void getIntoToOptionWhyAboutUs(){


        webDriver.findElement(By.linkText("About Us"));
        WebElement text=  webDriver.findElement(By.className("section-big-title-unauth"));
        WebDriverWait wait = new WebDriverWait(webDriver, WAIT_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(text));
        Assert.assertEquals("About Us",text.getAttribute("innerText"));
    }
    @Test
    public void getIntoToOptionWhyProjects(){


        webDriver.findElement(By.linkText("Log In")).click();

        WebElement text=  webDriver.findElement(By.className("section-big-title-unauth"));
        WebDriverWait wait = new WebDriverWait(webDriver, WAIT_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(text));
        Assert.assertTrue(text.isEnabled());

    }
    @Test
    public void getIntoToOptionWhyLogin(){

        webDriver.findElement(By.linkText("Log In")).click();
        WebElement button=  webDriver.findElement(By.className("btn"));
        WebDriverWait wait = new WebDriverWait(webDriver, WAIT_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        Assert.assertTrue(button.isEnabled());

    }
    @Test
    public void buttonValidateBecomeUTester(){

        webDriver.findElement(By.linkText("Become a uTester")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement inputName = webDriver.findElement(By.id("firstName"));
        Assert.assertTrue(inputName.isDisplayed());
    }


    @After
    public void closeBrowser(){
        webDriver.close();
    }
}

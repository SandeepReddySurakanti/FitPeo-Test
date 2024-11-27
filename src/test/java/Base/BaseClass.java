package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class BaseClass {

    public static ThreadLocal<WebDriver> driverContainer = new ThreadLocal<>();

    public static WebDriver driver=driverContainer.get();


    @BeforeTest
    public void beforeTest()
    {
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }



}

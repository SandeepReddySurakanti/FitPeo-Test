package testClasses;


import Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.FitPeo_HomePage_PageObjects;
import utils.ActionUtil;
import utils.FrameworkException;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FitPeoTest extends BaseClass {

    ActionUtil actionUtil=new ActionUtil();


    @Test
    public void method1() throws Exception {
        driver.get("https://www.fitpeo.com/");
        System.out.println("Line:"+Thread.currentThread().getStackTrace()[1].getLineNumber()+"and method:"+Thread.currentThread().getStackTrace()[1].getMethodName());

        FitPeo_HomePage_PageObjects fitPeo_homePage_pageObjects= PageFactory.initElements(driver,FitPeo_HomePage_PageObjects.class);
        actionUtil.click(Thread.currentThread().getStackTrace()[1].getLineNumber(),fitPeo_homePage_pageObjects.lnk_RevenueCalculator,"Revenue Calculator");


        if(actionUtil.isElementDisplayed(Thread.currentThread().getStackTrace()[1].getLineNumber(),fitPeo_homePage_pageObjects.header_Medicare_Eligible_Patients,"Medicare Eligible Patients"))
        {
            System.out.println("After clicking Revenue Calculator,Medicar Eligible Patients details displayed ");
        }
        else
        {
            throw new FrameworkException("After clicking Revenue Calculator, Medicar Eligible Patients details not displayed ",Thread.currentThread().getStackTrace()[1].getLineNumber());
        }


        /********* moving to slider **********/
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", fitPeo_homePage_pageObjects.header_Medicare_Eligible_Patients);

        // Locate the slider thumb (which is within the span element)
        WebElement sliderThumb = driver.findElement(By.cssSelector(".MuiSlider-thumb"));

        /********* dragging the slider **********/

        Actions actions = new Actions(driver);
        actions.dragAndDropBy(sliderThumb, 93, 0).perform();
        Thread.sleep(1000);

        // Create an instance of Robot
        Robot robot = new Robot();

        // Simulate pressing the "Arrow Right" key
        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_RIGHT);

        Thread.sleep(1000);

        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        Thread.sleep(1000);

        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        Thread.sleep(1000);

        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        Thread.sleep(1000);

        //validating the text field value after moving slider
        String textFieldValue= actionUtil.getAttributeValue(Thread.currentThread().getStackTrace()[1].getLineNumber(),"value",fitPeo_homePage_pageObjects.txtBox_Number_Medicar_Eligible_Patients,"Text field value");
        if(textFieldValue.equals("820"))
        {
            System.out.println("Total Individual Patients value is changing if we move slider");
        }
        else
        {
            throw new FrameworkException("Total Individual Patients value is not changing if we move slider");
        }


        /********** update the text field value to 560 **********/
        fitPeo_homePage_pageObjects.txtBox_Number_Medicar_Eligible_Patients.click();
        Thread.sleep(1000);

        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);

        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);

        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);

        fitPeo_homePage_pageObjects.txtBox_Number_Medicar_Eligible_Patients.sendKeys("560");

        /********** Validating slider is change after entering 560 in text field **********/
        String attributeValue=actionUtil.getAttributeValue(Thread.currentThread().getStackTrace()[1].getLineNumber(),"aria-valuenow",fitPeo_homePage_pageObjects.slider_Medicar_Eligible_Patients,"Slider");
        if(attributeValue.equals("560"))
        {
            System.out.println("Slider is changing when we change text field Value ");
        }
        else
        {
            throw new FrameworkException("Slider is not changing when we change text field Value ");
        }

        Thread.sleep(5000);

        //clicking the different CPT checkboxes
        actionUtil.click(Thread.currentThread().getStackTrace()[1].getLineNumber(),fitPeo_homePage_pageObjects.getCheckBoxOfCPT("CPT-99091"),"CPT-99091 check box");
        actionUtil.click(Thread.currentThread().getStackTrace()[1].getLineNumber(),fitPeo_homePage_pageObjects.getCheckBoxOfCPT("CPT-99453"),"CPT-99453 check box");

        actionUtil.click(Thread.currentThread().getStackTrace()[1].getLineNumber(),fitPeo_homePage_pageObjects.getCheckBoxOfCPT("CPT-99454"),"CPT-99454 check box");

        actionUtil.click(Thread.currentThread().getStackTrace()[1].getLineNumber(),fitPeo_homePage_pageObjects.getCheckBoxOfCPT("CPT-99474"),"CPT-99474 check box");

        String actualValue=actionUtil.getElementText(Thread.currentThread().getStackTrace()[1].getLineNumber(),fitPeo_homePage_pageObjects.txt_Total_Recurring_Reimbursement_for_all_Patients_Per_Month,"Total Recurring Reimbursement for all Patients Per Month:");
        //String actualValue=fitPeo_homePage_pageObjects.txt_Total_Recurring_Reimbursement_for_all_Patients_Per_Month.getText();
        System.out.println("Actual value of Total_Recurring_Reimbursement_for_all_Patients_Per_Month:"+actualValue);

        Assert.assertEquals(actualValue,"$110700","Total Recurring Reimbursement for all Patients Per Month is not matching");




    }
}

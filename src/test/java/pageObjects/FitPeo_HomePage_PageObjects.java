package pageObjects;

import Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FitPeo_HomePage_PageObjects extends BaseClass {

    @FindBy(xpath = "//div[normalize-space(text())='Revenue Calculator']/parent::a")
    public WebElement lnk_RevenueCalculator;

    @FindBy(xpath = "//h4[text()='Medicare Eligible Patients']/..//input[@type='number']")
    public WebElement txtBox_Number_Medicar_Eligible_Patients;

    @FindBy(xpath = "(//h4[text()='Medicare Eligible Patients']/..//input)[1]")
    public WebElement slider_Medicar_Eligible_Patients;

    @FindBy(xpath = "//p[text()='Total Individual Patient/Month']/following-sibling::p")
    public WebElement txt_Total_Individual_Patient;

    @FindBy(xpath = "//p[text()='Total Recurring Reimbursement for all Patients Per Month:']/p")
    public WebElement txt_Total_Recurring_Reimbursement_for_all_Patients_Per_Month;

    @FindBy(xpath = "//h4[text()='Medicare Eligible Patients']")
    public WebElement header_Medicare_Eligible_Patients;

    public WebElement getCheckBoxOfCPT(String cptNumber) {
       //
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='"+cptNumber+"']/..//input[@type='checkbox']/..")));
        return element;
    }
}

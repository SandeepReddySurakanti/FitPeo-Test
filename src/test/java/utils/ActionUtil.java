package utils;

import Base.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;

import org.apache.commons.lang3.StringUtils;

public class ActionUtil extends BaseClass {

    public synchronized void moveToElement(int lineNumber, WebElement element, String labelName) throws Exception {

        try {
            // System.out.println("Moving to the element:" + element.toString() + " in the method: " ++ " and in the class: " + testUtil.getClassesToScenarioMapping(testUtil.getScenarioName().split("-")[0]) + " and line number: " + lineNumber, true);

            new Actions(driver).moveToElement(element).build().perform();
            //APPLICATION_LOGS.info("Moved to the element: "+element);

        } catch (JavascriptException | ElementNotInteractableException e2) {

        } catch (StaleElementReferenceException ex) {
            try {
                Thread.sleep(2000);
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
            } catch (Exception e1) {
                //closeWebDriver(lineNumber);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            throw new FrameworkException("Issue/Rerun --> Application not responding properly due to object progress event errors", lineNumber);
        }

//    		ex.printStackTrace();


    }

    public synchronized void click(int lineNumber, WebElement element, String label) throws Exception
    {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            System.out.println("Element is clickable for : " + element.toString() + " and line number: " + lineNumber);
            moveToElement(lineNumber,element,label);
            element.click();
            Thread.sleep(1000);

        } catch (Exception e) {

            System.out.println("Element is not clickable for : " + element.toString() +  " and line number: " + lineNumber + " due to: " + convertExceptionAsString(e));
            String exceptionMessage=convertExceptionAsString(e);
            if (StringUtils.containsIgnoreCase(exceptionMessage, "ElementClickIntercepted")) {
                throw new ElementClickInterceptedException("Unable to click on the element as it is intercepted for: " + label);
            } else if (StringUtils.containsIgnoreCase(exceptionMessage, "NoSuchElement")) {
                throw new NoSuchElementException("Unable to click on the element as it is not found for: " + label);
            } else if (StringUtils.containsIgnoreCase(exceptionMessage, "StaleElement")) {
                throw new StaleElementReferenceException("Unable to click on the element as it is Stale for: " + label);
            } else if (StringUtils.containsIgnoreCase(exceptionMessage, "ElementNotInteractable")) {
                throw new ElementNotInteractableException("Unable to click on the element as it is not interactable for: " + label);
            } else if (StringUtils.containsIgnoreCase(exceptionMessage, "UnreachableBrowser")) {
                throw new UnreachableBrowserException("Unable to click on the element as it is not interactable for: " + label);
            } else if (StringUtils.containsIgnoreCase(exceptionMessage, "WebDriverException")) {
                throw new WebDriverException("Unable to click on the element as it is not interactable for: " + label);
            } else if (StringUtils.containsIgnoreCase(exceptionMessage, "NoSuchWindow")) {
                throw new NoSuchWindowException("Unable to click on the element as it is not interactable for: " + label);
            } else if (StringUtils.containsIgnoreCase(exceptionMessage, "Timeout")) {
                throw new TimeoutException("Unable to click on the element as the page has been timed out for: " + label);
            } else
                throw new FrameworkException("Unable to click on the element due to: " + exceptionMessage);
        }

    }


    public static String convertExceptionAsString(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        System.setProperty("Exception Msg:", exceptionAsString);
        return exceptionAsString;
    }

    public String getAttributeValue(int lineNumber, String attribute, WebElement element, String label) throws Exception {
        System.out.println("Fetching the attribute of the element having: " + attribute + " for: " + label + " and in the line number: " + lineNumber);
        String invertedCommas="\"";
        try {
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
            return element.getAttribute(attribute);
        } catch (NoSuchElementException ex) {
            throw new FrameworkException("Issue --> " + invertedCommas + attribute + invertedCommas + " not found for: " + label, lineNumber);
        } catch (WebDriverException e2) {
            throw new FrameworkException("Issue --> " + invertedCommas + attribute + invertedCommas + " not found for: " + label + " as the browser is not reachable",  lineNumber);
        }
    }

    public String getElementText(int lineNumber, WebElement element, String label) throws Exception {
        System.out.println("Fetching the text of the element for: " + label + " and in the line number: " + lineNumber);
        String invertedCommas="\"";
        try {
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.getText();
        } catch (NoSuchElementException ex) {
            throw new FrameworkException("Issue --> Element not found for: " + label, lineNumber);
        } catch (WebDriverException e2) {
            throw new FrameworkException("Issue --> Element not found for: " + label + " as the browser is not reachable",  lineNumber);
        }
    }

    public boolean isElementDisplayed(int lineNumber, WebElement element, String label) throws Exception {

        String invertedCommas="\"";
        try {
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException ex) {
            throw new FrameworkException("Issue --> Element not Visible for: " + label, lineNumber);
        } catch (WebDriverException e2) {
            throw new FrameworkException("Issue --> Element not Visible for: " + label + " as the browser is not reachable",  lineNumber);
        }
    }






}

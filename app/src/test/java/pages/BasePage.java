package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected static WebDriver driver;
    private static Actions actions;
    WebDriverWait wait = new WebDriverWait(driver, 15);

    static {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
    }

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    public static void navigateTo(String url) {
        driver.get(url);
    }

    public static void closeBrowser() {
        driver.quit();
    }

    public void goToLinkText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    private WebElement FindByXpath(String locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    private WebElement FindByCssSelector(String locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
    }

    public void clickElement(String locator) {
        FindByXpath(locator).click();
    }

    public void write(String locator, String text) {
        FindByXpath(locator).clear();
        FindByXpath(locator).sendKeys(text);
    }

    public void selectFromDropdownByValue(String locator, String valueToSelect) {
        Select dropdown = new Select(FindByXpath(locator));
        dropdown.selectByValue(valueToSelect);
    }

    public void selectFromDropdownByIndex(String locator, int index) {
        Select dropdown = new Select(FindByXpath(locator));
        dropdown.selectByIndex(index);
    }

    public void selectFromDropdownByText(String locator, String valueToSelect) {
        Select dropdown = new Select(FindByXpath(locator));
        dropdown.selectByVisibleText(valueToSelect);
    }

    public void hoverOverElement(String locator) {
        actions.moveToElement(FindByXpath(locator));
    }

    public void doubleClick(String locator) {
        actions.doubleClick(FindByXpath(locator));
    }

    public void rightClick(String locator) {
        actions.contextClick(FindByXpath(locator));
    }

    public String getValueFromTable(String locator, int row, int column) {
        String cellINeed = locator + "/table/tbody/tr[" + row + "]/td[" + column + "]";

        return FindByXpath(cellINeed).getText();
    }

    public void setValueOnTable(String locator, int row, int column, String stringToSend) {
        String cellToFill = locator + "/table/tbody/tr[" + row + "]/td[" + column + "]";

        FindByXpath(cellToFill).sendKeys(stringToSend);

    }

    public void switchToIframe(int iFrameIndex) {
        try {
            driver.switchTo().frame(iFrameIndex);
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void dismissAlert() {

        driver.switchTo().alert().dismiss();
    }

    public String textFromElement(String locator) {
        return FindByXpath(locator).getText();
    }

    public boolean isElementEnabled(String locator) {
        return FindByXpath(locator).isEnabled();
    }

    public boolean isElementDisplayed(String locator) {
        return FindByXpath(locator).isDisplayed();
    }

    public boolean isElementSelected(String locator) {
        return FindByXpath(locator).isSelected();
    }

    public List<WebElement> bringAllElements(String locator) {
        return driver.findElements(By.className(locator));
    }

}

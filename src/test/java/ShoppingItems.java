import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ShoppingItems {
    By homeButton = By.xpath("//*[@id=\"center_column\"]/ul/li/a/span");
    By categoryOption = By.xpath("//*[@id='block_top_menu']/ul/li[1]/a");
    By subCategoryOption = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[2]/a");
    By itemImageLink = By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img");
    By itemName = By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/h1");
    By selectedColor = By.id("color_11");
    By sizeOption = By.id("group_1");
    By addToCart = By.id("add_to_cart");
    By proceedToCheckOutButton = By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span");
    By reviewAndProceed = By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span");
    By reviewAddressAndProceed = By.xpath("//*[@id=\"center_column\"]/form/p/button/span");
    By reviewTotalCost = By.xpath("//*[@id=\"form\"]/p/button/span");
    By termsAction = By.id("cgv");
    By paymentOption = By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a/span");
    By placeOrder = By.xpath("//*[@id=\"cart_navigation\"]/button/span");


    WebDriver driver;
    WebDriverWait wait;
    DataDrivenManger dataManger;
    ExtentReports reports;
    ExtentTest logs;
    public ShoppingItems(WebDriver driver,DataDrivenManger dataManger,ExtentReports reports){
        this.driver = driver;
        this.dataManger = dataManger;
        this.reports = reports;

        wait = new WebDriverWait(driver, 30);
        logs = reports.startTest("Shopping item","search for item in category and order it");

    }
    public void searchForItem() {
    /*
    * wait until login user and account loaded
    * */
        wait.until(ExpectedConditions.elementToBeClickable(homeButton));
            logs.log(LogStatus.INFO,"landing screen for items opened");

        /*
        * perform filter in women category for sub category Blouses
        * */
        Actions builder = new Actions(driver);
        WebElement iii = driver.findElement(categoryOption);
        builder.moveToElement(iii).build().perform();
        WebElement ttt = driver.findElement(subCategoryOption);
        builder.moveToElement(ttt).build().perform();
        ttt.click();
        logs.log(LogStatus.INFO,"perform search inside category with sub category");

        /*
        * select first item appeared from filter
        * */

        wait.until(ExpectedConditions.elementToBeClickable(itemImageLink));
        driver.findElement(itemImageLink).click();
        logs.log(LogStatus.INFO,"show item appered form filter");

        /*
        * get reviewed item data and keep it in data manger
        * to vailded aginest it in future after i placed order
        * */
        String name = driver.findElement(itemName).getText();
        String color = driver.findElement(selectedColor).getAttribute("name");
        String size =  new Select(driver.findElement(sizeOption)).getFirstSelectedOption().getText();
        dataManger.selectItem(name,color,size);
        logs.log(LogStatus.INFO,"save item deatil to vailding aginest it after order item");

        /*
         * add this item to cart
         * */
        driver.findElement(addToCart).click();
        logs.log(LogStatus.INFO,"item added to card");

        /*
        * select option to proceed to to checkOut
        * */
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckOutButton));
        logs.log(LogStatus.INFO,"item added to card");

        driver.findElement(proceedToCheckOutButton).click();
        logs.log(LogStatus.INFO,"proceed in cehckOut");

        /*
        *
        * review item before process order.
        * can can increase item count
        * check total in screen
        * */

        wait.until(ExpectedConditions.elementToBeClickable(reviewAndProceed));
        driver.findElement(reviewAndProceed).click();
        logs.log(LogStatus.INFO,"review and proceed in cehckOut");

        /*
        * review address and personal information
        * */
        wait.until(ExpectedConditions.elementToBeClickable(reviewAddressAndProceed));
        driver.findElement(reviewAddressAndProceed).click();
        logs.log(LogStatus.INFO,"review address and proceed in cehckOut");

        /*
        * review shipping cost
        * accept Terms of service
        * */
        wait.until(ExpectedConditions.elementToBeClickable(reviewTotalCost));
        driver.findElement(termsAction).click();
        driver.findElement(reviewTotalCost).click();
        logs.log(LogStatus.INFO,"accept terms and review totla cost");

        /*
        * review total price with shipping cost
        * select payment option
        * */
        wait.until(ExpectedConditions.elementToBeClickable(paymentOption));
        driver.findElement(paymentOption).click();
        logs.log(LogStatus.INFO,"select way to pay");

        /*
        * review all deatils and payment way
        * confirm order
        * */
        wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
        driver.findElement(placeOrder).click();
        logs.log(LogStatus.INFO,"order placed");


    }
}


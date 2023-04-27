import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\sliashenko\\Desktop\\CD current version\\97.0.4692.71\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String user = "slyashenko%ri_demo_stage";
        String pass = "Mede1234##";
        driver.get("https://client.medeanalytics.com/default");

        try {
            //WebElemenst for Login

            WebElement username = driver.findElement(By.xpath("//input[@id='login']"));
            WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
            WebElement loginButton = driver.findElement(By.xpath("//span[@class='button__text']"));

            //Actions for login
            username.clear();
            username.sendKeys(user);
            password.clear();
            password.sendKeys(pass);
            loginButton.click();

            //Waiter for loading
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            WebDriverWait loading = new WebDriverWait(driver, Duration.ofSeconds(10));


            //Find Identify Tab
            WebElement identifyTab = driver.findElement(By.xpath("//a[@id='menu_bucket_11_0']"));
            //("//ul[@class='nav']/li[@class='dropdown'][2]/a[@id='menu_bucket_11_0']"));
            identifyTab.click();
            //Find Documentation Opportunities
            WebElement documentOpportunities = driver.findElement(By.xpath("//a[@id='menu_00000000_0000_0000_0000_000000000002']"));
            //"//li[@class='dropdown-submenu']/a[text()='Documentation Opportunities']"));
            Actions hover = new Actions(driver);
            hover.moveToElement(documentOpportunities).perform();
            WebElement codeBenchOpportunities = driver.findElement(By.xpath("//a[@id='menu_00000000_0000_0000_0000_000000000004']"));
            codeBenchOpportunities.click();

            //Waiter for loading
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

            //Drilldown to the Claim Detail
            driver.switchTo().frame("frmMain");
            WebElement claimColumn = driver.findElement(By.xpath("//tr[@data-row-name='[DRGMDC].[MSDRG Group].[MSDRG Group].&[296]']/td[2]"));
            claimColumn.click();

            List<WebElement> fieldslist = driver.findElements(By.xpath("//tr[@class='colhead']"));
            for(int i =0;i<fieldslist.size();i++) {
                String elementText = fieldslist.get(i).getText();
                System.out.println(elementText);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            Thread.sleep(30000);
            driver.quit();
        }





        }
}

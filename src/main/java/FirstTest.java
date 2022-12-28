import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class FirstTest {
    String BASE_URL = "https://next.privat24.ua/money-transfer/card";
    String cardFromExample = "4004159115449011";
    WebDriver driver = new ChromeDriver();
    By cardNumberFrom = By.xpath(".//input[@data-qa-node='numberdebitSource']");
    By expDate = By.xpath(".//input[@data-qa-node='expiredebitSource']");
    By cvv = By.xpath(".//input[@data-qa-node='cvvdebitSource']");
    By nameFrom = By.xpath(".//input[@data-qa-node='firstNamedebitSource']");
    By surnameFrom = By.xpath(".//input[@data-qa-node='lastNamedebitSource']");
    By cardTo = By.xpath(".//input[@data-qa-node='numberreceiver']");
    By nameTo = By.xpath(".//input[@data-qa-node= 'firstNamereceiver']");
    By surnameTo = By.xpath(".//input[@data-qa-node='lastNamereceiver']");
    By amount = By.xpath(".//input[@data-qa-node='amount']");
    By toggleComment = By.xpath(".//span[@data-qa-node='toggle-comment']");
    By comment = By.xpath(".//textarea[@data-qa-node='comment']");
    By btnAddToBasket = By.xpath(".//button[@type='submit']");
    By termsLink = By.xpath(".//a[@href='https://privatbank.ua/terms']");
    @Test
    @Disabled
    void checkMinSum() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(BASE_URL);
        driver.findElement(cardNumberFrom ).sendKeys(cardFromExample);
        driver.findElement(expDate).sendKeys("0725");
        driver.findElement(cvv).sendKeys("123");
        driver.findElement(nameFrom).sendKeys("Olga");
        driver.findElement(surnameFrom).sendKeys("Zviahina");
        driver.findElement(cardTo).sendKeys("5309233034765085");
        driver.findElement(nameTo).sendKeys("Igor");
        driver.findElement(surnameTo).sendKeys("Ivanov");
        driver.findElement(amount).sendKeys("500");
        driver.findElement(toggleComment).click();
        driver.findElement(comment).sendKeys("Test comment Hillel");
        driver.findElement(btnAddToBasket).submit();

        Assertions.assertEquals(cardFromExample, driver.findElement(By.xpath(".//span[@data-qa-node='payer-card']")).getText());


    }
    @Test
    void checkSwitchToNewWindow() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize(); //

        driver.get(BASE_URL);
        driver.findElement(termsLink).click();
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(driver.getWindowHandles().size() - 1));

        Assertions.assertEquals("https://privatbank.ua/terms", driver.getCurrentUrl());
        Assertions.assertEquals("Умови та правила", driver.getTitle());
    }

    }

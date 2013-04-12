package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:8080");
        System.out.println(driver.getPageSource());
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println("==");

        System.out.println(driver.getPageSource());
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();

        System.out.println("==");
        System.out.println(driver.getPageSource());

        oikeaNimiVaaraSalasana(driver);
        kayttajaTunnustaEiOle(driver);
        luoKayttajatunnus(driver);
        kirjauduDumbledorena(driver);

    }

    public static void oikeaNimiVaaraSalasana(WebDriver statham) {
        statham.get("http://localhost:8080");
        System.out.println(statham.getPageSource());
        WebElement element = statham.findElement(By.linkText("login"));
        element.click();
        System.out.println("==");
        System.out.println(statham.getPageSource());
        element = statham.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = statham.findElement(By.name("password"));
        element.sendKeys("akkep1");
        element = statham.findElement(By.name("login"));
        element.submit();
    }

    public static void kayttajaTunnustaEiOle(WebDriver thompson) {
        thompson.get("http://localhost:8080");
        System.out.println(thompson.getPageSource());
        WebElement element = thompson.findElement(By.linkText("login"));
        element.click();
        System.out.println("==");
        System.out.println(thompson.getPageSource());
        element = thompson.findElement(By.name("username"));
        element.sendKeys("batman");
        element = thompson.findElement(By.name("password"));
        element.sendKeys("akkep1");
        element = thompson.findElement(By.name("login"));
        element.submit();
    }

    public static void luoKayttajatunnus(WebDriver doink) {
        doink.get("http://localhost:8080");
        System.out.println(doink.getPageSource());
        WebElement element = doink.findElement(By.linkText("register new user"));
        element.click();
        System.out.println("==");
        element = doink.findElement(By.name("username"));
        element.sendKeys("dumbledore");
        element = doink.findElement(By.name("password"));
        element.sendKeys("robottiwobotti123");
        element = doink.findElement(By.name("passwordConfirmation"));
        element.sendKeys("robottiwobotti123");
        element.submit();
    }

    public static void kirjauduDumbledorena(WebDriver nimbus) {
        nimbus.get("http://localhost:8080");
        System.out.println(nimbus.getPageSource());
        WebElement emmentaali = nimbus.findElement(By.linkText("login"));
        emmentaali.click();
        System.out.println("==");
        System.out.println(nimbus.getPageSource());
        emmentaali = nimbus.findElement(By.name("username"));
        emmentaali.sendKeys("dumbledore");
        emmentaali = nimbus.findElement(By.name("password"));
        emmentaali.sendKeys("robottiwobotti123");
        emmentaali = nimbus.findElement(By.name("login"));
        emmentaali.submit();
    }
}

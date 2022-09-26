package structureForFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class TestCase2 {
    @Test
    public void TestCase1() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/Students/Index");
        WebElement createNewBtn = driver.findElement(By.xpath("//a[@href=\"/Students/Create\"]"));
        createNewBtn.click();
        driver.switchTo().frame("aswift_2");
        driver.switchTo().frame("ad_iframe");

        WebElement closeBtn = driver.findElement(By.xpath("//div[@id=\"dismiss-button\"]"));
        closeBtn.click();

        WebElement firstnameBox = driver.findElement(By.id("FirstName"));
        firstnameBox.sendKeys("Savr");
        WebElement lastnameBox = driver.findElement(By.id("LastName"));
        lastnameBox.sendKeys("Muchkaev");
        WebElement enrollmentDate = driver.findElement(By.id("EnrollmentDate"));
        enrollmentDate.sendKeys("8/28/2022");
        WebElement createBtn = driver.findElement(By.xpath("//input[@class=\"btn btn-default\"]"));
        createBtn.click();

        WebElement searchBox = driver.findElement(By.id("Search_Data"));
        searchBox.sendKeys("Savr");
        WebElement findBtn = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
        findBtn.click();

        WebElement actualFirstname = driver.findElement(By.xpath("//tr[@style=\"border:1px solid black;text-align:center\"]"));
        Assert.assertTrue(BrowserUtils.getText(actualFirstname).contains("Savr"));

    }

    @Test
    public void TestCase2() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/Students/Index");

        WebElement searchBox = driver.findElement(By.id("Search_Data"));
        searchBox.sendKeys("Muchkaev");
        WebElement findBtn = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
        findBtn.click();
        driver.switchTo().defaultContent();
        WebElement editBtn = driver.findElement(By.xpath("//button[.=\"EDIT\"]"));
        editBtn.click();
        WebElement firstNameBox = driver.findElement(By.id("FirstName"));
        firstNameBox.clear();
        firstNameBox.sendKeys("Gela");
        WebElement saveBtn = driver.findElement(By.xpath("//input[@class=\"btn btn-default\"]"));
        saveBtn.click();
        searchBox = driver.findElement(By.id("Search_Data"));
        searchBox.sendKeys("Gela");
        findBtn = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
        findBtn.click();
        WebElement firstNameBox1 = driver.findElement(By.xpath("//table"));

        Assert.assertTrue(BrowserUtils.getText(firstNameBox1).contains("Gela"));
    }

    @Test
    public void TestCase3() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/Students/Index");
        WebElement searchBox = driver.findElement(By.id("Search_Data"));
        searchBox.sendKeys("Muchkaev");
        WebElement findBtn = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
        findBtn.click();
        WebElement deleteBtn = driver.findElement(By.xpath("//button[.='DELETE']"));
        deleteBtn.click();
        WebElement deleteButton = driver.findElement(By.xpath("//input[@class=\"btn btn-default\"]"));
        deleteButton.click();
        BrowserUtils.switchByTitle(driver, "Testing Controls ");
        WebElement searchBox1 = driver.findElement(By.id("Search_Data"));
        searchBox1.sendKeys("Muchkaev");
        WebElement findBtn1 = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
        findBtn1.click();
        WebElement message = driver.findElement(By.xpath("//div[@class=\"container body-content\"]"));
        System.out.println(message.getText());
        Assert.assertTrue(message.getText().contains("There are zero students with this search text Page 0 of 0"));
    }

    @Test
    public void TestCase4() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/");
        WebElement dragBox = driver.findElement(By.xpath("//div[@class=\"ui-widget-content ui-draggable ui-draggable-handle\"]"));
        WebElement dropHereBox = driver.findElement(By.xpath("//div[@class=\"ui-widget-header ui-droppable\"]"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(dragBox, dropHereBox).perform();
        WebElement message = driver.findElement(By.id("droppable"));
        String expectedMessage = "Dropped!";
        Assert.assertEquals(BrowserUtils.getText(message), "Dropped!");

        //TESTCASE 5

        WebElement doubleClickBtn = driver.findElement(By.xpath("//button[@ondblclick=\"myDblClickFunction()\"]"));
        actions.doubleClick(doubleClickBtn).perform();
        Alert alert = driver.switchTo().alert();
        String actualMessage = alert.getText();
        String expectedMessage1 = "Double Clicked !!";
        Assert.assertEquals(actualMessage, expectedMessage1);
        alert.accept();

    }

    @Test
    public void TestCase6() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/");

        driver.switchTo().frame("iframe_a");
        WebElement iframeBox = driver.findElement(By.xpath("//body/input[@id=\"name\"]"));
        iframeBox.sendKeys("Savr");
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
        WebElement secondLink = driver.findElement(By.xpath("//h3[.='click on the below link: ']"));

        BrowserUtils.scrollWithJS(driver, secondLink);
        WebElement link = driver.findElement(By.xpath("//p//a[@href=\"http://www.uitestpractice.com/\"]"));
        BrowserUtils.clickWithJS(driver, link);
        WebElement doubleClickBtn = driver.findElement(By.xpath("//button[@ondblclick=\"myDblClickFunction()\"]"));
        BrowserUtils.scrollWithJS(driver, doubleClickBtn);
        driver.switchTo().frame("iframe_a");
        Actions actions = new Actions(driver);
        Thread.sleep(3000);
        WebElement iframe = driver.findElement(By.xpath("//div[@id=\"sub-frame-error-details\"]"));
        actions.moveToElement(iframe).perform();
        String expectedMessage = "refused to connect.";
        Assert.assertTrue(BrowserUtils.getText(iframe).contains(expectedMessage));
        System.out.println(iframe.getText().trim());

    }

    @Test
    public void TestCase7() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/");

        WebElement clickNewWindow = driver.findElement(By.xpath("//a[@href=\"https://www.youtube.com/watch?v=dX-MDFWKBwE\"]"));
        clickNewWindow.click();
        Thread.sleep(2000);
        driver.manage().window().maximize();
        driver.getTitle();
        String mainId = driver.getWindowHandle();
        BrowserUtils.switchById(driver, mainId);
        String actualTitle = driver.getTitle();
        String expectedTitle = "C# Beginner to advanced";
        Assert.assertTrue(actualTitle.contains(expectedTitle));
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));


    }
}

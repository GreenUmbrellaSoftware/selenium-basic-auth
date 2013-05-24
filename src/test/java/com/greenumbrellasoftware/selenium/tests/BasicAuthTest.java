package com.greenumbrellasoftware.selenium.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.security.UserAndPassword;

import static org.junit.Assert.*;

public class BasicAuthTest {

    static WebDriver driver;
    String pageURL = "https://localhost:8443/manager/html";

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");

        // throws org.openqa.selenium.UnsupportedCommandException: Not implemented yet
        driver = new InternetExplorerDriver();

        // Alert not present
        // driver = new FirefoxDriver();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        driver.quit();
    }

    @Test
    public void testLogon() throws Exception {
        driver.get(pageURL);
        Alert logonDialog = driver.switchTo().alert();
        Credentials userAndPassword = new UserAndPassword("tomcat", "tomcat");
        logonDialog.authenticateUsing(userAndPassword);
        assertEquals("manager", driver.getTitle());
    }
}

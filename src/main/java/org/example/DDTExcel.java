package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DDTExcel {
    protected static String result;
    WebDriver driver;
    @Test(dataProvider = "testdata")
    public void DemoProject(String username, String password) {
        try {
            // Set up ChromeDriver using WebDriverManager
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("http://newtours.demoaut.com");

            driver.findElement(By.name("userName")).sendKeys(username);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.name("submit")).click();

            Thread.sleep(5000);

            Assert.assertTrue(driver.getTitle().matches("Login: Mercury Tours"), "Invalid credentials");
            System.out.println("Login successful");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    void ProgramTermination() {
        driver.quit();
    }
    @DataProvider(name = "testdata")
    public Object[][] TestDataFeed() {
        org.example.ReadExcelFile config = new org.example.ReadExcelFile("C:\\Users\\ADMIN\\OneDrive\\Documents\\STUDY\\ĐBCLKT\\Installation\\LoginCredentials.xlsx");

        int rows = config.getRowCount(0);

        Object[][] credentials = new Object[rows][2];

        for (int i = 0; i <rows; i++) {
            credentials[i][0] = config.getData(0,i,0);
            credentials[i][1] = config.getData(0,i,1);
        }
        return credentials;
    }
}
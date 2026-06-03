package com.automation;


import com.automation.annotations.DataProviderFilePath;
import com.automation.dataProviders.CSVDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.pages.LoginPage;
import com.microsoft.playwright.Page;


public class AppTest extends BaseTest{
   
    @Test
    public void shouldAnswerWithTrue(){
        Page page = newPage();        
        LoginPage loginPage = new LoginPage(page);        
        loginPage.navigate();
        loginPage.loginWithStandardUserThenVerify();
        System.out.println(page.title());
    }

    @DataProviderFilePath("testdata/addTest.csv")
    @Test(dataProvider = "csvReader", dataProviderClass = CSVDataProvider.class)
    public void addTest(int a, int b, int r, boolean isTrue){
        Assert.assertEquals(a+b,r);
        Assert.assertTrue(isTrue);
    }

    @Test
    public void readCSv(){
        CSVDataProvider csvDataProvider = new CSVDataProvider();
        //System.out.println(csvDataProvider.csvData("testdata/addTest.csv").next()[0]);
    }
}

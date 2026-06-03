package com.automation;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.automation.core.BrowserProvider;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

@Slf4j
public class BaseTest {

    protected BrowserContext context;

    @BeforeClass(alwaysRun=true)
    public void setup(){
        log.debug("setup called");
        Browser browser = BrowserProvider.chromium();
        this.context = browser.newContext();
    }

    protected Page newPage(){
        return context.newPage();
    }

    @AfterClass(alwaysRun=true)
    public void tearDown(){
        log.debug("tearDown called");
        this.context.close();
        BrowserProvider.down();
    }

}

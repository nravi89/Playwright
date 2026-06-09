package com.automation.core;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Playwright;

public class BrowserProvider {

    private static Playwright playwright = Playwright.create();

    public static Browser chromium(){
        return playwright.chromium().launch(getLaunchOptions());
    }

    public static Browser firefox(){
        return playwright.firefox().launch(getLaunchOptions());
    }

    public static Browser webkit(){
        return playwright.webkit().launch(getLaunchOptions());
    }

    private static LaunchOptions getLaunchOptions(){
        LaunchOptions launchOptions = new LaunchOptions();

        if(!Env.HEADLESS)
            launchOptions.setHeadless(false);

        if(Env.SLOWMO > 0)
            launchOptions.setSlowMo(Env.SLOWMO);

        return launchOptions;
    }

    public static void down() {
        //playwright.close();
    }



}

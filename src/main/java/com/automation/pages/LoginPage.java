package com.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class LoginPage extends BasePage{

    private final Locator username;
    private final Locator password;
    private final Locator btnLogin;

    public LoginPage(Page page){
        super(page);
        username = this.page.locator("#user-name");
        password = this.page.locator("#password");
        btnLogin = this.page.locator("#login-button");
    }

    public void login(String username, String password){
        this.username.fill(username);
        this.password.fill(password);
        btnLogin.click();
    }

    public void loginWithStandardUser(){
        this.login("standard_user", "secret_sauce");
    }

     public void loginWithStandardUserThenVerify(){
        this.login("standard_user", "secret_sauce");
        verifyPage();
    }


    @Override
    public void navigate() {
        navigate("https://www.saucedemo.com/");
    }

    @Override
    public void verifyPage() {
       PlaywrightAssertions.assertThat(page).hasTitle("Swag Labs");
    }

    

}

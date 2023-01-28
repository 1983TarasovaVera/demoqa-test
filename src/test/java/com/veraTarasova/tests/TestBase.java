package com.veraTarasova.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    @BeforeAll
    static void BeforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
        Configuration.browserSize = "2100x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }
}

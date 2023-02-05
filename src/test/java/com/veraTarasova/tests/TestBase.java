package com.veraTarasova.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.veraTarasova.config.CredentialsConfig;
import com.veraTarasova.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class TestBase {

    public static CredentialsConfig credentialsConfig =
            ConfigFactory.create(CredentialsConfig.class);
    static String login = credentialsConfig.login();
    static String password = credentialsConfig.password();
    static String credentials = format("https://%s:%s@",login,password);
    static String browser = System.getProperty("browser", "chrome");
    static String version = System.getProperty("version", "100.0.4896.60");
    static String browserSize = System.getProperty("browserSize", "2100x1080");
    static String url = System.getProperty("url","selenoid.autotests.cloud/wd/hub/");

    @BeforeAll
    static void BeforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = credentials + url;
        Configuration.browser = browser;
        Configuration.browserSize = browserSize;
//        Configuration.browserVersion = version;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}

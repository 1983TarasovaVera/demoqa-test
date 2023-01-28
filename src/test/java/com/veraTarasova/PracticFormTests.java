package com.veraTarasova;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class PracticFormTests {

    @BeforeAll
    static void BeforeAll() {
        Configuration.browserSize = "2100x1080";
        Configuration.baseUrl = "https://demoqa.com";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    void fillFormTest() {
        //Определение переменных для заполнения полей формы
        String url = "/automation-practice-form";
        String firstName = "Vera";
        String lastName = "Tarasova";
        String mail = "Tarasova@mail.ru";
        String phoneNumber = "9172111111";
        String address = "Street, house";
        String gender = "Female";
        String hobby = "Music";
        String subject = "Economics";
        String state = "NCR";
        String city = "Delhi";

        step("1. открыть веб-форму", () -> {
            open(url);
        });
        step("2. заполнить форму", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(mail);
            $(byText(gender)).click();
            $("#userNumber").setValue(phoneNumber);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("September");
            $(".react-datepicker__year-select").selectOption("1999");
            $("[aria-label=\"Choose Monday, September 13th, 1999\"]").click();
            $("#subjectsInput").setValue(subject).pressEnter();
            $("#hobbiesWrapper").$(byText(hobby)).click();
            $("#uploadPicture").uploadFromClasspath("images/1.jpg");
            $("#currentAddress").setValue(address).scrollTo().click();
            $("#state").click();
            $("#stateCity-wrapper").$(byText(state)).click();
            $("#city").scrollTo().click();
            $("#stateCity-wrapper").$(byText(city)).click();
            takeScreenshot();
            takePageSource();
        });
        step("3. сохранить форму", () -> {
            $("#submit").pressEnter();
            takeScreenshot();
            takePageSource();
        });
        step("4. проверить сохранение данных", () -> {
            $(".table-responsive").$(byText("Student Name")).parent().shouldHave(Condition.text(firstName + " " + lastName));
            $(".table-responsive").$(byText("Student Email")).parent().shouldHave(Condition.text(mail));
            $(".table-responsive").$(byText("Gender")).parent().shouldHave(Condition.text(gender));
            $(".table-responsive").$(byText("Mobile")).parent().shouldHave(Condition.text(phoneNumber));
            $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(Condition.text("13 September,1999"));
            $(".table-responsive").$(byText("Subjects")).parent().shouldHave(Condition.text(subject));
            $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(Condition.text(hobby));
            $(".table-responsive").$(byText("Picture")).parent().shouldHave(Condition.text("1.jpg"));
            $(".table-responsive").$(byText("Address")).parent().shouldHave(Condition.text(address));
            $(".table-responsive").$(byText("State and City")).parent().shouldHave(Condition.text(state + " " + city));
            takeScreenshot();
            takePageSource();
        });
    }
    @Attachment(value = "Скриншот", type = "image/png")
    public byte[] takeScreenshot () {
        return Selenide.screenshot(OutputType.BYTES);
    }
    @Attachment(value = "Страница", type = "text/html")
    public byte[] takePageSource() {
        return WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }
}

package com.vera.tarasova;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoQATests {

    @BeforeAll
    static void BeforeAll() {
        Configuration.browserSize = "2100x1080";
        Configuration.baseUrl = "https://demoqa.com";
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

        //Открытие веб-формы
        open(url);

        // Заполнение полей формы

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
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        //Сохранение формы
        $("#submit").pressEnter();

        //Проверка сохранения введенных данных в итоговой таблице
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


    }

}

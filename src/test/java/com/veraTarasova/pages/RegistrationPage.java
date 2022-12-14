package com.veraTarasova.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.veraTarasova.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.veraTarasova.tests.TestData.*;

public class RegistrationPage {

    private final String FORM_TITLE = "Student Registration Form";
    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            mailInput = $("#userEmail"),
            phoneNumberInput = $("#userNumber"),
            subjectInput = $("#subjectsInput"),
            hobbyInput = $("#hobbiesWrapper"),
            pictureLoad = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("#state"),
            stateCityInput = $("#stateCity-wrapper"),
            cityInput = $("#city");
    public CalendarComponent calendar = new CalendarComponent();

    public void openPage() {
        open(url);
        formTitle.shouldHave(text(FORM_TITLE));
    }

    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeMail(String value) {
        mailInput.setValue(value);
        return this;
    }

    public RegistrationPage typeGender(String value) {
        $(byText(value)).click();
        return this;
    }

    public RegistrationPage typePhoneNumber(String value) {
        phoneNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage typeSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage typeHobby(String value) {
        hobbyInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage loadPicture() {
        pictureLoad.uploadFromClasspath("images/1.jpg");
        return this;
    }

    public RegistrationPage typeAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationPage typeState(String value) {
        stateInput.scrollTo().click();
        stateCityInput.$(byText(value)).click();
        return this;
    }

    public void typeCity(String value) {
        cityInput.click();
        stateCityInput.$(byText(value)).click();
    }
    public RegistrationPage typeBirthDate(String day, String month, String year) {
        calendar.setDate(day,month,year);
        return this;
    }

    public void submitData() {
        $("#submit").pressEnter();
    }

    public void checkResultsValue(String key, String value) {
        $(".table-responsive").$(byText(key)).parent().shouldHave(Condition.text(value));
    }
}

package com.veraTarasova.tests;

import com.veraTarasova.pages.RegistrationPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.veraTarasova.tests.TestData.*;
import static io.qameta.allure.Allure.step;

public class PracticFormTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @Tag("ui-tests")
    void fillFormTest() {
    
        step("1. открыть веб-форму", () -> {
            registrationPage.openPage();
        });

        step("2. заполнить форму", () -> {
             registrationPage.typeFirstName(firstName)
                .typeLastName(lastName)
                .typeMail(mail)
                .typeGender(gender)
                .typePhoneNumber(phoneNumber)
                .typeBirthDate(dayOfBirth, monthOfBirth, yearOfBirth)
                .typeSubject(subject)
                .typeHobby(hobby)
                .loadPicture()
                .typeAddress(address)
                .typeState(state)
                .typeCity(city);
        });
        
         step("3. сохранить форму", () -> {
             registrationPage.submitData();
          });   
 
        step("4. проверить сохранение данных", () -> {
          registrationPage.checkResultsValue("Student Name", (firstName + " " + lastName));
          registrationPage.checkResultsValue("Student Email", mail);
          registrationPage.checkResultsValue("Gender", gender);
          registrationPage.checkResultsValue("Mobile", phoneNumber);
          registrationPage.checkResultsValue("Date of Birth", (dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
          registrationPage.checkResultsValue("Subjects", subject);
          registrationPage.checkResultsValue("Hobbies", hobby);
          registrationPage.checkResultsValue("Picture", ("1.jpg"));
          registrationPage.checkResultsValue("Address", address);
          registrationPage.checkResultsValue("State and City", state + " " + city);
        });
    }
}

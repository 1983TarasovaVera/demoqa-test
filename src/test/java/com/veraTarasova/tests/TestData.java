package com.veraTarasova.tests;

import com.github.javafaker.Faker;

public class TestData {
    static Faker faker = new Faker();
    public static String url = "/automation-practice-form",
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            mail = faker.internet().emailAddress(),
            address = faker.address().streetAddress(),
            phoneNumber = "" + faker.number().numberBetween(1111111111, 2147483647),
            dayOfBirth = "28",
            monthOfBirth = "September",
            yearOfBirth = "1999",
            gender = "Female",
            hobby = "Music",
            subject = "Economics",
            state = "NCR",
            city = "Delhi";

}

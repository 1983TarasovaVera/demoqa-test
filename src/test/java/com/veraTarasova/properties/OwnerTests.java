package com.veraTarasova.properties;

import com.veraTarasova.config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class OwnerTests {
    public CredentialsConfig credentialsConfig =
            ConfigFactory.create(CredentialsConfig.class);
    @Test
    void readCredentialTest() {
        String login = credentialsConfig.login();
        String password = credentialsConfig.password();
        String message = format("i login as %s with password %s",login,password);

        System.out.println(login);
        System.out.println(password);
        System.out.println(message);
    }
}

package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.RegistrationTestPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    RegistrationTestPage registrationTestPage = new RegistrationTestPage();

    @BeforeAll
    static void setupEnvironment(){
        Configuration.browserSize = "1920x1280";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
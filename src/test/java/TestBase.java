import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1280";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @AfterEach
    void afterEach () {
        closeWebDriver();
    }
}


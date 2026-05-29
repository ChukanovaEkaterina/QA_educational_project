import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {    @BeforeAll
static void beforeAll(){
    Configuration.browserSize = "1920x1088";
    Configuration.browser = "chrome";
    Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
}
}

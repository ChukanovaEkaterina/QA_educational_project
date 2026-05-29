import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class RegistrationTests extends TestBase {

    @Test
    void successfulRegistrationTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Chukanova");
        $("#userEmail").setValue("Chukanova@mail.ru");
        $("#gender-radio-2").click();
        $("#userNumber").setValue("1234512345");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").setValue("Art").pressEnter();
        $("#hobbies-checkbox-1").click();
        $("#hobbies-checkbox-3").click();
        $("#uploadPicture").uploadFromClasspath("photo.jpg");
        $("#currentAddress").setValue("Mira Street, 15");
        $("#react-select-3-input").setValue("RAJ").pressEnter();
        $("#react-select-4-input").setValue("Jaipur").pressEnter();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-hover").shouldHave(text("Katya Chukanova"));
        $(".table-hover").shouldHave(text("Chukanova@mail.ru"));
        $(".table-hover").shouldHave(text("Female"));
        $(".table-hover").shouldHave(text("1234512345"));
        $(".table-hover").shouldHave(text("01 June,1997"));
        $(".table-hover").shouldHave(text("Arts"));
        $(".table-hover").shouldHave(text("Sports, Music"));
        $(".table-hover").shouldHave(text("photo.jpg"));
        $(".table-hover").shouldHave(text("Mira Street, 15"));
        $(".table-hover").shouldHave(text("Rajasthan"));
        $(".table-hover").shouldHave(text("Jaipur"));
    }
}

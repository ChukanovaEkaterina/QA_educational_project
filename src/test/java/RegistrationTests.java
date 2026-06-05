import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationTests extends TestBase {

    @Test
    void successfulRegistrationTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Chukanova");
        $("#userEmail").setValue("Chukanova@mail.ru");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("1234512345");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").setValue("Art").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
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

    @Test
    void requiredFieldsRegistrationTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Chukanova");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("1234512345");
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-hover").shouldHave(text("Katya Chukanova"));
        $(".table-hover").shouldHave(text("Female"));
        $(".table-hover").shouldHave(text("1234512345"));
    }

    @Test
    void invalidEmailTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Chukanova");
        $("#userEmail").setValue("Chukanova");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("1234512345");
        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(".table-hover").shouldNotBe(visible);
    }

    @Test
    void invalidShortUserNumberTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Chukanova");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("111");
        $("#submit").click();

        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(".table-hover").shouldNotBe(visible);
    }

    @Test
    void noRequiredfieldFirstNameTest() {
        open("/automation-practice-form");

        $("#lastName").setValue("Chukanova");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("1234512345");
        $("#submit").click();

        $("#firstName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(".table-hover").shouldNotBe(visible);
    }

    @Test
    void allInputFieldsEmptyTest() {
        open("/automation-practice-form");

        $("#submit").click();

        $("#firstName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#lastName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[name=gender][value=Male]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[name=gender][value=Female]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[name=gender][value=Other]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(".table-hover").shouldNotBe(visible);
    }
}





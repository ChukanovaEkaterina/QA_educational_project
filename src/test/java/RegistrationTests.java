import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationTests extends TestBase {

    @CsvSource (value = {
            "Female, Sports",
            "Male, Reading",
            "Other, Music"
    })
    @ParameterizedTest(name = "Успешная регистрация с полом: {0} и хобби: {1} при вводе всех полей валидными данными")
    void successfulRegistrationTest(String genterWrapper, String hobbiesWrapper) {
        open("/automation-practice-form");

        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Chukanova");
        $("#userEmail").setValue("Chukanova@mail.ru");
        $("#genterWrapper").$(byText(genterWrapper)).click();
        $("#userNumber").setValue("1234512345");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").setValue("Art").pressEnter();
        $("#hobbiesWrapper").$(byText(hobbiesWrapper)).click();
        $("#uploadPicture").uploadFromClasspath("photo.jpg");
        $("#currentAddress").setValue("Mira Street, 15");
        $("#react-select-3-input").setValue("RAJ").pressEnter();
        $("#react-select-4-input").setValue("Jaipur").pressEnter();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Katya Chukanova"));
        $(".table-responsive").shouldHave(text("Chukanova@mail.ru"));
        $(".table-responsive").shouldHave(text(genterWrapper));
        $(".table-responsive").shouldHave(text("1234512345"));
        $(".table-responsive").shouldHave(text("01 June,1997"));
        $(".table-responsive").shouldHave(text("Arts"));
        $(".table-responsive").shouldHave(text(hobbiesWrapper));
        $(".table-responsive").shouldHave(text("photo.jpg"));
        $(".table-responsive").shouldHave(text("Mira Street, 15"));
        $(".table-responsive").shouldHave(text("Rajasthan"));
        $(".table-responsive").shouldHave(text("Jaipur"));
    }

    @Tag("Regression")
    @DisplayName("Успешная регистрация при вводе обязательных полей валидными данными")
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

    @Tag("Regression")
    @DisplayName("Отсутствует обязательное поле ввода email")
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

    @Tag("Regression")
    @ParameterizedTest(name= "Невалидное значение {0} поля ввода userNumber")
    @ValueSource (strings = {
            "null", "0", "bobobo", "111"
    })
    void invalidShortUserNumberTest(String invalidNumber) {
        open("/automation-practice-form");

        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Chukanova");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue(invalidNumber);
        $("#submit").click();

        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(".table-hover").shouldNotBe(visible);
    }

    @Tag("Regression")
    @DisplayName("Отсутствует обязательное поле ввода firstName")
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

    @Tag("Regression")
    @DisplayName("Отправка пустой формы регистрации")
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

    @Tag("Regression")
    @ParameterizedTest(name = "Привязка телефона {2} к имени {0} и фамилии {1} при регистрации")
    @CsvFileSource(
            resources = "/test_data/linkingUserNumberFirstNameLastName.csv"
    )

    void linkingUserNumberFirstNameLastName(String firstName, String lastName,
                                            String phoneNumber, String firstAndLastName) {
        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue(phoneNumber);
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-hover").shouldHave(text(firstAndLastName));
        $(".table-hover").shouldHave(text("Female"));
        $(".table-hover").shouldHave(text(phoneNumber));
    }
}





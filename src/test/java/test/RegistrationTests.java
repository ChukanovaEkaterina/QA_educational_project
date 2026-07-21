package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testdata.TestData.*;

public class RegistrationTests extends TestBase {



    @Test
    @DisplayName("Успешная регистрация при вводе всех полей валидными данными")
    void successfulRegistrationTest() {
        registrationTestPage
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(userEmail)
                .setGender(genderWrapper)
                .typeUserNumber(userNumber)
                .setDateOfBirth(BirthDay, BirthMonth, BirthYear)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .pictureUpload(picture)
                .typeCurrentAddress(currentAddress)
                .setStateAndCity(studentState, studentCity)
                .Submit();

        $("#example-modal-sizes-title-lg").shouldHave(text(textSuccessfulRegistrationForm));
        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text(genderWrapper));
        $(".table-responsive").shouldHave(text(userNumber));
        $(".table-responsive").shouldHave(text(BirthDay + " " + BirthMonth + "," + BirthYear));
        $(".table-responsive").shouldHave(text(subjects));
        $(".table-responsive").shouldHave(text(hobbies));
        $(".table-responsive").shouldHave(text(picture));
        $(".table-responsive").shouldHave(text(currentAddress));
        $(".table-responsive").shouldHave(text(studentState));
        $(".table-responsive").shouldHave(text(studentCity));
    }

    @Tag("Regression")
    @DisplayName("Успешная регистрация при вводе обязательных полей валидными данными")
    @Test
    void requiredFieldsRegistrationTest() {
        registrationTestPage
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName);
        $("#genterWrapper").$(byText(genderWrapper)).click();
        $("#userNumber").setValue(userNumber);
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text(textSuccessfulRegistrationForm));
        $(".table-hover").shouldHave(text(firstName + " " + lastName));
        $(".table-hover").shouldHave(text(genderWrapper));
        $(".table-hover").shouldHave(text(userNumber));
    }

    @Tag("Regression")
    @DisplayName("Невалидные данные в  поле ввода email")
    @Test
    void invalidEmailTest() {
        registrationTestPage
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(lastName);
        $("#genterWrapper").$(byText(genderWrapper)).click();
        $("#userNumber").setValue(userNumber);
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
        registrationTestPage
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName);
        $("#genterWrapper").$(byText(genderWrapper)).click();
        $("#userNumber").setValue(invalidNumber);
        $("#submit").click();

        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(".table-hover").shouldNotBe(visible);
    }

    @Tag("Regression")
    @DisplayName("Отсутствует обязательное поле ввода firstName")
    @Test
    void noRequiredfieldFirstNameTest() {
        registrationTestPage
                .openPage()
                .typeLastName(lastName);;
        $("#genterWrapper").$(byText(genderWrapper)).click();
        $("#userNumber").setValue(userNumber);
        $("#submit").click();

        $("#firstName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(".table-hover").shouldNotBe(visible);
    }

    @Tag("Regression")
    @DisplayName("Отправка пустой формы регистрации")
    @Test
    void allInputFieldsEmptyTest() {
        registrationTestPage
                .openPage()
                .Submit();

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
        $("#genterWrapper").$(byText(genderWrapper)).click();
        $("#userNumber").setValue(phoneNumber);
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text(textSuccessfulRegistrationForm));
        $(".table-hover").shouldHave(text(firstAndLastName));
        $(".table-hover").shouldHave(text(genderWrapper));
        $(".table-hover").shouldHave(text(phoneNumber));
    }
}





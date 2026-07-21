package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

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
                .submit()

                .chekFormModal(textSuccessfulRegistrationForm)
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", genderWrapper)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", BirthDay + " " + BirthMonth + "," + BirthYear)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", picture)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", studentState + " " + studentCity);
    }

    @Tag("Regression")
    @DisplayName("Успешная регистрация при вводе обязательных полей валидными данными")
    @Test
    void requiredFieldsRegistrationTest() {
        registrationTestPage
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .setGender(genderWrapper)
                .typeUserNumber(userNumber)
                .submit()

                .chekFormModal(textSuccessfulRegistrationForm)
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", genderWrapper)
                .checkResult("Mobile", userNumber);
    }

    @Tag("Regression")
    @DisplayName("Невалидные данные в  поле ввода email")
    @Test
    void invalidEmailTest() {
        registrationTestPage
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(lastName)
                .setGender(genderWrapper)
                .typeUserNumber(userNumber)
                .submit()

                .checkErrorUserEmail()
                .notVisibleTableHover();
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
                .typeLastName(lastName)
                .setGender(genderWrapper)
                .typeUserNumber(invalidNumber)
                .submit()

                .checkErrorUserNumber()
                .notVisibleTableHover();
    }

    @Tag("Regression")
    @DisplayName("Отсутствует обязательное поле ввода firstName")
    @Test
    void noRequiredfieldFirstNameTest() {
        registrationTestPage
                .openPage()
                .typeLastName(lastName)
                .setGender(genderWrapper)
                .typeUserNumber(userNumber)
                .submit()

                .checkErrorFirstName()
                .notVisibleTableHover();
    }

    @Tag("Regression")
    @DisplayName("Отправка пустой формы регистрации")
    @Test
    void allInputFieldsEmptyTest() {
        registrationTestPage
                .openPage()
                .submit()

                .checkErrorFirstName()
                .checkErrorLastName()
                .checkErrorUserNumber()
                .notVisibleTableHover();

//$("[name=gender][value=Male]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));//$("[name=gender][value=Female]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
//$("[name=gender][value=Other]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

    }

    @Tag("Regression")
    @ParameterizedTest(name = "Привязка телефона {2} к имени {0} и фамилии {1} при регистрации")
    @CsvFileSource(
            resources = "/test_data/linkingUserNumberFirstNameLastName.csv"
    )

    void linkingUserNumberFirstNameLastName(String firstName, String lastName,
                                            String phoneNumber, String firstAndLastName) {
        registrationTestPage
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .setGender(genderWrapper)
                .typeUserNumber(phoneNumber)
                .submit()

                .chekFormModal(textSuccessfulRegistrationForm)
                .checkResult("Student Name", firstAndLastName)
                .checkResult("Gender", genderWrapper)
                .checkResult("Mobile", phoneNumber);
    }
}





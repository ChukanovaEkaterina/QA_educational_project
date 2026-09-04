package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import testdata.TestData;

import static com.codeborne.selenide.logevents.SelenideLogger.step;


public class RegistrationTests extends TestBase {

    TestData testData = new TestData();

    @Test
    @DisplayName("Успешная регистрация при вводе всех полей валидными данными")
    void successfulRegistrationTest() {

        step("Open Student Registration page", () -> {
            registrationTestPage.openPage();
        });

        step("Fill registration form", () -> {
            registrationTestPage
                    .scrollHeight()
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName)
                    .typeUserEmail(testData.userEmail)
                    .setGender(testData.genderWrapper)
                    .typeUserNumber(testData.userNumber)
                    .setDateOfBirth(testData.BirthDay, testData.BirthMonth, testData.BirthYear)
                    .setSubjects(testData.subjects)
                    .setHobbies(testData.hobbies)
                    .pictureUpload(testData.picture)
                    .typeCurrentAddress(testData.currentAddress)
                    .setStateAndCity(testData.state, testData.city)
                    .submit();
        });

        step("Check registration form results data", () -> {
            registrationTestPage
                    .chekFormModal(testData.textSuccessfulRegistrationForm)
                    .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResult("Student Email", testData.userEmail)
                    .checkResult("Gender", testData.genderWrapper)
                    .checkResult("Mobile", testData.userNumber)
                    .checkResult("Date of Birth", testData.BirthDay + " " + testData.BirthMonth + "," + testData.BirthYear)
                    .checkResult("Subjects", testData.subjects)
                    .checkResult("Hobbies", testData.hobbies)
                    .checkResult("Picture", testData.picture)
                    .checkResult("Address", testData.currentAddress)
                    .checkResult("State and City", testData.state + " " + testData.city);
        });
    }

    @Tag("Regression")
    @DisplayName("Успешная регистрация при вводе обязательных полей валидными данными")
    @Test
    void requiredFieldsRegistrationTest() {
        step("Open Student Registration page", () -> {
            registrationTestPage.openPage();
        });

        step("Fill registration form", () -> {
            registrationTestPage
                    .scrollHeight()
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName)
                    .setGender(testData.genderWrapper)
                    .typeUserNumber(testData.userNumber)
                    .submit();
        });

        step("Check registration form results data", () -> {
            registrationTestPage
                    .chekFormModal(testData.textSuccessfulRegistrationForm)
                    .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResult("Gender", testData.genderWrapper)
                    .checkResult("Mobile", testData.userNumber);
        });
    }

    @Tag("Regression")
    @DisplayName("Невалидные данные в  поле ввода email")
    @Test
    void invalidEmailTest() {
        step("Open Student Registration page", () -> {
            registrationTestPage.openPage();
        });

        step("Fill registration data with invalid Email", () -> {
            registrationTestPage
                    .scrollHeight()
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName)
                    .typeUserEmail(testData.lastName)
                    .setGender(testData.genderWrapper)
                    .typeUserNumber(testData.userNumber)
                    .submit();
        });

        step("Check registration results data", () -> {
            registrationTestPage
                    .checkErrorUserEmail()
                    .notVisibleTableHover();
        });
    }

    @Tag("Regression")
    @ParameterizedTest(name= "Невалидное значение {0} поля ввода userNumber")
    @ValueSource (strings = {
            "null", "0", "bobobo", "111"
    })
    void invalidShortUserNumberTest(String invalidNumber) {
        step("Open Student Registration page", () -> {
            registrationTestPage.openPage();
        });

        step("Fill registration data with invalid userNumber", () -> {
            registrationTestPage
                    .scrollHeight()
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName)
                    .setGender(testData.genderWrapper)
                    .typeUserNumber(invalidNumber)
                    .submit();
        });

        step("Check registration results data", () -> {
            registrationTestPage
                    .checkErrorUserNumber()
                    .notVisibleTableHover();
        });
    }

    @Tag("Regression")
    @DisplayName("Отсутствует обязательное поле ввода firstName")
    @Test
    void noRequiredfieldFirstNameTest() {
        step("Open Student Registration page", () -> {
            registrationTestPage.openPage();
        });

        step("Fill registration data without invalid firstName", () -> {
            registrationTestPage
                    .scrollHeight()
                    .typeLastName(testData.lastName)
                    .setGender(testData.genderWrapper)
                    .typeUserNumber(testData.userNumber)
                    .submit();
        });

        step("Check registration results data", () -> {
            registrationTestPage
                    .checkErrorFirstName()
                    .notVisibleTableHover();
        });
    }

    @Tag("Regression")
    @DisplayName("Отправка пустой формы регистрации")
    @Test
    void allInputFieldsEmptyTest() {
        step("Open Student Registration page", () -> {
            registrationTestPage.openPage();
        });

        step("Fill registration data with all input fields empty", () -> {
            registrationTestPage
                    .scrollHeight()
                    .submit();
        });

        step("Check registration results data", () -> {
            registrationTestPage
                    .checkErrorFirstName()
                    .checkErrorLastName()
                    .checkErrorUserNumber()
                    .notVisibleTableHover();
        });
    }

    @Tag("Regression")
    @ParameterizedTest(name = "Привязка телефона {2} к имени {0} и фамилии {1} при регистрации")
    @CsvFileSource(
            resources = "/test_data/linkingUserNumberFirstNameLastName.csv"
    )

    void linkingUserNumberFirstNameLastName(String firstName, String lastName,
                                            String phoneNumber, String firstAndLastName) {

        step("Open Student Registration page", () -> {
            registrationTestPage.openPage();
        });

        step("Fill registration data with link Number, FirstName and LastName", () -> {
            registrationTestPage
                    .scrollHeight()
                    .typeFirstName(firstName)
                    .typeLastName(lastName)
                    .setGender(testData.genderWrapper)
                    .typeUserNumber(phoneNumber)
                    .submit();
        });

        step("Check registration results data", () -> {
            registrationTestPage
                    .chekFormModal(testData.textSuccessfulRegistrationForm)
                    .checkResult("Student Name", firstAndLastName)
                    .checkResult("Gender", testData.genderWrapper)
                    .checkResult("Mobile", phoneNumber);
        });
    }
}





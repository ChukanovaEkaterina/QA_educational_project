package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import testdata.TestData;


public class RegistrationTests extends TestBase {

    TestData testData = new TestData();

    @Test
    @DisplayName("Успешная регистрация при вводе всех полей валидными данными")
    void successfulRegistrationTest() {

        TestData testData = new TestData();

        registrationTestPage
                .openPage()
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
                .submit()

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
    }

    @Tag("Regression")
    @DisplayName("Успешная регистрация при вводе обязательных полей валидными данными")
    @Test
    void requiredFieldsRegistrationTest() {
        registrationTestPage
                .openPage()
                .scrollHeight()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .setGender(testData.genderWrapper)
                .typeUserNumber(testData.userNumber)
                .submit()

                .chekFormModal(testData.textSuccessfulRegistrationForm)
                .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Gender", testData.genderWrapper)
                .checkResult("Mobile", testData.userNumber);
    }

    @Tag("Regression")
    @DisplayName("Невалидные данные в  поле ввода email")
    @Test
    void invalidEmailTest() {
        registrationTestPage
                .openPage()
                .scrollHeight()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeUserEmail(testData.lastName)
                .setGender(testData.genderWrapper)
                .typeUserNumber(testData.userNumber)
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
                .scrollHeight()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .setGender(testData.genderWrapper)
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
                .scrollHeight()
                .typeLastName(testData.lastName)
                .setGender(testData.genderWrapper)
                .typeUserNumber(testData.userNumber)
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
                .scrollHeight()
                .submit()

                .checkErrorFirstName()
                .checkErrorLastName()
                .checkErrorUserNumber()
                .notVisibleTableHover();
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
                .scrollHeight()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .setGender(testData.genderWrapper)
                .typeUserNumber(phoneNumber)
                .submit()

                .chekFormModal(testData.textSuccessfulRegistrationForm)
                .checkResult("Student Name", firstAndLastName)
                .checkResult("Gender", testData.genderWrapper)
                .checkResult("Mobile", phoneNumber);
    }
}





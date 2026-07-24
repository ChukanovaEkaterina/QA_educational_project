package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultFormComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTestPage {

    CalendarComponent calendar = new CalendarComponent();
    ResultFormComponent resultFormComponent = new ResultFormComponent();

   private final SelenideElement firstNameInput = $("#firstName");
   private final SelenideElement lastNameInput = $("#lastName");
   private final SelenideElement userEmailInput = $("#userEmail");
   private final SelenideElement genderWrapperInput = $("#genterWrapper");
   private final SelenideElement userNumberInput = $("#userNumber");
   private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
   private final SelenideElement subjectsInput = $("#subjectsInput");
   private final SelenideElement hobbiesWrapperInput = $("#hobbiesWrapper");
   private final SelenideElement pictureUploadInput = $("#uploadPicture");
   private final SelenideElement currentAddressInput = $("#currentAddress");
   private final SelenideElement stateInput = $("#state");
   private final SelenideElement cityInput = $("#city");
   private final SelenideElement stateCityWrapper = $("#stateCity-wrapper");
   private final SelenideElement submitButton = $("#submit");
   private final SelenideElement formModal = $("#example-modal-sizes-title-lg");
   private final SelenideElement tableHover = $(".table-hover");

   public RegistrationTestPage openPage(){
       open("/automation-practice-form");
       return this;
   }

    public RegistrationTestPage scrollHeight() {
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
        return this;
    }

   public RegistrationTestPage typeFirstName(String value){
        firstNameInput.setValue(value);
        return this;
   }

   public RegistrationTestPage typeLastName(String value){
        lastNameInput.setValue(value);
        return this;
   }

   public RegistrationTestPage typeUserEmail(String value){
        userEmailInput.setValue(value);
        return this;
   }

   public RegistrationTestPage setGender(String value){
       genderWrapperInput.$(byText(value)).click();
        return this;
   }


   public RegistrationTestPage typeUserNumber(String value){
        userNumberInput.setValue(value);
        return this;
   }

   public RegistrationTestPage setDateOfBirth(String day, String month, String year){
        dateOfBirthInput.click();
        calendar.setDate(day, month, year);
        return this;
   }

   public RegistrationTestPage setSubjects(String value){
       subjectsInput.setValue(value).pressEnter();
       return this;
   }

    public RegistrationTestPage setHobbies(String value){
        hobbiesWrapperInput.$(byText(value)).click();
        return this;
    }

    public RegistrationTestPage pictureUpload(String value){
        pictureUploadInput.uploadFromClasspath(value);
        return this;
    }

    public RegistrationTestPage typeCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public RegistrationTestPage setState(String value) {
        stateInput.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationTestPage setCity(String value) {
        cityInput.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationTestPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);
        return this;
    }

   public RegistrationTestPage submit(){
       submitButton.click();
       return this;
   }

    public RegistrationTestPage chekFormModal(String value) {
        formModal.shouldBe(visible);
        formModal.shouldHave(text(value));
        return this;
    }

    public RegistrationTestPage checkResult(String key, String value){
        resultFormComponent.chekField(key,value);
        return this;
    }

    public RegistrationTestPage notVisibleTableHover(){
        tableHover.shouldNotBe(visible);
        return this;
    }

    public RegistrationTestPage checkErrorUserNumber(){
        userNumberInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        return this;
    }

    public RegistrationTestPage checkErrorUserEmail(){
        userEmailInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        return this;
    }

    public RegistrationTestPage checkErrorFirstName(){
        firstNameInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        return this;
    }

    public RegistrationTestPage checkErrorLastName(){
        lastNameInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        return this;
    }
}

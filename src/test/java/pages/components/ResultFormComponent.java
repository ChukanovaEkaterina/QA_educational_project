package pages.components;

import com.codeborne.selenide.SelenideElement;
import pages.RegistrationTestPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultFormComponent {


    private final SelenideElement resultForm = $(".table-responsive");

    public ResultFormComponent chekField(String key, String value){
        resultForm.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }
}

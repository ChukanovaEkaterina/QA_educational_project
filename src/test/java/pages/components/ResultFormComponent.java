package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultFormComponent {

    private final SelenideElement resultForm = $(".table-responsive");

    @Step("Chek Field in result form")
    public ResultFormComponent chekField(String key, String value){
        resultForm.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }
}

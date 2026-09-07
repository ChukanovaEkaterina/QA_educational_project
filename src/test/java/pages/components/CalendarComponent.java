package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class CalendarComponent {

    private final SelenideElement monthSelect = $(".react-datepicker__month-select");
    private final SelenideElement yearSelect = $(".react-datepicker__year-select");
    private final SelenideElement dateSelect = $(".react-datepicker__month");


    public void setDate(String day, String month, String year){
    monthSelect.$(byText(month)).click();
    yearSelect.$(byText(year)).click();
    dateSelect.$(byText(day)).click();
}
}

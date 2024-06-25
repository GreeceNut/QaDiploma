package Pays;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditCard {

    private final SelenideElement headerCredit = $$("h3")
            .find(text("Кредит по данным карты"));
    private SelenideElement form = $(".input");
    private SelenideElement continueButton = $(".button")
            .shouldHave(text(" Продолжить"));
    private SelenideElement notificationOk = $(".notification_status_ok");
    private SelenideElement notificationError = $(".notification_status_error");

    public CreditCard() {
        headerCredit.shouldBe(visible);
    }


}

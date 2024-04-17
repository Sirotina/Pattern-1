
import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
 public class PatternTest {

     private Faker faker;

     @BeforeEach
     void setUp(){
         open("http://localhost:9999");
     }
     @Test
     @DisplayName("Should successful plan meeting")
     void validForm() {
         var validUser = generateDate.Registration.generateUser("ru");
         var daysToAddForFirstMeeting = 4;
         var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
         var daysToAddForSecondMeeting = 7;
         var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
         $("[data-test-id='city'] input").val(validUser.getCity());
         $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
         $("[data-test-id='date'] input").val(firstMeetingDate);
         $("[data-test-id='name'] input").val(validUser.getName());
         $("[data-test-id='phone'] input").val(validUser.getPhone());
         $("[data-test-id='agreement']").click();
         $(".button").click();
         $("[data-test-id='success-notification'] .notification__title").shouldBe(visible).shouldBe(exactText("Успешно!"));
         $("[data-test-id='success-notification'] .notification__content").shouldBe(visible).shouldBe(exactText("Встреча успешно запланирована на " + firstMeetingDate));
         $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
         $("[data-test-id='date'] input").val(secondMeetingDate);
         $(".button").click();
         $("[data-test-id='replan-notification'] .notification__title").shouldBe(visible).shouldBe(exactText("Необходимо подтверждение"));
         $("[data-test-id='replan-notification'] .notification__content").shouldBe(visible).shouldBe(exactText("У вас уже запланирована встреча на другую дату. Перепланировать? Перепланировать"));
         $("[data-test-id='replan-notification'] .button").click();
         $("[data-test-id='success-notification'] .notification__title").shouldBe(visible).shouldBe(exactText("Успешно!"));
         $("[data-test-id='success-notification'] .notification__content").shouldBe(visible).shouldBe(exactText("Встреча успешно запланирована на " + secondMeetingDate));
     }
     @Test
     void invalidName() {
         var validUser = DataGenerator.Registration.generateUser("ru");
         var daysToAddForFirstMeeting = 4;
         var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
         var daysToAddForSecondMeeting = 7;
         var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
         $("[data-test-id='city'] input").val(validUser.getCity());
         $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
         $("[data-test-id='date'] input").val(firstMeetingDate);
         $("[data-test-id='phone'] input").val(validUser.getPhone());
         $("[data-test-id='agreement']").click();
         $(".button").click();
         $("[data-test-id='name'] .input__inner .input__sub")
                 .shouldBe(visible)
                 .shouldHave(text("Поле обязательно для заполнения"));
     }

     @Test
     void invalidCity() {
         var validUser = DataGenerator.Registration.generateUser("ru");
         var daysToAddForFirstMeeting = 4;
         var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
         var daysToAddForSecondMeeting = 7;
         var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
         $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
         $("[data-test-id='date'] input").val(firstMeetingDate);
         $("[data-test-id='name'] input").val(validUser.getName());
         $("[data-test-id='phone'] input").val(validUser.getPhone());
         $("[data-test-id='agreement']").click();
         $(".button").click();
         $("[data-test-id='city'] .input__inner .input__sub")
                 .shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
     }
     @Test
     void cityEnglish() {
         var validUser = DataGenerator.Registration.generateUser("ru");
         var daysToAddForFirstMeeting = 4;
         var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
         var daysToAddForSecondMeeting = 7;
         var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
         $("[data-test-id='city'] input").setValue("Kemerovo");
         $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
         $("[data-test-id='date'] input").val(firstMeetingDate);
         $("[data-test-id='name'] input").val(validUser.getName());
         $("[data-test-id='phone'] input").val(validUser.getPhone());
         $("[data-test-id='agreement']").click();
         $(".button").click();
         $("[data-test-id='city'] .input__inner .input__sub")
                 .shouldBe(visible)
                 .shouldHave(text("Доставка в выбранный город недоступна"));
     }
     @Test
     void invalidDate() {
         var validUser = DataGenerator.Registration.generateUser("ru");
         var daysToAddForFirstMeeting = 4;
         var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
         var daysToAddForSecondMeeting = 7;
         var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
         $("[data-test-id='city'] input").val(validUser.getCity());
         $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
         $("[data-test-id='name'] input").val(validUser.getName());
         $("[data-test-id='phone'] input").val(validUser.getPhone());
         $("[data-test-id='agreement']").click();
         $(".button").click();
         $("[data-test-id='date'] .input__inner .input__sub")
                 .shouldBe(visible)
                 .shouldHave(text("Неверно введена дата"));
     }

     @Test
     void minusDay() {
         var validUser = DataGenerator.Registration.generateUser("ru");
         var daysToAddForFirstMeeting = -4;
         var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
         var daysToAddForSecondMeeting = 7;
         var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
         $("[data-test-id='city'] input").val(validUser.getCity());
         $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
         $("[data-test-id='date'] input").val(firstMeetingDate);
         $("[data-test-id='name'] input").val(validUser.getName());
         $("[data-test-id='phone'] input").val(validUser.getPhone());
         $("[data-test-id='agreement']").click();
         $(".button").click();
         $("[data-test-id='date'] .input__inner .input__sub")
                 .shouldBe(visible).shouldHave(text("Заказ на выбранную дату невозможен"));
     }
     @Test
     void invalidPhone() {
         var validUser = DataGenerator.Registration.generateUser("ru");
         var daysToAddForFirstMeeting = 4;
         var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
         var daysToAddForSecondMeeting = 7;
         var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
         $("[data-test-id='city'] input").val(validUser.getCity());
         $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
         $("[data-test-id='date'] input").val(firstMeetingDate);
         $("[data-test-id='name'] input").val(validUser.getName());
         $("[data-test-id='agreement']").click();
         $(".button").click();
         $("[data-test-id='phone'] .input__inner .input__sub")
                 .shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
     }

     @Test
     @DisplayName("Should stop because of out of checkbox")
     void invalidCheckbox() {
         var validUser = DataGenerator.Registration.generateUser("ru");
         var daysToAddForFirstMeeting = 4;
         var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
         var daysToAddForSecondMeeting = 7;
         var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
         $("[data-test-id='city'] input").val(validUser.getCity());
         $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
         $("[data-test-id='name'] input").val(validUser.getName());
         $("[data-test-id='date'] input").val(firstMeetingDate);
         $(".button").click();
         $("[data-test-id='agreement'] .checkbox__text")
                 .shouldBe(visible)
                 .shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"), Condition.cssValue("user-select", "none"));
     }
 }


}
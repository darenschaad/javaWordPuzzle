import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Enter a phrase to turn it into a word puzzle");
  }
  @Test
  public void guessTest() {
      goTo("http://localhost:4567/");
      fill("#inputSentence").with("Hello World!");
      submit("#home");
      assertThat(pageSource()).contains("Guess the phrase:");
  }
  @Test
  public void guessWrongTest() {
      goTo("http://localhost:4567/");
      fill("#inputSentence").with("Hello World!");
      submit("#home");
      fill("#guessPhrase").with("Hello");
      submit("#puzzle");
      assertThat(pageSource()).contains("Sorry");
  }

  @Test
  public void replaceVowels_givenAPhrase_removeVowels() {
    App testApp = new App();
    assertEquals("H_ll_ W_rld", testApp.replaceVowels("Hello World"));
  }

}

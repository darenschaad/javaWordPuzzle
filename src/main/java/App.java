import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {

  }

  public static String replaceVowels(String inputSentence) {
    String wordPuzzle = inputSentence.replaceAll("[aeiouAEIOU]", "_");
    return wordPuzzle;
  }
}

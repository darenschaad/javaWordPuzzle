import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    String form = "templates/home.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", form);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/puzzle", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String inputSentence = request.queryParams("inputSentence");
      request.session().attribute("inputSentence", inputSentence);
      String wordPuzzle = replaceVowels(inputSentence);
      request.session().attribute("wordPuzzle", wordPuzzle);
      model.put("wordPuzzle", wordPuzzle);
      model.put("template", "templates/puzzle.vtl");
      model.put("form", form);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/guess", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      // String inputSentence = request.queryParams("inputSentence");
      String guessPhrase = request.queryParams("guessPhrase");
      String guess = checkGuess(guessPhrase, request.session().attribute("inputSentence"));
      model.put("wordPuzzle", request.session().attribute("wordPuzzle"));
      model.put("win", request.session().attribute("inputSentence"));
      model.put("guess", guess);
      model.put("template", "templates/guess.vtl");
      model.put("form", form);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

  public static String replaceVowels(String inputSentence) {
    String wordPuzzle = inputSentence.replaceAll("[aeiouAEIOU]", "_");
    return wordPuzzle;
  }
  public static String checkGuess(String guessPhrase, String inputSentence) {
    if (guessPhrase.equals(inputSentence)){
      return "Yay, you guessed it!";
    }
    else {
      return "Sorry, that is not quite right. Make sure to check your capitalization and punctuation too";
    }
  }
}

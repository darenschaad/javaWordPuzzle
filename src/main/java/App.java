import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
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
      String wordPuzzle = replaceVowels(inputSentence);
      model.put("wordPuzzle", wordPuzzle);
      model.put("template", "templates/puzzle.vtl");
      model.put("form", form);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

  public static String replaceVowels(String inputSentence) {
    String wordPuzzle = inputSentence.replaceAll("[aeiouAEIOU]", "_");
    return wordPuzzle;
  }
}

import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout)
    }, new VelocityTemplateEngine())
  }

  public static String replaceVowels(String inputSentence) {
    String wordPuzzle = inputSentence.replaceAll("[aeiouAEIOU]", "_");
    return wordPuzzle;
  }
}

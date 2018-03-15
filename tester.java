import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import com.google.gson.*;
import java.util.List;
import java.util.Scanner;

public class tester {

  public static void main(String[] arg) {

    ClarifaiClient client = new ClarifaiBuilder("ecd13c5a28c14a18b3f72450f5e8e0ce").buildSync();

    final List<ClarifaiOutput<Concept>> predictionResults =
        client
            .getDefaultModels()
            .generalModel() // You can also do client.getModelByID("id") to get your custom models
            .predict()
            .withInputs(ClarifaiInput.forImage("https://samples.clarifai.com/metro-north.jpg"))
            .executeSync()
            .get();
    Scanner sc =
        new Scanner(new GsonBuilder().setPrettyPrinting().create().toJson(predictionResults));
    for (int i = 0; i < 24; i++) {
      sc.nextLine();
    }
    while (sc.hasNextLine()) {
      String name = sc.nextLine();
      if (!name.contains("}")) {
        name = name.substring(0, name.length() - 1);
        System.out.println(name.trim());
      }
      if (sc.hasNextLine()) {
        sc.nextLine();
      }
      if (sc.hasNextLine()) {
        String value = sc.nextLine();
        value = value.trim();
        value = value.substring(9, value.length());
        System.out.println(value.trim());
      }
      System.out.println();
      for (int i = 0; i < 3; i++) {
        if (sc.hasNextLine()) {
          sc.nextLine();
        }
      }
    }
  }
}

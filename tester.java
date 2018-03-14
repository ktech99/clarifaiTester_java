import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import com.google.gson.*;
import java.util.List;

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
    System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(predictionResults));
  }
}

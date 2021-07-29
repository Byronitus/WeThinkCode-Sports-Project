
import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.League;
import com.example.springbootdemomaven.ListOfClasses.ListOfLeagues;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;

public class ListOfLeaguesTest {
    public Database database;
    public MongoDatabase mongoDatabase;

    @Test
    void TestAPIIntegration() {
        int count = 0;
        try {
            FileReader fileReader = new FileReader("src\\\\test\\\\java\\\\JSONTestFiles\\\\Leagues.json");
            JsonObject jsonObject = new Gson().fromJson(fileReader, JsonObject.class); //Creating json object
            JsonArray jsonArray = (JsonArray) jsonObject.get("countrys");
            ListOfLeagues listOfLeagues = new ListOfLeagues("Motorsport", this.mongoDatabase);
            listOfLeagues.listLeagues("file:///" + (new File("Leagues.json").getAbsolutePath())
                    .replaceAll("Leagues.json", "src\\\\test\\\\java\\\\JSONTestFiles\\\\Leagues.json")); //Sending the url of the json file
            for (League league : listOfLeagues.getListOfLeagues()) { //Iterating through the list of classes and comparing it to the json file
                JsonObject jsonLeagues = (JsonObject) jsonArray.get(count);
                Assertions.assertEquals(league.getStrLeague(), jsonLeagues.get("strLeague")
                        .toString().replaceAll("\"", ""));
                count = count + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import com.example.springbootdemomaven.Classes.Team;
import com.example.springbootdemomaven.ListOfClasses.ListOfTeams;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;

public class ListOfTeamsTest {
    public MongoDatabase mongoDatabase;

    @Test
    void TestAPIIntegration() {
        int count = 0;
        try {
            FileReader fileReader = new FileReader("src\\\\test\\\\java\\\\JSONTestFiles\\\\Teams.json");
            JsonObject jsonObject = new Gson().fromJson(fileReader, JsonObject.class); //Creating json object
            JsonArray jsonArray = (JsonArray) jsonObject.get("teams");
            ListOfTeams listOfTeams = new ListOfTeams("4370", this.mongoDatabase);
            listOfTeams.APIListTeams("file:///" + (new File("Leagues.json").getAbsolutePath())
                    .replaceAll("Leagues.json", "src\\\\test\\\\java\\\\JSONTestFiles\\\\Teams.json")); //Sending the url of the json file
            for (Team team : listOfTeams.getListOfTeams()) { //Iterating through the list of classes and comparing it to the json file

                JsonObject jsonTeams = (JsonObject) jsonArray.get(count);

                Assertions.assertEquals(team.getStrTeam(), jsonTeams.get("strTeam")
                        .toString().replaceAll("\"", ""));

                Assertions.assertEquals(team.getStrSport(), jsonTeams.get("strSport")
                        .toString().replaceAll("\"", ""));

                Assertions.assertEquals(team.getIdTeam(), jsonTeams.get("idTeam")
                        .toString().replaceAll("\"", ""));

                count = count + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

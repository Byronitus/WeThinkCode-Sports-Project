import com.example.springbootdemomaven.Classes.Season;
import com.example.springbootdemomaven.ListOfClasses.ListOfSeasons;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileReader;

public class ListOfSeasonsTest {

    @Test
    void TestAPIIntegration(){
        int count = 0;
        try {
            FileReader fileReader = new FileReader("src\\\\test\\\\java\\\\JSONTestFiles\\\\Seasons.json");
            JsonObject jsonObject = new Gson().fromJson(fileReader, JsonObject.class); //Creating json object
            JsonArray jsonArray = (JsonArray) jsonObject.get("seasons");
            ListOfSeasons listOfSeasons = new ListOfSeasons();
            listOfSeasons.APIListSeason("file:///"+(new File("Leagues.json").getAbsolutePath())
                    .replaceAll("Leagues.json","src\\\\test\\\\java\\\\JSONTestFiles\\\\Seasons.json")); //Sending the url of the json file

            for (Season season:listOfSeasons.getListOfSeasons()) { //Iterating through the list of classes and comparing it to the json file
                JsonObject jsonSeasons = (JsonObject) jsonArray.get(count);
                Assertions.assertEquals(season.getStrSeason(), jsonSeasons.get("strSeason")
                        .toString().replaceAll("\"",""));

                count = count + 1;
            }
        }catch(Exception e){e.printStackTrace();}
    }
}

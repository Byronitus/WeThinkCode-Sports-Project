
import Classes.Database;
import Classes.Sport;
import ListOfClasses.ListOfSports;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;

public class ListOfSportsTest {
    Database database;


    @Test
    void TestAPIIntegration(){
        int count = 0;
        try {
            FileReader fileReader = new FileReader("src\\\\test\\\\java\\\\JSONTestFiles\\\\Sports.json");
            JsonObject jsonObject = new Gson().fromJson(fileReader, JsonObject.class); //Creating json object
            JsonArray jsonArray = (JsonArray) jsonObject.get("sports");
            ListOfSports listOfSports = new ListOfSports(this.database);
            listOfSports.APIListSports("file:///"+(new File("Leagues.json").getAbsolutePath())
                    .replaceAll("Leagues.json","src\\\\test\\\\java\\\\JSONTestFiles\\\\Sports.json")); //Sending the url of the json file
            for (Sport sport:listOfSports.getListOfSports()) { //Iterating through the list of classes and comparing it to the json file

                JsonObject jsonSports = (JsonObject) jsonArray.get(count);

                Assertions.assertEquals(sport.getIdSport(), jsonSports.get("idSport")
                        .toString().replaceAll("\"",""));

                Assertions.assertEquals(sport.getStrSport(), jsonSports.get("strSport")
                        .toString().replaceAll("\"",""));

                count = count + 1;
            }
        }catch(Exception e){e.printStackTrace();}
    }
}

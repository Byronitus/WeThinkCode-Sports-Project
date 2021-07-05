
import com.example.springbootdemomaven.Classes.Event;
import com.example.springbootdemomaven.ListOfClasses.ListOfEvents;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileReader;

public class ListOfEventsTest {


    @Test
    void TestAPIIntegration(){
        int count = 0;
        try {
            FileReader fileReader = new FileReader("src\\\\test\\\\java\\\\JSONTestFiles\\\\Events.json");
            JsonObject jsonObject = new Gson().fromJson(fileReader, JsonObject.class); //Creating json object
            JsonArray jsonArray = (JsonArray) jsonObject.get("events");
            ListOfEvents listOfEvents = new ListOfEvents();
            listOfEvents.APIListEvents("file:///"+(new File("Leagues.json").getAbsolutePath())
                    .replaceAll("Leagues.json","src\\\\test\\\\java\\\\JSONTestFiles\\\\Events.json")); //Sending the url of the json file
            for (Event event:listOfEvents.getListOfEvent()) { //Iterating through the list of classes and comparing it to the json file

                JsonObject jsonEvents = (JsonObject) jsonArray.get(count);

                Assertions.assertEquals(event.getStrEvent(), jsonEvents.get("strEvent")
                        .toString().replaceAll("\"",""));

                Assertions.assertEquals(event.getIdEvent(), jsonEvents.get("idEvent")
                        .toString().replaceAll("\"",""));

                count = count + 1;
            }
        }catch(Exception e){e.printStackTrace();}
    }
}

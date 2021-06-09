package ListOfClasses;

import Classes.Event;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfEvents {
    public ArrayList<Event> ListOfEvent = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/1/eventsseason.php?id=";
    public Gson gson = new Gson();

    public String createURLString(String LeagueID, String SeasonName){
        return this.API+LeagueID+"&s="+SeasonName;
    }

    public void APIListEvents(String url){
        try
        {
            Scanner scanner = new Scanner(new URL(url).openStream(),
                    StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            JsonObject jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
            addToArrayList(jsonObject);
        }catch (Exception e){e.printStackTrace();}
    }

    public void addToArrayList(JsonObject jsonObject){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("events");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject eventJson = (JsonObject) jsonArray.get(i);
                Event event = this.gson.fromJson(eventJson, Event.class);
                this.ListOfEvent.add(event);
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void TestPrint() {
        for (Event event : this.ListOfEvent) {
            System.out.println(event.getStrEvent());
        }
    }

    public ArrayList<Event> getListOfEvent(){
        return this.ListOfEvent;
    }
}

import Classes.Leagues;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfLeagues {
    public ArrayList<Leagues> ListOfLeagues = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/1/search_all_leagues.php?s=";
    public Gson gson = new Gson();

    public void APIListLeagues(String SportType){
        try
        {
            Scanner scanner = new Scanner(new URL(this.API+SportType).openStream(),
                    StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            JsonObject jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
            addToArrayList(jsonObject);
        }catch (Exception e){e.printStackTrace();}
    }

    public void addToArrayList(JsonObject jsonObject){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("countrys");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject teamJson = (JsonObject) jsonArray.get(i);
                Leagues leagues = this.gson.fromJson(teamJson, Leagues.class);
                this.ListOfLeagues.add(leagues);
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void TestPrint() {
        for (Leagues leagues : this.ListOfLeagues) {
            System.out.println(leagues.getStrLeague());
        }
    }
}

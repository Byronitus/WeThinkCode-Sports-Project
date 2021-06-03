import Classes.Sports;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfSports {
    public ArrayList<Sports> ListOfSports = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/1/all_sports.php";
    public Gson gson = new Gson();

    public void APIListSports(){
        try
        {
            Scanner scanner = new Scanner(new URL(this.API).openStream(),
                    StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            JsonObject jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
            addToArrayList(jsonObject);
        }catch (Exception e){e.printStackTrace();}
    }

    public void addToArrayList(JsonObject jsonObject){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("sports");
//            System.out.println(jsonArray);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject teamJson = (JsonObject) jsonArray.get(i);
                    Sports sports = this.gson.fromJson(teamJson, Sports.class);
                    this.ListOfSports.add(sports);
//                    System.out.println(this.ListOfSports);
                }
            }catch(Exception e){e.printStackTrace();}
    }

    public void TestPrint() {
        for (Sports sports : this.ListOfSports) {
            System.out.println(sports.getStrSport());
//            System.out.println(sports.getStrSportDescription());
        }
    }
}


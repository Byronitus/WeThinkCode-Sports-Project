import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Search {
    public final String API = "https://www.thesportsdb.com/api/v1/json/1/";
    public final String SearchTeams = "searchteams.php?t=";
    public String SearchString;
    public ArrayList<Team> TeamList = new ArrayList<>();
    public Gson gson = new Gson();


    public void getUserSearch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ENTER TEAM NAME");
        this.SearchString = scanner.nextLine();
        APISearch();
    }

    public void APISearch(){
        try
        {
            Scanner scanner = new Scanner(new URL(this.API+this.SearchTeams+this.SearchString).openStream(),
                    StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            JsonObject jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
        }catch (Exception e){e.printStackTrace();}
        addToArrayList(jsonObject);
    }

    public void addToArrayList(JsonObject jsonObject){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("teams");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject teamJson = (JsonObject) jsonArray.get(i);
                Team team = new Team();
                team = gson.fromJson(teamJson, Team.class);
                TeamList.add(team);
            }
        }catch(Exception e){}
    }

    public void TestPrint(){
        for (Team team:TeamList) {
            System.out.println(team.getStrTeam());
        }
    }

}


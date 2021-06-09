package ListOfClasses;

import Classes.Team;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfTeams {
    public ArrayList<Team> ListOfTeams = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=";
    public Gson gson = new Gson();

    public String createURLString(String LeagueID){
        return this.API+LeagueID;
    }

    public void APIListTeams(String url){
        System.out.println(url);
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
            JsonArray jsonArray = (JsonArray) jsonObject.get("teams");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject teamJson = (JsonObject) jsonArray.get(i);
                Team team = this.gson.fromJson(teamJson, Team.class);
                this.ListOfTeams.add(team);
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void TestPrint() {
        for (Team team : this.ListOfTeams) {
            System.out.println(team.getStrTeam());
        }
    }

    public ArrayList<Team> getListOfTeams(){
        return this.ListOfTeams;
    }
}
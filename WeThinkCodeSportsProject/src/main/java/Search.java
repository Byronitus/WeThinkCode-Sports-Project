import Classes.Player;
import Classes.Team;
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
    public final String SearchPlayers = "searchplayers.php?p=";
    public String SearchString;
    public ArrayList<Team> TeamList = new ArrayList<Team>();
    public ArrayList<Player> PlayerList = new ArrayList<Player>();
    public Gson gson = new Gson();




    public void getUserSearch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ENTER TEAM NAME");
        this.SearchString = scanner.nextLine();
        APITeamSearch();
        APIPlayerSearch();
    }

    public void APIPlayerSearch(){
        try
        {
            Scanner scanner = new Scanner(new URL(this.API+this.SearchPlayers+this.SearchString.replaceAll(" ","%20")).openStream(),
                    StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            JsonObject jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
            addToArrayListPlayer(jsonObject, this.PlayerList);
        }catch (Exception e){e.printStackTrace();}
    }

    public void APITeamSearch(){
        try
        {
            Scanner scanner = new Scanner(new URL(this.API+this.SearchTeams+this.SearchString).openStream(),
                    StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            JsonObject jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
            addToArrayListTeam(jsonObject, this.TeamList);
        }catch (Exception e){}
    }

    public void addToArrayListTeam(JsonObject jsonObject, ArrayList arrayList){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("teams");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject teamJson = (JsonObject) jsonArray.get(i);
                Team team = this.gson.fromJson(teamJson, Team.class);
                arrayList.add(team);
            }
        }catch(Exception e){}
    }

    public void addToArrayListPlayer(JsonObject jsonObject, ArrayList arrayList){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("player");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject teamJson = (JsonObject) jsonArray.get(i);
                Player player = this.gson.fromJson(teamJson, Player.class);
                arrayList.add(player);
            }
        }catch(Exception e){}
    }

    public void TestPrint(){
        for (Team team:TeamList) {
            System.out.println(team.getStrTeam());
        }
        for (Player player:PlayerList) {
            System.out.println(player.getStrPlayer());
        }
    }
}


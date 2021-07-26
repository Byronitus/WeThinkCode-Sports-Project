package com.example.springbootdemomaven.ListOfClasses;


import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.Team;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoDatabase;
import org.ini4j.Wini;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfTeams {
    public ArrayList<Team> ListOfTeams = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/";
    public Gson gson = new Gson();
    public Database database;
    public String LeagueID;
    public String APIkey;
    public String field = "/lookup_all_teams.php?id=";

    public void ReadFromIni(){
        try {
            InputStream inputStream = new FileInputStream("APIKey.ini");
            Wini iniFile = new Wini(inputStream);
            this.APIkey = iniFile.get("APIKey", "key", String.class);
        }catch (Exception e){
            this.APIkey = "1";
        }
    }

    public ListOfTeams(String LeagueID, MongoDatabase mongoDatabase){
        this.LeagueID = LeagueID;
        this.database = new Database(mongoDatabase);
        ReadFromIni();
    }

    public String createURLString(){
        return this.API+this.APIkey+this.field+this.LeagueID;
    }

    public void APIListTeams(String url){
        try
        {
            JsonObject jsonObject;
            jsonObject = this.database.checkIfDocumentExists(this.LeagueID,"Teams");
            if (jsonObject == null) {
                Scanner scanner = new Scanner(new URL(url).openStream(),
                        StandardCharsets.UTF_8.toString());
                scanner.useDelimiter("\\A");
                jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
                this.database.AddDocument(jsonObject,"Teams",this.LeagueID,"");
            }
            addToArrayList(jsonObject);
        }catch (Exception e){e.printStackTrace();}
    }

    public void addToArrayList(JsonObject jsonObject){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("teams");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject teamJson = (JsonObject) jsonArray.get(i);
                Team team = this.gson.fromJson(teamJson, Team.class);
                changeURL(team);
                checkImage(team);
                this.ListOfTeams.add(team);
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void checkImage(Team team){
        if (team.strTeamLogo == null){
            team.setTeamLogo("https://www.mycashflow.online/cdn/assets/layouts/app/img/img_not_available.png");
        }
    }

    public void changeURL(Team team){
            String badge = team.getStrTeamLogo();
            if (badge != null){
                String url = "https://www.thesportsdb.com/images/media/team/logo/";
                badge = badge.replaceAll(url,"small/");
                team.setTeamLogo(url + badge);
            }
    }


    public ArrayList<Team> getListOfTeams(){
        return this.ListOfTeams;
    }
}
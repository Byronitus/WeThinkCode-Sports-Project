package com.example.springbootdemomaven.ListOfClasses;


import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.Team;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoDatabase;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfTeams {
    public ArrayList<Team> ListOfTeams = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=";
    public Gson gson = new Gson();
    public Database database;
    public String LeagueID;

    public ListOfTeams(String LeagueID, MongoDatabase mongoDatabase){
        this.LeagueID = LeagueID;
        this.database = new Database(mongoDatabase);
    }

    public String createURLString(){
        return this.API+this.LeagueID;
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
                this.database.AddDocument(jsonObject,"Teams",this.LeagueID);
                System.out.println(jsonObject.toString());
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

    public void TestPrint() {
        for (Team team : this.ListOfTeams) {
            System.out.println(team.getStrTeam());
        }
    }

    public ArrayList<Team> getListOfTeams(){
        return this.ListOfTeams;
    }
}
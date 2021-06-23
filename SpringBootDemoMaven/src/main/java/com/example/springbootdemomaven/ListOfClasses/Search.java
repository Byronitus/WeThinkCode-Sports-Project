package com.example.springbootdemomaven.ListOfClasses;

import com.example.springbootdemomaven.Classes.Event;
import com.example.springbootdemomaven.Classes.Player;
import com.example.springbootdemomaven.Classes.Team;
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
    public final String SearchEvent = "searchevents.php?e=";
    public String SearchString;
    public ArrayList<Event> EventList = new ArrayList<>();
    public ArrayList<Team> TeamList = new ArrayList<Team>();
    public ArrayList<Player> PlayerList = new ArrayList<Player>();
    public ArrayList<Object> UserSearchList = new ArrayList<>();
    public Gson gson = new Gson();

    public Search(String SearchString){
        this.SearchString = SearchString;
    }


    public void UserSearch(){
        APITeamSearch();
        APIPlayerSearch();
        APIEventSearch();
    }

    public void APIEventSearch(){
        try
        {
            Scanner scanner = new Scanner(new URL(this.API+this.SearchEvent+this.SearchString.replaceAll(" ","%20")).openStream(),
                    StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            JsonObject jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
            addToArrayListEvent(jsonObject, this.UserSearchList);
        }catch (Exception e){e.printStackTrace();}
    }


    public void APIPlayerSearch(){
        try
        {
            Scanner scanner = new Scanner(new URL(this.API+this.SearchPlayers+this.SearchString.replaceAll(" ","%20")).openStream(),
                    StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            JsonObject jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
            addToArrayListPlayer(jsonObject, this.UserSearchList);
        }catch (Exception e){e.printStackTrace();}
    }

    public void APITeamSearch(){
        try
        {
            Scanner scanner = new Scanner(new URL(this.API+this.SearchTeams+this.SearchString).openStream(),
                    StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            JsonObject jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
            addToArrayListTeam(jsonObject, this.UserSearchList);
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

    public void addToArrayListEvent(JsonObject jsonObject, ArrayList arrayList){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("event");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject teamJson = (JsonObject) jsonArray.get(i);
                Event event = this.gson.fromJson(teamJson, Event.class);
                arrayList.add(event);
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

    public ArrayList<Object> getUserSearchList(){
        return this.UserSearchList;
    }

    public void TestPrint(){
        for (Team team:TeamList) {
            System.out.println(team.getStrTeam());
        }
        for (Player player:PlayerList) {
            System.out.println(player.getStrPlayer());
        }
        for (Event event:EventList){
            System.out.println(event.getStrEvent());
            System.out.println(event.getDateEvent());
        }
    }
}


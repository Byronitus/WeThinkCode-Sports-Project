package com.example.springbootdemomaven.Classes;

import com.example.springbootdemomaven.Classes.League;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.util.ArrayList;

public class Counter {
    public Gson gson = new Gson();

    public ArrayList<League> IncreaseCount(String LeagueName, ArrayList<League> LeagueList, Database database, String SportName, String Date){
        for (League league:LeagueList){
            if (league.getStrLeague().equals(LeagueName)){
                league.IncreaseCount();
                UpdateDatabase(LeagueList,database, SportName, Date);
                return LeagueList;
            }
        }
        return null;
    }

    public void UpdateDatabase(ArrayList<League> LeagueList, Database database, String SportName, String Date){
        database.DeleteDocument(SportName.toLowerCase(),"Leagues");
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (League league:LeagueList){
            String Copy = league.getStrLogo();
            league.setStrLogo(league.getStrLogo().replaceAll("/small",""));
            JsonObject jsonLeague = new JsonParser().parse(this.gson.toJson(league)).getAsJsonObject();
            jsonArray.add(jsonLeague);
            league.setStrLogo(Copy);
        }
        jsonObject.add("countrys",jsonArray);
        database.AddDocument(jsonObject,"Leagues",SportName.toLowerCase(), Date);
    }


    public ArrayList<ArrayList<String>> SaveCount(JsonObject jsonObject){
        ArrayList<ArrayList<String>> CountList = new ArrayList<>();
        JsonArray jsonArray = (JsonArray) jsonObject.get("countrys");
        for (int i = 0; i < jsonArray.size(); i++) {
            ArrayList<String> Count = new ArrayList<>();
            JsonObject leaguesJson = (JsonObject) jsonArray.get(i);
            Count.add(String.valueOf(leaguesJson.get("strLeague")));
            Count.add(String.valueOf(leaguesJson.get("Count")));
            CountList.add(Count);
        }
        return CountList;
    }
}

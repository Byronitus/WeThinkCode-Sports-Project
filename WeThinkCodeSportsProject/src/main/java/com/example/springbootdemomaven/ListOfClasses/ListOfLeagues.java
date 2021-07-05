package com.example.springbootdemomaven.ListOfClasses;


import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.League;
import com.example.springbootdemomaven.Classes.Team;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoDatabase;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfLeagues {
    public ArrayList<League> ListOfLeagues = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/1/search_all_leagues.php?s=";
    public Gson gson = new Gson();
    public String SportType;
    public Database database;

    public ListOfLeagues(String SportType, MongoDatabase mongoDatabase){
        this.database = new Database(mongoDatabase);
        this.SportType = SportType;
    }

    public String createURLString(){
        return this.API+this.SportType;
    }

    public void APIListLeagues(String url){
        try
        {
            JsonObject jsonObject;
            jsonObject = this.database.checkIfDocumentExists(this.SportType.toLowerCase(),"Leagues");
            if (jsonObject == null) {
                Scanner scanner = new Scanner(new URL(url).openStream(),
                        StandardCharsets.UTF_8.toString());
                scanner.useDelimiter("\\A");
                jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
                this.database.AddDocument(jsonObject,"Leagues",this.SportType.toLowerCase());
            }
            addToArrayList(jsonObject);
        }catch (Exception e){e.printStackTrace();}
    }

    public void addToArrayList(JsonObject jsonObject){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("countrys");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject leaguesJson = (JsonObject) jsonArray.get(i);
                League league = this.gson.fromJson(leaguesJson, League.class);
                changeURL(league);
                checkImage(league);
                this.ListOfLeagues.add(league);
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void checkImage(League league){
        if (league.strLogo == null){
            league.setStrLogo("https://www.mycashflow.online/cdn/assets/layouts/app/img/img_not_available.png");
        }
    }

    public void changeURL(League league){
        try {
                String logo = league.getStrLogo();
                String url = "https://www.thesportsdb.com/images/media/league/logo/";
                logo = logo.replaceAll(url, "small/");
                league.setStrLogo(url + logo);
        }catch (Exception e){}
    }

    public void TestPrint() {
        for (League league : this.ListOfLeagues) {
            System.out.println(league.getStrLeague());
            System.out.println(league.getIdLeague());
        }
    }

    public ArrayList<League> getListOfLeagues(){
        return this.ListOfLeagues;
    }
}

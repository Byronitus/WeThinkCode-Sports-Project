package com.example.springbootdemomaven;


import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.Sport;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;


public class ListOfSports {
    public ArrayList<Sport> ListOfSports = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/1/all_sports.php";
    public Gson gson = new Gson();
    public Database database;

    public ListOfSports(Database database){
        this.database = database;
    }

    public String createURLString(){
        return this.API;
    }

    public void APIListSports(String url){
        try
        {
            JsonObject jsonObject;
            jsonObject = database.checkIfDocumentExists("Sports","Sports");
            if (jsonObject == null) {
                Scanner scanner = new Scanner(new URL(url).openStream(),
                        StandardCharsets.UTF_8.toString());
                scanner.useDelimiter("\\A");
                jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
            database.AddDocument(jsonObject,"Sports","Sports");
            }
            addToArrayList(jsonObject);
        }catch (Exception e){e.printStackTrace();}
    }

    public void addToArrayList(JsonObject jsonObject){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("sports");
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject sportJson = (JsonObject) jsonArray.get(i);
                    Sport sport = this.gson.fromJson(sportJson, Sport.class);
                    this.ListOfSports.add(sport);
                }
            }catch(Exception e){e.printStackTrace();}
    }

    public void TestPrint() {
        for (Sport sport : this.ListOfSports) {
            System.out.println(sport.getStrSport());
        }
    }

    public ArrayList<Sport> getListOfSports(){
        return this.ListOfSports;
    }
}


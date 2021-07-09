package com.example.springbootdemomaven.ListOfClasses;


import com.example.springbootdemomaven.Classes.Season;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.ini4j.Wini;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfSeasons {
    public ArrayList<Season> ListOfSeasons = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/";
    public String field = "/search_all_seasons.php?id=";
    public Gson gson = new Gson();
    public String APIkey;

    public ListOfSeasons(){
        ReadFromIni();
    }

    public void ReadFromIni(){
        try {
            InputStream inputStream = new FileInputStream("APIKey.ini");
            Wini iniFile = new Wini(inputStream);
            this.APIkey = iniFile.get("APIKey", "key", String.class);
        }catch (Exception e){
            this.APIkey = "1";
        }
    }

    public String createURLString(String LeagueID){
        return this.API+this.APIkey+this.field+LeagueID;
    }

    public void APIListSeason(String url){
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
            JsonArray jsonArray = (JsonArray) jsonObject.get("seasons");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject seasonJson = (JsonObject) jsonArray.get(i);
                Season season = this.gson.fromJson(seasonJson, Season.class);
                this.ListOfSeasons.add(season);
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void TestPrint() {
        for (Season season : this.ListOfSeasons) {
            System.out.println(season.getStrSeason());
        }
    }

    public ArrayList<Season> getListOfSeasons(){
        return this.ListOfSeasons;
    }
}

package com.example.springbootdemomaven.ListOfClasses;


import com.example.springbootdemomaven.Classes.LiveScore;
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

public class ListOfLiveEvents {
    public ArrayList<LiveScore> ListOfLiveScores = new ArrayList<>();
    public String API = "https://www.thesportsdb.com/api/v2/json/";
    public String sportField = "/livescore.php?s=";
    public String idField = "/livescore.php?l=";
    public String APIKey;
    public Gson gson = new Gson();
    public String SportType;
    public String EventID;

    public ListOfLiveEvents(String sportType, String EventID){
        ReadFromIni();
        this.SportType = sportType;
        this.EventID = EventID;
    }

    public void ReadFromIni(){
        try {
            InputStream inputStream = new FileInputStream("APIKey.ini");
            Wini iniFile = new Wini(inputStream);
            this.APIKey = iniFile.get("APIKey", "key", String.class);
        }catch (Exception e){
            this.APIKey = "1";
        }
    }

    public String createURLString(){
        if (this.EventID.equals("")) {
            return this.API + this.APIKey + this.sportField + this.SportType;
        }else{
            return this.API + this.APIKey + this.idField + this.EventID;
        }
    }

    public void APILiveScores(String url){
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
            JsonArray jsonArray = (JsonArray) jsonObject.get("events");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject seasonJson = (JsonObject) jsonArray.get(i);
                LiveScore liveScore = this.gson.fromJson(seasonJson, LiveScore.class);
                this.ListOfLiveScores.add(liveScore);
            }
        }catch(Exception e){}
    }

    public ArrayList<LiveScore> getLiveEvent(String LiveEventID){
        ArrayList<LiveScore> list = new ArrayList<>();
        for (LiveScore liveScore:this.ListOfLiveScores){
            if (liveScore.getIdLiveScore().equals(LiveEventID)){
                list.add(liveScore);
            }
        }
        return list;
    }

    public ArrayList<LiveScore> getListOfLiveScores(){return this.ListOfLiveScores;}

}

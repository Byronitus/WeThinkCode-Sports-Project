package com.example.springbootdemomaven.ListOfClasses;

import com.example.springbootdemomaven.Classes.LiveScore;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TestListOfLiveEvents {
    public Gson gson = new Gson();
    public ArrayList<LiveScore> ListOfEvents = new ArrayList<>();

    public ArrayList<LiveScore> GetJson() {
        try {
            String JsonString = new Scanner(new File("ListOfLiveEvnts.json")).useDelimiter("\\Z").next();
            JsonArray jsonArray = gson.fromJson(JsonString, JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject eventJson = (JsonObject) jsonArray.get(i);
                LiveScore liveScore = this.gson.fromJson(eventJson, LiveScore.class);
                this.ListOfEvents.add(liveScore);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.ListOfEvents;
    }

}

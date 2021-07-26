package com.example.springbootdemomaven.ListOfClasses;

import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.Event;
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

public class PastEventsForTeam {
    public ArrayList<Event> ListOfEvents = new ArrayList<>();
    public String API = "https://www.thesportsdb.com/api/v1/json/";
    public String APIkey;
    public String field = "/eventslast.php?id=";
    public Database database;
    public String TeamID;
    public Gson gson = new Gson();

    public void ReadFromIni(){
        try {
            InputStream inputStream = new FileInputStream("APIKey.ini");
            Wini iniFile = new Wini(inputStream);
            this.APIkey = iniFile.get("APIKey", "key", String.class);
        }catch (Exception e){
            this.APIkey = "1";
        }
    }

    public PastEventsForTeam(MongoDatabase mongoDatabase, String TeamID){
        this.TeamID = TeamID;
        this.database = new Database(mongoDatabase);
        ReadFromIni();
    }

    public String CreateURL(){return this.API+this.APIkey+this.field+this.TeamID;}

    public void APIListEvents(String url){
        try
        {
            JsonObject jsonObject;
            jsonObject = this.database.checkIfDocumentExists(this.TeamID,"PastTeamEvents");
            if (jsonObject == null) {
                Scanner scanner = new Scanner(new URL(url).openStream(),
                        StandardCharsets.UTF_8.toString());
                scanner.useDelimiter("\\A");
                jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
                this.database.AddDocument(jsonObject,"PastTeamEvents",this.TeamID,"");
            }
            addToArrayList(jsonObject);
        }catch (Exception e){e.printStackTrace();}
    }

    public void addToArrayList(JsonObject jsonObject){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("results");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject eventJson = (JsonObject) jsonArray.get(i);
                Event event = this.gson.fromJson(eventJson, Event.class);
//                changeURL(player);
//                checkImage(player);
                this.ListOfEvents.add(event);
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

    public ArrayList<Event> getListOfEvents(){
        return this.ListOfEvents;
    }

}

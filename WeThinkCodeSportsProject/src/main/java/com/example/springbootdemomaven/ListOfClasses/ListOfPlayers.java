package com.example.springbootdemomaven.ListOfClasses;

import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.Player;
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

public class ListOfPlayers {
    public ArrayList<Player> ListOfPlayers = new ArrayList<>();
    public String API = "https://www.thesportsdb.com/api/v1/json/";
    public String field = "/lookup_all_players.php?id=";
    public String APIkey;
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

    public ListOfPlayers(MongoDatabase mongoDatabase, String TeamID){
        ReadFromIni();
        this.database = new Database(mongoDatabase);
        this.TeamID = TeamID;
    }

    public String CreateURL(){return this.API+this.APIkey+this.field+this.TeamID;}

    public void APIListPlayers(String url){
        try
        {
            JsonObject jsonObject;
            jsonObject = this.database.checkIfDocumentExists(this.TeamID,"Players");
            if (jsonObject == null) {
                Scanner scanner = new Scanner(new URL(url).openStream(),
                        StandardCharsets.UTF_8.toString());
                scanner.useDelimiter("\\A");
                jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
                this.database.AddDocument(jsonObject,"Players",this.TeamID);
            }
            addToArrayList(jsonObject);
        }catch (Exception e){e.printStackTrace();}
    }

    public void addToArrayList(JsonObject jsonObject){
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("player");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject playerJson = (JsonObject) jsonArray.get(i);
                Player player = this.gson.fromJson(playerJson, Player.class);
                changeURL(player);
                checkImage(player);
                this.ListOfPlayers.add(player);
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void checkImage(Player player){
        if (player.strThumb == null){
            player.setStrThumb("https://www.mycashflow.online/cdn/assets/layouts/app/img/img_not_available.png");
        }
        player.setStrPlayerImage(player.getStrThumb());
    }

    public void changeURL(Player player){
        String badge = player.getStrThumb();
        if (badge != null){
            String url = "https://www.thesportsdb.com/images/media/player/thumb/";
            badge = badge.replaceAll(url,"small/");
            player.setStrThumb(url + badge);
        }
    }

    public ArrayList<Player> getListOfPlayers(){
        return this.ListOfPlayers;
    }

}

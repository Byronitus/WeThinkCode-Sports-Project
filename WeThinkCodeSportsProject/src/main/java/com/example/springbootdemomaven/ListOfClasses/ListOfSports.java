package com.example.springbootdemomaven.ListOfClasses;


import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.Sport;
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


public class ListOfSports {
    public String APIkey;
    public ArrayList<Sport> ListOfSports = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/";
    public String field = "/all_sports.php";
    public Gson gson = new Gson();
    public Database database;

    public void ReadFromIni(){
        try {
            InputStream inputStream = new FileInputStream("APIKey.ini");
            Wini iniFile = new Wini(inputStream);
            this.APIkey = iniFile.get("APIKey", "key", String.class);
        }catch (Exception e){
            this.APIkey = "1";
        }
    }

    public ListOfSports(MongoDatabase mongoDatabase){
        ReadFromIni();
        this.database = new Database(mongoDatabase);
    }

    public String createURLString(){
        return this.API+this.APIkey+this.field;
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
            database.AddDocument(jsonObject,"Sports","Sports","");
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
                    checkImage(sport);
                    this.ListOfSports.add(sport);
                }
            }catch(Exception e){e.printStackTrace();}
    }

    public void checkImage(Sport sport){
        if (sport.getStrSportThumb() == null){
            sport.setStrSportThumb("https://www.mycashflow.online/cdn/assets/layouts/app/img/img_not_available.png");
        }
    }


    public ArrayList<Sport> getListOfSports(){
        return this.ListOfSports;
    }
}


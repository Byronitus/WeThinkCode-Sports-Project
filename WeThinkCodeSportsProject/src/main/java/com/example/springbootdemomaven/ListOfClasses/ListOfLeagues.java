package com.example.springbootdemomaven.ListOfClasses;


import com.example.springbootdemomaven.Classes.Counter;
import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.League;
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
import java.util.Comparator;
import java.util.Scanner;

public class ListOfLeagues {
    public ArrayList<League> ListOfLeagues = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/";
    public String field = "/search_all_leagues.php?s=";
    public Gson gson = new Gson();
    public String SportType;
    public Database database;
    public String APIkey;
    public String Date;
    public Boolean check = false;

    public void ReadFromIni() {
        try {
            InputStream inputStream = new FileInputStream("APIKey.ini");
            Wini iniFile = new Wini(inputStream);
            this.APIkey = iniFile.get("APIKey", "key", String.class);
        } catch (Exception e) {
            this.APIkey = "1";
        }
    }

    public ListOfLeagues(String SportType, MongoDatabase mongoDatabase) {
        this.database = new Database(mongoDatabase);
        this.SportType = SportType;
        ReadFromIni();
    }

    public String createURLString() {
        return this.API + this.APIkey + this.field + this.SportType.replaceAll(" ", "%20");
    }

    public void APIListLeagues(String url) {
        try {
            JsonObject jsonObject;
            jsonObject = this.database.checkIfDocumentExists(this.SportType.toLowerCase(), "Leagues");
            if (jsonObject == null) {
                this.check = true;
                Scanner scanner = new Scanner(new URL(url).openStream(),
                        StandardCharsets.UTF_8.toString());
                scanner.useDelimiter("\\A");
                jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
//                jsonObject = addCount(jsonObject);
                this.database.AddDocument(jsonObject, "Leagues", this.SportType.toLowerCase(), "");
            }
            addToArrayList(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JsonObject addCount(JsonObject jsonObject) {
        JsonArray jsonArray = (JsonArray) jsonObject.get("countrys");
        JsonObject newJsonObject = new JsonObject();
        JsonArray newJsonArray = new JsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject leaguesJson = (JsonObject) jsonArray.get(i);
            leaguesJson.addProperty("Count", 0);
            newJsonArray.add(leaguesJson);
        }
        newJsonObject.add("countrys", newJsonArray);
        return newJsonObject;
    }

    public void addToArrayList(JsonObject jsonObject) {
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("countrys");
            this.Date = String.valueOf(jsonObject.get("date"));
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject leaguesJson = (JsonObject) jsonArray.get(i);
                League league = this.gson.fromJson(leaguesJson, League.class);
                if (this.check) {
                    addCount(league);
                }
                changeURL(league);
                checkImage(league);
                this.ListOfLeagues.add(league);
            }
            Counter counter = new Counter();
            counter.UpdateDatabase(this.ListOfLeagues, this.database, this.ListOfLeagues.get(0).getStrSport(), this.Date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCount(League league) {
        if (this.database.getCountList().size() > 0) {
            for (ArrayList<String> CountList : this.database.getCountList()) {
                if (league.getStrLeague().equals(CountList.get(0).replaceAll("\"", ""))) {
                    league.Count = Integer.parseInt(CountList.get(1));
                    break;
                }
            }
        } else {
            league.Count = 0;
        }
    }

    public void checkImage(League league) {
        if (league.strLogo == null) {
            league.setStrLogo("https://www.mycashflow.online/cdn/assets/layouts/app/img/img_not_available.png");
        }
    }

    public void changeURL(League league) {
        try {
            String logo = league.getStrLogo();
            String url = "https://www.thesportsdb.com/images/media/league/logo/";
            logo = logo.replaceAll(url, "small/");
            league.setStrLogo(url + logo);
        } catch (Exception e) {
        }
    }


    public String getDate() {
        return this.Date;
    }

    public ArrayList<League> getListOfLeagues() {
        this.ListOfLeagues.sort(Comparator.comparing(League::getCount).reversed());
        return this.ListOfLeagues;
    }
}

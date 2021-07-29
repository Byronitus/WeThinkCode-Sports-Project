package com.example.springbootdemomaven.ListOfClasses;


import com.byronitis.wethinkcode.Constants;
import com.byronitis.wethinkcode.UtilFunctions;
import com.example.springbootdemomaven.Classes.Counter;
import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.League;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoDatabase;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ListOfLeagues {

    /*
        NOTE: General Changes
        1) by convention, variables in Java start with a lowercase letter
        2) by convention, class methods in Java start with a lowercase letter
        3) Class members should be private, unless there is an explicit reason to do otherwise
        4) Frequently-used strings (like the image-not-available URL) should be extracted to constants, and referenced everywhere
     */

    private final ArrayList<League> listOfLeagues = new ArrayList<>();
    private final String api = "https://www.thesportsdb.com/api/v1/json/";
    private final String field = "/search_all_leagues.php?s=";
    private final Gson gson = new Gson();
    private final String sportType;
    private final Database database;
    private final String apiKey;
    private String date;
    private Boolean check = false;

    public ListOfLeagues(String SportType, MongoDatabase mongoDatabase) {
        this.database = new Database(mongoDatabase);
        this.sportType = SportType;
        this.apiKey = UtilFunctions.readFromIni();
    }

    public String createURLString() {
        return this.api + this.apiKey + this.field + this.sportType.replaceAll(" ", "%20");
    }

    public void listLeagues(String url) {
        try {
            JsonObject jsonObject;
            jsonObject = this.database.checkIfDocumentExists(this.sportType.toLowerCase(), "Leagues");
            if (jsonObject == null) {
                this.check = true;
                Scanner scanner = new Scanner(new URL(url).openStream(),
                        StandardCharsets.UTF_8.toString());
                scanner.useDelimiter("\\A");
                jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
                this.database.AddDocument(jsonObject, "Leagues", this.sportType.toLowerCase(), "");
            }
            addToArrayList(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToArrayList(JsonObject jsonObject) {
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("countrys");
            this.date = String.valueOf(jsonObject.get("date"));
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject leaguesJson = (JsonObject) jsonArray.get(i);
                League league = this.gson.fromJson(leaguesJson, League.class);
                if (this.check) {
                    addCount(league);
                }
                league.setStrLogo(UtilFunctions.changeURL(league.getStrLogo(), "https://www.thesportsdb.com/images/media/league/logo/"));
                checkImage(league);
                this.listOfLeagues.add(league);
            }
            Counter counter = new Counter();
            counter.UpdateDatabase(this.listOfLeagues, this.database, this.listOfLeagues.get(0).getStrSport(), this.date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCount(League league) {
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

    private void checkImage(League league) {
        if (league.strLogo == null) {
            league.setStrLogo(Constants.IMG_NOT_AVAILABLE);
        }
    }

    public String getDate() {
        return this.date;
    }

    public ArrayList<League> getListOfLeagues() {
        this.listOfLeagues.sort(Comparator.comparing(League::getCount).reversed());
        return this.listOfLeagues;
    }
}

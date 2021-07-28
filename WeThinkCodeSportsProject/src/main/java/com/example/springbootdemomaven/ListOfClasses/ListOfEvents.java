package com.example.springbootdemomaven.ListOfClasses;

import com.example.springbootdemomaven.Classes.Event;
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

public class ListOfEvents {
    public ArrayList<Event> ListOfEvent = new ArrayList<>();
    public final String API = "https://www.thesportsdb.com/api/v1/json/";
    public String field = "/eventsseason.php?id=";
    public Gson gson = new Gson();
    public String APIkey;

    public ListOfEvents() {
        ReadFromIni();
    }

    public void ReadFromIni() {
        try {
            InputStream inputStream = new FileInputStream("APIKey.ini");
            Wini iniFile = new Wini(inputStream);
            this.APIkey = iniFile.get("APIKey", "key", String.class);
        } catch (Exception e) {
            this.APIkey = "1";
        }
    }

    public String createURLString(String LeagueID, String SeasonName) {
        return this.API + this.APIkey + this.field + LeagueID + "&s=" + SeasonName;
    }

    public void APIListEvents(String url) {
        try {
            Scanner scanner = new Scanner(new URL(url).openStream(),
                    StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            JsonObject jsonObject = new Gson().fromJson(scanner.next(), JsonObject.class);
            addToArrayList(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToArrayList(JsonObject jsonObject) {
        try {
            JsonArray jsonArray = (JsonArray) jsonObject.get("events");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject eventJson = (JsonObject) jsonArray.get(i);
                Event event = this.gson.fromJson(eventJson, Event.class);
                changeURL(event);
                checkImage(event);
                checkVideo(event);
                this.ListOfEvent.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkVideo(Event event) {
        if (!(event.getStrVideo() == null)) {
            String VideoID = event.getStrVideo().substring(32);
            event.setStrVideo(VideoID);
        }
    }

    public void checkImage(Event event) {
        if (event.strThumb == null) {
            event.setStrThumb("https://www.mycashflow.online/cdn/assets/layouts/app/img/img_not_available.png");
        }
    }

    public void changeURL(Event event) {
        try {
            String logo = event.getStrThumb();
            String url = "https://www.thesportsdb.com/images/media/event/thumb/";
            logo = logo.replaceAll(url, "small/");
            event.setStrThumb(url + logo);
        } catch (Exception e) {
        }
    }


    public ArrayList<Event> getListOfEvent() {
        return this.ListOfEvent;
    }
}

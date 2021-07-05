package com.example.springbootdemomaven;

import com.example.springbootdemomaven.Classes.*;
import com.example.springbootdemomaven.ListOfClasses.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Objects;

@Controller
public class HomeController {
//    Database database = new Database();
    public MongoClient client = MongoClients.create("mongodb+srv://admin:9wM1j5hGec7zjy8c@cluster0.b8igj.mongodb.net/test");
    public MongoDatabase mongoDatabase = this.client.getDatabase("SportsDB");

    @GetMapping("/api/sport")
    public ResponseEntity<ArrayList<Sport>> getListOfSport(){
        ListOfSports listOfSports = new ListOfSports(this.mongoDatabase);
        listOfSports.APIListSports(listOfSports.createURLString());
        return ResponseEntity.ok(listOfSports.getListOfSports());
    }

    @GetMapping("/api/league/{name}")
    public ResponseEntity<ArrayList<League>> getListOfLeagues(@PathVariable(value = "name" ) String name){
        ListOfLeagues listOfLeagues = new ListOfLeagues(name,this.mongoDatabase);
        listOfLeagues.APIListLeagues(listOfLeagues.createURLString());
        return ResponseEntity.ok(listOfLeagues.getListOfLeagues());
    }

    @GetMapping("/api/team/{id}")
    public ResponseEntity<ArrayList<Team>> getListOfTeams(@PathVariable(value = "id" ) String id){
        ListOfTeams listOfTeams = new ListOfTeams(id, this.mongoDatabase);
        listOfTeams.APIListTeams(listOfTeams.createURLString());
        return ResponseEntity.ok(listOfTeams.getListOfTeams());
    }

    @GetMapping("/api/Seasons/{id}")
    public ResponseEntity<ArrayList<Season>> getSeasonList(@PathVariable(value = "id" ) String id){
        ListOfSeasons listOfSeasons = new ListOfSeasons();
        listOfSeasons.APIListSeason(listOfSeasons.createURLString(id));
        return ResponseEntity.ok(listOfSeasons.getListOfSeasons());
    }

    @GetMapping("/api/Event/{id}/{season}")
    public ResponseEntity<ArrayList<Event>> getListOfEvents(@PathVariable(value = "id" ) String id, @PathVariable(value = "season" ) String season){
        ListOfEvents listOfEvents = new ListOfEvents();
        listOfEvents.APIListEvents(listOfEvents.createURLString(id,season));
        return ResponseEntity.ok(listOfEvents.getListOfEvent());
    }

    @GetMapping("/api/Search/{searchString}")
    public ResponseEntity<ArrayList<Object>> getUserSearch(@PathVariable(value = "searchString") String searchString){
        Search search = new Search(searchString);
        search.UserSearch();
        return ResponseEntity.ok(search.getUserSearchList());
    }

//    @RequestMapping(value = "/")
//    public String index() {
//        return "index";
//    }
}
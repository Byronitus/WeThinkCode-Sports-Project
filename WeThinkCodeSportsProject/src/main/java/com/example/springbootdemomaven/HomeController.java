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

import java.util.ArrayList;

@Controller
public class HomeController {
    public ArrayList<League> LeagueList = new ArrayList<>();
    public MongoClient client = MongoClients.create("");
    public MongoDatabase mongoDatabase = this.client.getDatabase("SportsDB");
    public String Date;

    @GetMapping("/api/sport")
    public ResponseEntity<ArrayList<Sport>> getListOfSport() {
        ListOfSports listOfSports = new ListOfSports(this.mongoDatabase);
        listOfSports.APIListSports(listOfSports.createURLString());
        return ResponseEntity.ok(listOfSports.getListOfSports());
    }

    @GetMapping("/api/league/{name}")
    public ResponseEntity<ArrayList<League>> getListOfLeagues(@PathVariable(value = "name") String name) {
        ListOfLeagues listOfLeagues = new ListOfLeagues(name, this.mongoDatabase);
        listOfLeagues.listLeagues(listOfLeagues.createURLString());
        this.LeagueList = listOfLeagues.getListOfLeagues();
        this.Date = listOfLeagues.getDate();
        return ResponseEntity.ok(listOfLeagues.getListOfLeagues());
    }

    @GetMapping("/api/team/{id}")
    public ResponseEntity<ArrayList<Team>> getListOfTeams(@PathVariable(value = "id") String id) {
        ListOfTeams listOfTeams = new ListOfTeams(id, this.mongoDatabase);
        listOfTeams.APIListTeams(listOfTeams.createURLString());
        return ResponseEntity.ok(listOfTeams.getListOfTeams());
    }

    @GetMapping("/api/Seasons/{id}")
    public ResponseEntity<ArrayList<Season>> getSeasonList(@PathVariable(value = "id") String id) {
        ListOfSeasons listOfSeasons = new ListOfSeasons();
        listOfSeasons.APIListSeason(listOfSeasons.createURLString(id));
        return ResponseEntity.ok(listOfSeasons.getListOfSeasons());
    }

    @GetMapping("/api/Event/{id}/{season}")
    public ResponseEntity<ArrayList<Event>> getListOfEvents(@PathVariable(value = "id") String id, @PathVariable(value = "season") String season) {
        ListOfEvents listOfEvents = new ListOfEvents();
        listOfEvents.APIListEvents(listOfEvents.createURLString(id, season));
        return ResponseEntity.ok(listOfEvents.getListOfEvent());
    }

    @GetMapping("/api/Search/{searchString}")
    public ResponseEntity<ArrayList<Object>> getUserSearch(@PathVariable(value = "searchString") String searchString) {
        Search search = new Search(searchString);
        search.UserSearch();
        return ResponseEntity.ok(search.getUserSearchList());
    }

    @GetMapping("/api/Players/{TeamID}")
    public ResponseEntity<ArrayList<Player>> getListOfPlayers(@PathVariable(value = "TeamID") String TeamID) {
        ListOfPlayers listOfPlayers = new ListOfPlayers(this.mongoDatabase, TeamID);
        listOfPlayers.APIListPlayers(listOfPlayers.CreateURL());
        return ResponseEntity.ok(listOfPlayers.getListOfPlayers());
    }

    @GetMapping("/api/TeamUpcomingEvents/{TeamID}")
    public ResponseEntity<ArrayList<Event>> getListOfTeamEvents(@PathVariable(value = "TeamID") String TeamID) {
        FutureEventsForTeam futureEventsForTeam = new FutureEventsForTeam(mongoDatabase, TeamID);
        futureEventsForTeam.APIListEvents(futureEventsForTeam.CreateURL());
        return ResponseEntity.ok(futureEventsForTeam.getListOfEvents());
    }

    @GetMapping("/api/TeamPastEvents/{TeamID}")
    public ResponseEntity<ArrayList<Event>> getListOfTeamEventsPast(@PathVariable(value = "TeamID") String TeamID) {
        PastEventsForTeam pastEventsForTeam = new PastEventsForTeam(mongoDatabase, TeamID);
        pastEventsForTeam.APIListEvents(pastEventsForTeam.CreateURL());
        return ResponseEntity.ok(pastEventsForTeam.getListOfEvents());
    }

    @GetMapping("/api/LeaguePastEvents/{LeagueID}")
    public ResponseEntity<ArrayList<Event>> getListOfLeagueEventsPast(@PathVariable(value = "LeagueID") String LeagueID) {
        PastEventsForLeague pastEventsForLeague = new PastEventsForLeague(mongoDatabase, LeagueID);
        pastEventsForLeague.APIListEvents(pastEventsForLeague.CreateURL());
        return ResponseEntity.ok(pastEventsForLeague.getListOfEvents());
    }

    @GetMapping("/api/LeagueFutureEvents/{LeagueID}")
    public ResponseEntity<ArrayList<Event>> getListOfLeagueEvents(@PathVariable(value = "LeagueID") String LeagueID) {
        FutureEventsForLeague futureEventsForLeague = new FutureEventsForLeague(mongoDatabase, LeagueID);
        futureEventsForLeague.APIListEvents(futureEventsForLeague.CreateURL());
        return ResponseEntity.ok(futureEventsForLeague.getListOfEvents());
    }

    @GetMapping("/api/IncreaseCounter/{LeagueName}/{SportName}")
    public ResponseEntity<String> IncreaseCount(@PathVariable(value = "LeagueName") String LeagueName, @PathVariable(value = "SportName") String SportName) {
        Counter counter = new Counter();
        this.LeagueList = counter.IncreaseCount(LeagueName, this.LeagueList, new Database(this.mongoDatabase), SportName, this.Date);
        return ResponseEntity.ok(LeagueName);
    }

    //    @GetMapping("/api/ListOfLiveScores/{SportType}")
//    public ResponseEntity<ArrayList<LiveScore>> getListOfLiveScores(@PathVariable(value = "SportType") String SportType){
//        ListOfLiveEvents listOfLiveEvents = new ListOfLiveEvents(SportType,"");
//        listOfLiveEvents.APILiveScores(listOfLiveEvents.createURLString());
//        return ResponseEntity.ok(listOfLiveEvents.getListOfLiveScores());
//    }
//
//    @GetMapping("/api/LiveScore/{LiveScoreID}/{LeagueID}")
//    public ResponseEntity<ArrayList<LiveScore>> getLiveScore(@PathVariable(value = "LiveScoreID") String LiveScoreID, @PathVariable(value = "LeagueID") String LeagueID){
//        ListOfLiveEvents listOfLiveEvents = new ListOfLiveEvents("",LeagueID);
//        listOfLiveEvents.APILiveScores(listOfLiveEvents.createURLString());
//        return ResponseEntity.ok(listOfLiveEvents.getLiveEvent(LiveScoreID));
//    }
//
//    @GetMapping("/api/LeagueLiveScore/{LeagueID}")
//    public ResponseEntity<ArrayList<LiveScore>> getLeagueLiveScore( @PathVariable(value = "LeagueID") String LeagueID){
//        ListOfLiveEvents listOfLiveEvents = new ListOfLiveEvents("",LeagueID);
//        listOfLiveEvents.APILiveScores(listOfLiveEvents.createURLString());
//        return ResponseEntity.ok(listOfLiveEvents.getListOfLiveScores());
//    }
//
//
    @GetMapping("/api/ListOfLiveScores/{SportType}")
    public ResponseEntity<ArrayList<LiveScore>> getListOfLiveScoresTEST(@PathVariable(value = "SportType") String SportType) {
        TestListOfLiveEvents testListOfLiveEvents = new TestListOfLiveEvents();
        return ResponseEntity.ok(testListOfLiveEvents.GetJson());
    }

    @GetMapping("/api/LiveScore/{LiveScoreID}/{LeagueID}")
    public ResponseEntity<ArrayList<LiveScore>> getLiveScoreTEST(@PathVariable(value = "LiveScoreID") String LiveScoreID, @PathVariable(value = "LeagueID") String LeagueID) {
        TestListOfLiveEvents testListOfLiveEvents = new TestListOfLiveEvents();
        return ResponseEntity.ok(testListOfLiveEvents.GetJson());
    }

    @GetMapping("/api/LeagueLiveScore/{LeagueID}")
    public ResponseEntity<ArrayList<LiveScore>> getLeagueLiveScoreTEST(@PathVariable(value = "LeagueID") String LeagueID) {
        TestListOfLiveEvents testListOfLiveEvents = new TestListOfLiveEvents();
        return ResponseEntity.ok(testListOfLiveEvents.GetJson());
    }

}
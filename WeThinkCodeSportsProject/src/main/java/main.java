import Classes.Database;
import ListOfClasses.*;

public class Main {

    public static void main(String[] args) {
        Database database = new Database();


//        Search search = new Search("Arsenal_vs_Chelsea");
//        search.getUserSearch();
//        search.TestPrint();


//        ListOfSports listOfSports = new ListOfSports(database);
//        listOfSports.APIListSports(listOfSports.createURLString());
//        listOfSports.TestPrint();


        ListOfLeagues listOfLeagues = new ListOfLeagues("cricket",database);
        listOfLeagues.APIListLeagues(listOfLeagues.createURLString());
        listOfLeagues.TestPrint();


//        ListOfTeams listOfTeams = new ListOfTeams("4370",database);
//        listOfTeams.APIListTeams(listOfTeams.createURLString());
//        listOfTeams.TestPrint();


//        ListOfSeasons listOfSeasons = new ListOfSeasons();
//        listOfSeasons.APIListSeason(listOfSeasons.createURLString("4370"));
//        listOfSeasons.TestPrint();


//        ListOfEvents listOfEvents = new ListOfEvents();
//        listOfEvents.APIListEvents(listOfEvents.createURLString("4370","2000"));
//        listOfEvents.TestPrint();
    }
}

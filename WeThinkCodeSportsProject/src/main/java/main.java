public class main {

    public static void main(String[] args) {
//        Search search = new Search();
//        search.getUserSearch();
//        search.TestPrint();


//        ListOfSports listOfSports = new ListOfSports();
//        listOfSports.APIListSports();
//        listOfSports.TestPrint();
//
//
//        ListOfLeagues listOfLeagues = new ListOfLeagues();
//        listOfLeagues.APIListLeagues("Motorsport");
//        listOfLeagues.TestPrint();


//        ListOfTeams listOfTeams = new ListOfTeams();
//        listOfTeams.APIListTeams("4370");
//        listOfTeams.TestPrint();


//        ListOfSeasons listOfSeasons = new ListOfSeasons();
//        listOfSeasons.APIListSeason("4370");
//        listOfSeasons.TestPrint();


        ListOfEvents listOfEvents = new ListOfEvents();
        listOfEvents.APIListEvents("4370","2000");
        listOfEvents.TestPrint();
    }
}

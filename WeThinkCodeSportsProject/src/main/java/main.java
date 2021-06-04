public class main {

    public static void main(String[] args) {
//        Search search = new Search();
//        search.getUserSearch();
//        search.TestPrint();


//        ListOfSports listOfSports = new ListOfSports();
//        listOfSports.APIListSports();
//        listOfSports.TestPrint();

        ListOfLeagues listOfLeagues = new ListOfLeagues();
        listOfLeagues.APIListLeagues("Motorsport");
        listOfLeagues.TestPrint();
    }
}

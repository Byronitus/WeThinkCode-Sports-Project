import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.Sport;
import com.example.springbootdemomaven.ListOfClasses.ListOfSports;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;
import java.io.File;
import java.io.FileReader;

public class ListOfSportsTest {
    Database database;
    public MongodExecutable mongodExecutable;
    public MongoDatabase mongoDatabase;


    @Test
    void TestAPIIntegration(){
        int count = 0;
        try {
            FileReader fileReader = new FileReader("src\\\\test\\\\java\\\\JSONTestFiles\\\\Sports.json");
            JsonObject jsonObject = new Gson().fromJson(fileReader, JsonObject.class); //Creating json object
            JsonArray jsonArray = (JsonArray) jsonObject.get("sports");
            ListOfSports listOfSports = new ListOfSports(this.mongoDatabase);
            listOfSports.APIListSports("file:///"+(new File("Leagues.json").getAbsolutePath())
                    .replaceAll("Leagues.json","src\\\\test\\\\java\\\\JSONTestFiles\\\\Sports.json")); //Sending the url of the json file
            for (Sport sport:listOfSports.getListOfSports()) { //Iterating through the list of classes and comparing it to the json file

                JsonObject jsonSports = (JsonObject) jsonArray.get(count);

                Assertions.assertEquals(sport.getIdSport(), jsonSports.get("idSport")
                        .toString().replaceAll("\"",""));

                Assertions.assertEquals(sport.getStrSport(), jsonSports.get("strSport")
                        .toString().replaceAll("\"",""));

                count = count + 1;
            }
        }catch(Exception e){e.printStackTrace();}
    }
}

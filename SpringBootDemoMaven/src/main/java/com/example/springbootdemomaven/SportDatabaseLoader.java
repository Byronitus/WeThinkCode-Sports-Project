package com.example.springbootdemomaven;


import com.example.springbootdemomaven.Classes.Database;
import com.example.springbootdemomaven.Classes.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SportDatabaseLoader implements CommandLineRunner {
    ArrayList<Sport> SportList = new ArrayList<>();
    public final SportRepository sportRepository;

    @Autowired
    public SportDatabaseLoader(SportRepository sportRepository){
        this.sportRepository = sportRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        ListOfSports listOfSports = new ListOfSports(new Database());
        listOfSports.APIListSports(listOfSports.createURLString());
        this.SportList = listOfSports.getListOfSports();
        for (Sport sport:this.SportList) {
            this.sportRepository.save(sport);
        }
    }
}

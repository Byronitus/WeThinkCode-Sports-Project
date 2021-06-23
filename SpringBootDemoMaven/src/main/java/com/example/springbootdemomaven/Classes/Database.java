package com.example.springbootdemomaven.Classes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.model.Filters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Database {
    public Gson gson;
    MongoClient client;
    MongoDatabase mongoDatabase;

    public Database(){
        this.client = MongoClients.create("");
        this.mongoDatabase = this.client.getDatabase("SportsDB");
    }

    public void AddDocument(JsonObject jsonObject,String Collection, String id){
        MongoCollection mongoCollection = this.mongoDatabase.getCollection(Collection);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date =new Date();
        jsonObject.addProperty("_id",id);
        jsonObject.addProperty("date",dateFormat.format(date));
        Document document = Document.parse(jsonObject.toString());
        mongoCollection.insertOne(document);
    }

    public Boolean checkDate(String dateString){
        Calendar calendar = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            Date date = format.parse(dateString.replaceAll("\"",""));
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 2);
            System.out.println(calendar.getTime());
            System.out.println(currentDate.getTime());
            if (currentDate.compareTo(calendar) < 1){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void DeleteDocument(String id, String Collection){
        MongoCollection mongoCollection = this.mongoDatabase.getCollection(Collection);
        mongoCollection.deleteOne(Filters.eq("_id",id));
    }

    public JsonObject checkIfDocumentExists(String id, String Collection){
        try {
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(Collection);
            Document document = (Document) mongoCollection.find(Filters.eq("_id", id)).first();
            JsonObject jsonObject = new Gson().fromJson(document.toJson(), JsonObject.class);
            if (!checkDate(jsonObject.get("date").toString())){
                DeleteDocument(id,Collection);
                return null;
            }
            return jsonObject;
        }catch (NullPointerException exception){
            return null;}
    }
}

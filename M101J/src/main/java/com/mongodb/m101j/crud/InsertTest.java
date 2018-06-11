/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.m101j.util.Helpers.printJson;
import static java.util.Arrays.asList;
import org.bson.Document;

/**
 *
 * @author ftorrejon
 */
public class InsertTest {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> coll = db.getCollection("insertTest");
    
    coll.drop();
    
    Document smith = new Document("name", "Smith")
            .append("age",30)
            .append("profession", "programmer");
    
    Document jones = new Document("name", "Jones")
            .append("age",36)
            .append("profession", "analyst");
    
    printJson(smith);
    coll.insertMany(asList(smith, jones));
    printJson(smith);
    printJson(jones);
  }
}

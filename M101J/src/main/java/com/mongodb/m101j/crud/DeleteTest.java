package com.mongodb.m101j.crud;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.ArrayList;
import org.bson.Document;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ftorrejon
 */
public class DeleteTest {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase database = client.getDatabase("course");
    MongoCollection<Document> collection = database.getCollection("DeleteTest");
    
    collection.drop();
    
    for (int i = 0; i < 8; i++) {
      collection.insertOne(new Document().append("_id",i));
    }
    
    for (Document cur : collection.find().into(new ArrayList<Document>())){
      printJson(cur);
    }
    
    System.out.println();
    
    //collection.deleteMany(gte("_id",4));
    collection.deleteOne(gte("_id",4));
    
    for (Document cur : collection.find().into(new ArrayList<Document>())){
      printJson(cur);
    }
    
  }
}

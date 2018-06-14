/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import com.mongodb.client.model.UpdateOptions;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author ftorrejon
 */
public class UpdateTest {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase database = client.getDatabase("course");
    MongoCollection<Document> collection = database.getCollection("UpdateTest");
    
    collection.drop();
    
    for (int i = 0; i < 10; i++){
      collection.insertOne(new Document()
              .append("_id",i)
              .append("x", i)
              .append("y", true));
    }
    
    //collection.replaceOne(eq("x",5), new Document("x",20).append("updated",true));
    //collection.updateOne(eq("x",5), new Document("$set", new Document("x",20).append("updated", true)));
    //collection.updateOne(eq("x",5), combine(set("x",20),set("updated",true)));
    //collection.updateOne(eq("_id",11), combine(set("x",20),set("updated",true)), new UpdateOptions().upsert(true));
    
    collection.updateMany(gte("x",5), inc("x",1));
    for (Document cur: collection.find().into(new ArrayList<Document>())){
      printJson(cur);
    }
    
  }
  
}

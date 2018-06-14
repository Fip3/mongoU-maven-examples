/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author ftorrejon
 */
public class FindWithFilterTest {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase database = client.getDatabase("course");
    MongoCollection<Document> collection = database.getCollection("findWithFilterTest");
    
    collection.drop();
    
    for (int i = 1; i < 10; i++) {
      collection.insertOne(new Document()
              .append("x", new Random().nextInt(2))
              .append("y", new Random().nextInt(100)));
    }
    
//    Bson filter = new Document("x", 0)
//            .append("y", new Document("$gt", 25).append("$lt", 75));

    Bson filter = and(eq("x", 0),gt("y",10), lt("y",90));
    
    List<Document> all = collection.find(filter).into(new ArrayList<Document>());
    
    for (Document cur : all) {
      printJson(cur);
    }
    
    long count = collection.count(filter);
    System.out.println();
    System.out.println(count);
  }
  
}

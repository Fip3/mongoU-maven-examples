/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author ftorrejon
 */
public class FindTest {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase database = client.getDatabase("course");
    MongoCollection<Document> collection = database.getCollection("findTest");
    
    collection.drop();
    
    for (int i = 0; i < 10; i++){
      collection.insertOne(new Document("x",i));
    }
    
    System.out.println("Find one: ");
    Document first = collection.find().first();
    printJson(first);
    
    System.out.println("Find all with into: ");
    List<Document> all = collection.find().into(new ArrayList<Document>());
    for (Document cur : all){
      printJson(cur);
    }
    
    System.out.println("Find all with iteration: ");
    MongoCursor<Document> cursor = collection.find().iterator();
    try {
      while(cursor.hasNext()){
        Document cur = cursor.next();
        printJson(cur);
      }
    } finally {
      cursor.close();
    }
    
    System.out.println("Count: ");
    long count = collection.count();
    System.out.println(count);
  }
}

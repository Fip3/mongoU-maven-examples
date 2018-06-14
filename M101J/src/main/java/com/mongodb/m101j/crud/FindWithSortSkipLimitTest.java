/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Indexes.ascending;
import static com.mongodb.client.model.Indexes.descending;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.orderBy;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author ftorrejon
 */
public class FindWithSortSkipLimitTest {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase database = client.getDatabase("course");
    MongoCollection<Document> collection = database.getCollection("findWithSortSkipLimitTest");

    collection.drop();

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        collection.insertOne(new Document()
                .append("i", i)
                .append("j", j));
      }
    }
    
    Bson projection = fields(include("i","j"),excludeId());
    //Bson sort = new Document("i",1).append("j",-1);
    Bson sort = orderBy(ascending("i"), descending("j"));
    
    List<Document> all = collection.find()
            .projection(projection)
            .sort(sort)
            .skip(20)
            .limit(50)
            .into(new ArrayList<Document>());
    
    for (Document cur : all) {
      printJson(cur);
    }
  }
}

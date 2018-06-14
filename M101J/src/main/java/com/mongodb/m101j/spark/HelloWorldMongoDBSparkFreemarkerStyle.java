/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.m101j.spark;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import java.io.StringWriter;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 *
 * @author ftorrejon
 */
public class HelloWorldMongoDBSparkFreemarkerStyle {
  public static void main(String[] args) {
    final Configuration configuration = new Configuration(new Version(2,3,28));
    configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreemarkerStyle.class, "/");
    
    MongoClient client = new MongoClient();
    MongoDatabase database = client.getDatabase("course");
    final MongoCollection<Document> collection = database.getCollection("hello");
    
    collection.drop();
    
    collection.insertOne(new Document("name","MongoDB"));
    
    Spark.get("/", new Route(){
      @Override
      public Object handle(final Request request, final Response response) {
        StringWriter writer = new StringWriter();
        try {
          Template helloTemplate = configuration.getTemplate("hello.ftl");
          
          Document document = collection.find().first();
          helloTemplate.process(document, writer);
          
        } catch (Exception e) {
          Runtime.getRuntime().halt(500);
          e.printStackTrace();
        }
        
        return writer;
      }
      
    });
  }
  
}

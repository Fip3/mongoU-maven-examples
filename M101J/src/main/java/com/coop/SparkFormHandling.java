/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coop;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 *
 * @author ftorrejon
 */
public class SparkFormHandling {
  public static void main(String[] args) {
    final Configuration configuration = new Configuration(new Version(2,3,28));
    configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");
    
    Spark.get("/", new Route(){
      @Override
      public Object handle(Request rqst, Response rspns) {
        try {
          Map<String, Object> fruitsMap = new HashMap<>();
          fruitsMap.put("fruits",Arrays.asList("apple","orange","banana","peach"));
          Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
          StringWriter writer = new StringWriter();
          fruitPickerTemplate.process(fruitsMap, writer);
          
          return writer;
          
        } catch (Exception e){
          Runtime.getRuntime().halt(500);
          e.printStackTrace();
          return null;
        }
      }
    });
    
    Spark.post("/favorite_fruit", new Route(){
      @Override
      public Object handle(Request request, Response response) {
        final String fruit = request.queryParams("fruit");
        if (fruit == null) {
          return "Why don't you pick one?";
        } else {
          return "Your favorite fruit is " + fruit;
        }
      }
    });
  }
}

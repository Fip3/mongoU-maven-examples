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
public class HelloWorldSparkFreemarkerStyle {
  public static void main(String[] args) {
    final Configuration configuration = new Configuration(new Version(2,3,28));
    configuration.setClassForTemplateLoading(
            HelloWorldFreemarkerStyle.class,"/");
    
    Spark.get("/", new Route(){
      @Override
      public Object handle(Request rqst, Response rspns) throws Exception {
        StringWriter writer = new StringWriter();
        try {
          Template helloTemplate = configuration.getTemplate("hello.ftl");
          Map<String,Object> helloMap = new HashMap<>();
          helloMap.put("name", "Freemarker");

          helloTemplate.process(helloMap, writer);

          System.out.println(writer);

        } catch (Exception e) {
          Runtime.getRuntime().halt(500);
          e.printStackTrace();
        }
        return writer;
      }
    });
  }
}

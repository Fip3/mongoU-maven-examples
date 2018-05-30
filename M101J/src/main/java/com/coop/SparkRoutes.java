/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coop;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 *
 * @author ftorrejon
 */
public class SparkRoutes {
  public static void main(String[] args) {
    Spark.get("/", new Route(){
      @Override
      public Object handle(final Request request, final Response response){
        return "Hello World at Root";
      }
    });
    
    Spark.get("/test", new Route(){
      @Override
      public Object handle(final Request request, final Response response){
        return "Page Test";
      }
    });
    
    Spark.get("/echo/:thing", new Route(){
      @Override
      public Object handle(final Request request, final Response response){
        return request.params(":thing");
      }
    });
  }
}

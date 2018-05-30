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
public class HelloWorldSparkStyle {
  public static void main(String[] args) {
    Spark.get("/", new Route(){
      @Override
      public Object handle(Request rqst, Response rspns) throws Exception {
        return "Hello World from Spark!";
      }
    });
  }
}

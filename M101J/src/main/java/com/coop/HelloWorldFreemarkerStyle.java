/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coop;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.IOException;
import java.io.StringWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ftorrejon
 */
public class HelloWorldFreemarkerStyle {
  public static void main(String[] args) {
    Configuration configuration = new Configuration(new Version(2,3,28));
    configuration.setClassForTemplateLoading(
            HelloWorldFreemarkerStyle.class,"/");
    
    try {
      Template helloTemplate = configuration.getTemplate("hello.ftl");
      StringWriter writer = new StringWriter();
      Map<String,Object> helloMap = new HashMap<>();
      helloMap.put("name", "Freemarker");
      
      helloTemplate.process(helloMap, writer);
      
      System.out.println(writer);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

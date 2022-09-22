//package com.advancia.stage.controller;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//
//import io.swagger.jaxrs.config.BeanConfig;
//
//public class Bootstrap extends HttpServlet {
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//
//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setTitle("Pizzeria da Dylan"); // funziona
//        beanConfig.setVersion("1.0.69");
//        beanConfig.setSchemes(new String[]{"http"});
//        beanConfig.setHost("localhost:8080");
//        beanConfig.setBasePath("/api");
//        beanConfig.setResourcePackage("io.swagger.resources");
//        beanConfig.setScan(true);
//    }
//}
package com.advancia.stage.controller;

import javax.servlet.http.HttpServlet;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.model.ApiInfo;

public class Bootstrap extends HttpServlet {
  static {
        ApiInfo info = new ApiInfo(
          "Pizzeria Swagger",                             /* title */
          "Esempio di server Swagger con Java, senza Spring.  You can find out more about Swagger " + 
          "at <a href=\"http://swagger.wordnik.com\">http://swagger.wordnik.com</a> or on irc.freenode.net, #swagger.  For this sample, " + 
          "you can use the api key \"special-key\" to test the authorization filters", 
          "http://helloreverb.com/terms/",                  /* TOS URL */
          "dylan.tangredi@advancia.it",                            /* Contact */
          "Apache 2.0",                                     /* license */
          "http://www.apache.org/licenses/LICENSE-2.0.html" /* license URL */
        );

//        ConfigFactory.config.setApiInfo(info);
        ConfigFactory.config().setApiInfo(info);
    }
}
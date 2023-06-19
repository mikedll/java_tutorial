package com.mikedll.javatutorial;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.javatuples.Pair;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.StatusLine;

public class Application {

    static final Logger logger = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        logger.info("Entering application.");
         
        System.out.println("Hello everyone");

        test1();
    }

    public static void test1() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet("https://jsonplaceholder.typicode.com/todos/1");
            httpget.addHeader("Accept", "application/json");
            
            Object result = httpclient.execute(httpget, response -> {
                System.out.println("----------------------------------------");
                System.out.println(httpget + "->" + new StatusLine(response));
                // System.out.println(EntityUtils.toString(response.getEntity()));
                logger.info("Response -> {}", EntityUtils.toString(response.getEntity()));
                // response.getCode()
                return null;
            });
        } catch (IOException ex) {
            throw new RuntimeException("Error during fetch request", ex);
        }
    }
}

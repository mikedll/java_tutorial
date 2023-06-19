package com.mikedll.javatutorial;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.javatuples.Pair;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class Application {

    static Logger logger;
    
    public static void main(String[] args) {
        ConfigurationFactory.setConfigurationFactory(new LoggingConfigurationFactory());
        logger = LoggerFactory.getLogger(Application.class);

        
            
        logger.info("Entering application.");
                
        // test1();
        test2();
        test3();
        test4();
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

    public static void test2() {
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("client_id", "AAA"));
        list.add(new BasicNameValuePair("client_secret", "BBB"));

        URI uri = null;
        try {
            uri = new URIBuilder("http://mikedll.com/hello/world").addParameters(list).build();
        } catch (URISyntaxException ex) {
            throw new RuntimeException("Error in uri construction", ex);
        }
        logger.info("uri: " + uri);
    }
    
    public static void test3() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httppost = new HttpPost("https://jsonplaceholder.typicode.com/todos");
            // httppost.addHeader("Accept", "application/json");

            final List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("title", "Buy a bank"));
            params.add(new BasicNameValuePair("completed", "false"));
            httppost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
            
            Object result = httpclient.execute(httppost, response -> {
                System.out.println("----------------------------------------");
                System.out.println(httppost + "->" + new StatusLine(response));
                // System.out.println(EntityUtils.toString(response.getEntity()));
                logger.info("Response -> {}", EntityUtils.toString(response.getEntity()));
                // response.getCode()
                return null;
            });
        } catch (IOException ex) {
            throw new RuntimeException("Error during fetch request", ex);
        }
    }

    public static void test4() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httppost = new HttpPost("https://jsonplaceholder.typicode.com/todos");
            httppost.addHeader("Content-Type", "application/json");

            String json = "{\"title\":\"buy a boat\",\"completed\":false}";
            StringEntity entity = new StringEntity(json);
            httppost.setEntity(entity);
            
            Object result = httpclient.execute(httppost, response -> {
                System.out.println("----------------------------------------");
                System.out.println(httppost + "->" + new StatusLine(response));
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

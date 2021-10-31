package com.example.springproj4;


import java.io.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTest {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

//    String api_key = "";
//    FileReader fr;
//    {
//        try {
//            fr = new FileReader("nytimes_apikey.txt");
//            int i;
//            while ((i = fr.read()) != -1)
//                api_key += (char)i;
//        } catch (IOException e) { e.printStackTrace();}
//    }

    public String api_url = "https://api.nytimes.com/svc/books/v3/lists.json?list=combined-print-and-e-book-nonfiction&api-key=BpLowO3kAtHhf0gRmBG6nGcHEzGjMdw1";

    @Test
    public void apiTest() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(api_url, HttpMethod.GET, entity, String.class);
        boolean result = true;
        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray booklist = (JSONArray) jsonResponse.get("results");
        Integer rankBefore = 0;
        int i = 0;
        while(i < booklist.length()) {
            JSONObject allAboutBook = (JSONObject) booklist.get(i);
            Integer rank = (Integer) allAboutBook.get("rank");
            JSONArray bookDetails = (JSONArray) allAboutBook.get("book_details");
            JSONObject book = (JSONObject) bookDetails.get(0);
            String name = book.getString("title");
            if (name.isEmpty() || rank != rankBefore + 1) {
                result = false;
                break;
            }
            rankBefore = rank;
            i++;
        }
        assertTrue(result);
    }
}

package com.example.springproj5;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Springproj5ApplicationTests {
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	String api_key = "";
    FileReader fr;
    {
        try {
            fr = new FileReader("firebase_web_api_key.txt");
            int i;
            while ((i = fr.read()) != -1)
                api_key += (char)i;
        } catch (IOException e) { e.printStackTrace();}
    }

	String FIREBASE_SIGNIN = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=";
	String FIRESTORE_USERS = "https://firestore.googleapis.com/v1/projects/fir-project-ed43f/databases/(default)/documents/users/";
	


	void userTest() throws JSONException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("email", "vusalvusal2021@gmail.com");
		jsonObj.put("password", "hahaha@2021");
		jsonObj.put("returnSecureToken", true);

		HttpEntity<String> entity = new HttpEntity<>(jsonObj.toString(), headers);
		ResponseEntity<String> response = restTemplate.exchange(FIREBASE_SIGNIN + api_key, HttpMethod.POST, entity, String.class);

		JSONObject responseBody = new JSONObject(response.getBody());
		String idToken = responseBody.getString("idToken");
		String localId = responseBody.getString("localId");

		HttpHeaders headers2 = new HttpHeaders();
		headers2.put("Authorization", Collections.singletonList("Bearer ".concat(idToken)));
		HttpEntity<String> entity2 = new HttpEntity<>(null, headers2);
		ResponseEntity<String> response2 = restTemplate.exchange(FIRESTORE_USERS + localId, HttpMethod.GET, entity2, String.class);

		JSONObject responseBody2 = new JSONObject(response2.getBody());
		JSONObject fields = responseBody2.getJSONObject("fields");

		JSONObject name = fields.getJSONObject("name");
		JSONObject surname = fields.getJSONObject("surname");
		JSONObject phone = fields.getJSONObject("phone");

		assertEquals("Helloouu", name.getString("stringValue"));
		assertEquals("Hacker", surname.getString("stringValue"));
		assertEquals("0707070707", phone.getString("stringValue"));


	}

}

package com.example.springproj7;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;

import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class Springproj7ApplicationTests {
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

	void userTest() throws JSONException, IOException, InterruptedException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("email", "vusalvusal2021@gmail.com");
		jsonObj.put("password", "hahaha@2021");
		jsonObj.put("returnSecureToken", true);

		String authResponse = UtilityFunctions.post(FIREBASE_SIGNIN + api_key, jsonObj);
		JSONObject responseBody = new JSONObject(authResponse);
		String idToken = responseBody.getString("idToken");
		String localId = responseBody.getString("localId");

		String avatarFieldUrl = String.format("%s/%s?updateMask.fieldPaths=avatar", FIRESTORE_USERS, localId);
		JSONObject avatarData = new JSONObject();
		JSONObject fieldsObject = new JSONObject();
		JSONObject avatarObject = new JSONObject();
		avatarObject.put("stringValue", "");
		fieldsObject.put("avatar", avatarObject);
		avatarData.put("fields", fieldsObject);

		UtilityFunctions.patch(avatarFieldUrl, idToken, avatarData.toString());
		String usersUrl = String.format("%s/%s", FIRESTORE_USERS, localId);
		String userDetails = UtilityFunctions.get(usersUrl, idToken);
		String avatarReference = new JSONObject(userDetails).getJSONObject("fields").getJSONObject("avatar").getString("stringValue");
		if (!avatarReference.isEmpty()) fail();

		idToken="";
		String userDetailsLoggedOut = UtilityFunctions.get(usersUrl, idToken);
		assertEquals("Forbidden", userDetailsLoggedOut);
	}
}

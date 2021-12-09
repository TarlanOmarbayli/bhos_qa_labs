package com.example.springproj6;

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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Springproj6ApplicationTests {
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
	String FIREBASE_STORAGE = "https://firebasestorage.googleapis.com/v0/b/fir-project-ed43f.appspot.com/o";


	void userTest() throws JSONException, IOException, InterruptedException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("email", "vusalvusal2021@gmail.com");
		jsonObj.put("password", "hahaha@2021");
		jsonObj.put("returnSecureToken", true);
		String authResponse = UtilityFunctions.post(FIREBASE_SIGNIN + api_key, jsonObj);

		JSONObject responseBody = new JSONObject(authResponse);
		String idToken = responseBody.getString("idToken");
		String localId = responseBody.getString("localId");

		String uploadUrl = String.format("%s/%s%%2F%s?alt=media&token=%s", FIREBASE_STORAGE, localId, "avatar.jpg", System.getenv("ACCESS_TOKEN"));
		String uploadedFile = UtilityFunctions.uploadFile(uploadUrl, idToken, "avatar.jpg");
		String fileReference = new JSONObject(uploadedFile).getString("name");

		String avatarFieldUrl = String.format("%s/%s?updateMask.fieldPaths=avatar", FIRESTORE_USERS, localId);
		JSONObject avatarData = new JSONObject();
		JSONObject fieldsObject = new JSONObject();
		JSONObject avatarObject = new JSONObject();
		avatarObject.put("stringValue", fileReference);
		fieldsObject.put("avatar", avatarObject);
		avatarData.put("fields", fieldsObject);

		UtilityFunctions.patch(avatarFieldUrl, idToken, avatarData.toString());

		String usersUrl = String.format("%s/%s", FIRESTORE_USERS, localId);
		String userDetails = UtilityFunctions.get(usersUrl, idToken);
		String avatarReference = new JSONObject(userDetails).getJSONObject("fields").getJSONObject("avatar").getString("stringValue");
		String avatarReferenceUrlEncoded = URLEncoder.encode(avatarReference, StandardCharsets.UTF_8.toString());

		String url = String.format("%s/%s?alt=media&token=%s", FIREBASE_STORAGE, avatarReferenceUrlEncoded, System.getenv("ACCESS_TOKEN"));

		assertEquals(200, UtilityFunctions.check(url, idToken));

	}

}

package com.example.springproj2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Springproj2ApplicationTests {

	public String url = "https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos";

	public void testSSL() throws IOException{
		try {
			URL http_url = new URL(url);
			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) http_url.openConnection();
			httpsURLConnection.connect();

			Certificate[] server_certs = httpsURLConnection.getServerCertificates();
			Certificate cert_to_test = server_certs[0];

			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			FileInputStream inputStream  =  new FileInputStream ("cert.cer");
			X509Certificate localCertificate = (X509Certificate) certFactory.generateCertificate(inputStream);

			assertEquals(localCertificate, cert_to_test);

		} catch (IOException | CertificateException e) {
			e.printStackTrace();
		}
	}

	TestRestTemplate restTemplate = new TestRestTemplate();

	public void testJSONResponse() throws JSONException {
		Set<String> ids = new HashSet<>();
		Set<String> names = new HashSet<>();

		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		JSONArray repositories = new JSONArray(response.getBody());

		int i=0;
		while(i < repositories.length()) {
			JSONObject repository = (JSONObject) repositories.get(i);
			ids.add(repository.getString("id"));
			names.add(repository.getString("name"));
			i++;
		}

		boolean unique = ids.size() == repositories.length() && names.size() == repositories.length();
		assertTrue(unique);
	}

}

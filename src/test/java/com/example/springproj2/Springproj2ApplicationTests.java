package com.example.springproj2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

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

}

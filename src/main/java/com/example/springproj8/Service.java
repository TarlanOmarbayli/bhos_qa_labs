package com.example.springproj8;

import com.nimbusds.srp6.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;

@RestController
public class Service {
    static String verifier = "6767204271782158860771322229044513716412960751120553219637749143978708563343782316896185472711068761617527035250432255495604010400219986682191589592880894";
    static String salt = "4375690236459345";

    public static SRP6ServerSession serverSession;
    public static SRP6CryptoParams config;

    @PostMapping("/first")
    public String first(@RequestBody String userID) {

        config = SRP6CryptoParams.getInstance();
        serverSession = new SRP6ServerSession(config);

        BigInteger S = new BigInteger(salt);
        BigInteger V = new BigInteger(verifier);
        String serverPublicValueB = serverSession.step1(userID, S, V).toString();

        return serverPublicValueB;
    }

    @PostMapping("/second")
    public String second(@RequestBody CompValues compValues) {
        try {
            return serverSession.step2(compValues.A, compValues.M1).toString();
        } catch (SRP6Exception e) {
            return "";
        }
    }

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @GetMapping("/srpLoadTest")
    public ResponseEntity<Object> srpLoadTest() throws SRP6Exception {
        SRP6ClientSession Client = new SRP6ClientSession();
        String userID = "tarlan";
        String password = "hahaha@2021";
        Client.step1(userID, password);

        HttpEntity<String> newSesEntity = new HttpEntity<>(userID, headers);
        String newSesResponse = restTemplate.exchange("http://localhost:8080/first",
                HttpMethod.POST, newSesEntity, String.class).getBody();

        BigInteger serverPublicB = new BigInteger(newSesResponse);

        SRP6CryptoParams defaultCryptoParams = SRP6CryptoParams.getInstance();
        BigInteger saltS = new BigInteger(salt);
        SRP6ClientCredentials clientPublicAEvidenceM1 = Client.step2(defaultCryptoParams, saltS, serverPublicB);

        CompValues compValues = new CompValues(clientPublicAEvidenceM1.A, clientPublicAEvidenceM1.M1);
        HttpEntity<CompValues> compValEntity = new HttpEntity<>(compValues, headers);

        String serverEvidenceM2 = restTemplate.postForEntity("http://localhost:8080/second",
                compValEntity, String.class).getBody();

        if (serverEvidenceM2 == "") {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(serverEvidenceM2);
        }
    }
}
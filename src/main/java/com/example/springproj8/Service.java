package com.example.springproj8;

import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.SRP6Exception;
import com.nimbusds.srp6.SRP6ServerSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;

@RestController
public class Service {
    static String verifier = "6c26bfce5fc6f9da990d9d332c49425e3a1d06259e3a3d2463e7359b365066448dc8a44e3fd54870c82b3591df847f8e60b477ad7890cc3f7544424d489ecc0bd162368aee987ef1004eb267874bd3ce910e2174a5e47e0a102bf6d57dcef2e21d0100afeb40b4a5207e057be1baa913a23cc93247bcdeb57d754fddc69d75f504e1e993879e77d9b23c48e542c6ff37cd35213df94dad12c29e7526b47c07aae0e98d3f49ded88605357370c952d3c39148f1c89ef814c0947de430d1f5e507e3db9dea38ac25c628248f479551b5e55b904477ba36c51a6ae268ca57695a0216850c85c970a5c33e76b9d0c968d50b980cd78c54bd64cfddf685bc494f59e8";
    static String salt = "5ceb06b3813a61a9e646547b92a1bae4118e41a793764b1ce3fb8c49a7c26f88";

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
}
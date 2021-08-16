package com.demobackend.demo.security;

import com.demobackend.demo.exceptions.InvalidTokenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;

@Component
public class GoogleTokenVerifier {

    @Value("${GOOGLE_CLIENT_ID}")
    private String clientId;

    private static final HttpTransport transport = new NetHttpTransport();
    private static final JsonFactory jsonFactory = new JacksonFactory();


    public boolean isValid(String idTokenString) {
        try {
            return verify(idTokenString) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public Payload verify(String idTokenString)
            throws GeneralSecurityException, IOException, InvalidTokenException {
        return GoogleTokenVerifier.verifyToken(idTokenString, clientId);
    }

    private static Payload verifyToken(String idTokenString, String clientId)
            throws GeneralSecurityException, IOException, InvalidTokenException {
        final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.
                Builder(transport, jsonFactory)
                .setIssuers(Arrays.asList("https://accounts.google.com", "accounts.google.com"))
                .setAudience(Collections.singletonList(clientId))
                .build();


        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idTokenString);
        } catch (IllegalArgumentException e){
            // means token was not valid and idToken
            // will be null
        }

        if (idToken == null) {
            throw new InvalidTokenException("idToken is invalid");
        }

        return idToken.getPayload();
    }
}

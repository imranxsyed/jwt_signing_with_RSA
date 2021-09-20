package com.okta.createverifytokens;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;

import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JWTDemoTest {

    private static final Logger logger = LogManager.getLogger();
    @Test
    public void createAndDecodeJWT() {

        //JWT
        String jwt = JWTDemo.createJWTSignedToken();
        //JWT CLAIM
        Claims claims = JWTDemo.decodeRSA(jwt);
        //JWT HEADER
        JwsHeader header = JWTDemo.decodeJWTHeaderRSA(jwt);

        logger.info("Header = " + header);
        logger.info("jwt = \"" + jwt + "\"");
        logger.info("claims = " + claims.toString());

    }

}
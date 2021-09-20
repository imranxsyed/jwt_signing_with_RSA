package com.okta.createverifytokens;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

/*
    Our simple static class that demonstrates how to create and decode JWTs.
 */
public class JWTDemo {
    //PRIVATE RSA KEY
    private static final String SECRET_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDcOzSNMCOoR0f6" +
            "viUdF765Jd7UH6Fdfj3g3AgofQClyBMYS0XQ7C19F6AEoNpccxKxTtNp9iZE2A/Q" +
            "Z8gUSV0uP8PqV4CN5xdQBwmolbhwHiMwTGECQTWTOkbC0aN8P9Bptzsv5y1FAEn6" +
            "OAvr6cIqu6QhPaVQ+ziKHeF94qr2emKkPdfD8Gi5WTxlUrg3mnv+/4/20hHoXN3c" +
            "4IV4gm5MEGso/r0JMS06tva380DNtdolMm6huMKmtp2vZzZ4Jf9P1e0DSIKHY08p" +
            "jCC0Qk5Es2hbHiwXKYiCrJRIJkuaXo/WXohh3PuxPS97zibFcYxiU9D++cyTkT1v" +
            "lWh2E/MHAgMBAAECggEAfD9ywIz+MPw2IcKQ0b8JvQ8ejfJiaYrFgkzg1WbgrKca" +
            "DsS42MvGRiLEBphxWWMakqn0IH3czU5QbbV/beKODX+wGk/PmT9dKasuycveSHLY" +
            "S3Fr8Ye0oCFqtpLrdzfRbzMO01HLenhVRDPGlgxFnTJLwO00jWGOXm3JP+pXpcIN" +
            "pMPLWxOvS053BapTcKlai2pLUzHCp6Io5WK4GBIpY88/bfxb2f2zY3CgBiEMbrmt" +
            "jRUVt+f3RV1nYggHcdXo5zAlmNrJyTvtedDapcvsaGYZYBWDemFHE683IqEmMzuD" +
            "w3+FbNfYV9dHy29EcxEkbf1Hdz7JxMziZizBtmFewQKBgQD/5tABI9It4gz0GQ0J" +
            "e4mp40p+rsXDXBQ2vQnTDSz3e1FY02mBp00N8Y34ecAValc6THUiKC5iwls/loc8" +
            "GLQFNxlCxMiGpQSrT2JAMuctvy8+penO82X/V6JL2MFLL4+w6RB63/TwC7fqKUtv" +
            "bpWxYbXqMh1Hf4w7k7UCyn8e9wKBgQDcUOHBazaSQPv6Y/9ieB/BAEwR8x0JJe5H" +
            "qZG5zvOUUyT+t7VRYj2KTkUQ3NufC2xGtF8Pe7PzX43WNwLkMKGNVImP4qHrfROi" +
            "/tRkiZBJU/5stwa5q34Q+nmHVPUinjngv3JazVNJZSHK2XYvVwqp6zXh5QKCswNI" +
            "J9mj68j4cQKBgFQnY1/8l7HfFMNxOYhUyADGbkka0I5eSYd2wzRUsOTx+N5tJ9Ur" +
            "X3umZDnCUBi6z9SsMBj7fcWchMV0iySIOys742LBs/ATdGWhxX23P68boD3J8gnM" +
            "oU5HU2f98BL5oRdcjb7ax4HpWKH40oXip/edWmWZ9Wb92u/8sxon3YYhAoGANnB9" +
            "+E9Hwsfv+6Ut++T247fxFBZgvFv1DxwleCZdJe+NmqtGUH/X+Q7yHgKU+i3WaXo0" +
            "ax0EEEUyRRZ+zmf943BaNYWowQm9Cm+xh84+KwBoQ/DN5p8rnYJm+TvS/uTeJFzq" +
            "IunNhLtKqFcnzvbRGb+XJuRxxF37l57RNqISnOECgYEAy3ScwAx9Ddh2sqtcnNyn" +
            "wEZS8zEYBkZmpAPJawm9tHMkT7oZE7y4YjM04sovg8I33SL+vk2zqR3Czlu8YgoM" +
            "c4pFjjIUzX8Xxuaghy4vAGmhx0hFt11Yy8ltCAltuIaAkCNbRt4jouYY6kFzEniZ" +
            "bmy4x1dIguvZg3K1fhWnUrA=";



    public static Claims decodeRSA(String jwt) {

        String privateKey = SECRET_KEY;
        privateKey = privateKey.replaceAll("\\s+","");

        byte[] encodedKey = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
        Claims claims = null;
        try{
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privKey = kf.generatePrivate(keySpec);

             claims = Jwts.parser()
                    .setSigningKey(privKey)
                    .parseClaimsJws(jwt).getBody();
        }
        catch (Exception e){
            e.printStackTrace();
        }



        return claims;
    }

    public static JwsHeader decodeJWTHeaderRSA(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        String privateKey = SECRET_KEY;
        privateKey = privateKey.replaceAll("\\s+","");

        byte[] encodedKey = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
        JwsHeader claims = null;
        try{
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privKey = kf.generatePrivate(keySpec);

            claims = Jwts.parser()
                    .setSigningKey(privKey)
                    .parseClaimsJws(jwt).getHeader();
        }
        catch (Exception e){
            e.printStackTrace();
        }



        return claims;

    }


    public static String createJWTSignedToken() {

        String retStr = null;

        Claims claims = Jwts.claims();
        claims.put("sub", "13f7982d-1f78-46e2-a843-3273568fce89");
        claims.put("iss", "https://www.google.com");
        claims.put("preferred_username", "JonDoe");
        claims.put("phone_number", "+1-10-344-3765333");
        claims.put("iat", 1632152524L);
        claims.put("exp", 1758397307L);


        // strip the headers
        String privateKey = SECRET_KEY;
        privateKey = privateKey.replaceAll("\\s+","");

        byte[] encodedKey = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);

        try {

            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privKey = kf.generatePrivate(keySpec);

            retStr = Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.RS256,privKey)
                    .compact();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return retStr;
    }



}

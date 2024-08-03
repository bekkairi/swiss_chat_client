package com.swiss.sharing.client.service.security;


import com.swiss.sharing.client.service.exceptions.UserDigestServiceException;
import lombok.extern.slf4j.Slf4j;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Slf4j
public class SecurityUtil {
    private static final String ALGORITHM = "RSA";

    private SecurityUtil() {

    }

    public static String hashWith512(String textToHash) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] byteOfTextToHash = textToHash.getBytes(StandardCharsets.UTF_8);
            byte[] hashedByetArray = digest.digest(byteOfTextToHash);
            return Base64.getEncoder().encodeToString(hashedByetArray);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
            throw new UserDigestServiceException(e);
        }
    }

    public static String saveKeysToBase64() {
        Base64.Encoder encoder = Base64.getEncoder();
        StringWriter st = new StringWriter();
        st.write(encoder.encodeToString(generateKeyPair().getPrivate().getEncoded()));
        return st.toString();
    }

    public static String publicKeyToBase64(String private64Key) {

        PublicKey publicKey = publicKexFromString(private64Key);

        StringWriter st = new StringWriter();
        st.write(Base64.getEncoder().encodeToString(publicKey.getEncoded()));

        return st.toString();

    }


    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");

            keyGen.initialize(2048, random);

            KeyPair generateKeyPair = keyGen.generateKeyPair();

            return generateKeyPair;
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            log.error(e.getMessage(), e);
            throw new UserDigestServiceException(e);
        }


    }

    private static PublicKey publicKexFromString(String private64Key) {

        try {
            Base64.Decoder encoder = Base64.getDecoder();
            byte[] privateKey = encoder.decode(private64Key);

            PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(privateKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            var rsaPrivateKey = kf.generatePrivate(privateSpec);

            RSAKey rsaKey = (RSAKey) rsaPrivateKey;
            RSAPrivateCrtKey rsaPrivateCrtKey = (RSAPrivateCrtKey) rsaPrivateKey;


            PublicKey publicKey = kf.generatePublic(new RSAPublicKeySpec(rsaKey.getModulus(), rsaPrivateCrtKey.getPublicExponent()));

            return publicKey;


        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error(e.getMessage(), e);
            throw new UserDigestServiceException(e);
        }
    }
}

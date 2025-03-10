package com.sea.reporter.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Utility class for RSA encryption, decryption, and digital signatures.
 * Handles RSA key operations and cryptographic functions using configured public/private keys.
 */
@Component
public class RSAUtil {

    @Value("${organization.api.public.key}")
    private String publicKey;

    @Value("${organization.api.private.key}")
    private String privateKey;

    /**
     * Converts a Base64 encoded public key string to a PublicKey object.
     *
     * @param base64PublicKey Base64 encoded public key string
     * @return PublicKey object
     * @throws RuntimeException if key generation fails
     */
    public static PublicKey getPublicKey(String base64PublicKey) {
        try {
            PublicKey publicKey = null;
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error generating public key", e);
        }
    }

    /**
     * Converts a Base64 encoded private key string to a PrivateKey object.
     *
     * @param base64PrivateKey Base64 encoded private key string
     * @return PrivateKey object
     * @throws RuntimeException if key generation fails
     */
    public static PrivateKey getPrivateKey(String base64PrivateKey) {
        try {
            PrivateKey privateKey = null;
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
            privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error generating private key", e);
        }
    }

    /**
     * Encrypts data using RSA public key encryption.
     *
     * @param data String to encrypt
     * @return Encrypted byte array
     * @throws BadPaddingException if padding is invalid
     * @throws IllegalBlockSizeException if block size is invalid
     * @throws InvalidKeyException if key is invalid
     * @throws NoSuchPaddingException if padding scheme is not available
     * @throws NoSuchAlgorithmException if algorithm is not available
     */
    public byte[] encrypt(String data) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }

    /**
     * Decrypts data using RSA private key decryption.
     *
     * @param data Encrypted byte array to decrypt
     * @return Decrypted string
     * @throws NoSuchPaddingException if padding scheme is not available
     * @throws NoSuchAlgorithmException if algorithm is not available
     * @throws InvalidKeyException if key is invalid
     * @throws BadPaddingException if padding is invalid
     * @throws IllegalBlockSizeException if block size is invalid
     */
    public String decrypt(byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
        return new String(cipher.doFinal(data));
    }

    /**
     * Decrypts Base64 encoded encrypted data using RSA private key decryption.
     *
     * @param data Base64 encoded encrypted string to decrypt
     * @return Decrypted string
     * @throws IllegalBlockSizeException if block size is invalid
     * @throws InvalidKeyException if key is invalid
     * @throws BadPaddingException if padding is invalid
     * @throws NoSuchAlgorithmException if algorithm is not available
     * @throws NoSuchPaddingException if padding scheme is not available
     */
    public String decrypt(String data) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(Base64.getDecoder().decode(data.getBytes()));
    }

    /**
     * Generates a SHA1withRSA digital signature for the given data.
     *
     * @param s String to sign
     * @param keyString Key string for signature generation
     * @return Base64 encoded signature
     * @throws UnsupportedEncodingException if UTF-8 encoding is not supported
     * @throws NoSuchAlgorithmException if algorithm is not available
     * @throws InvalidKeyException if key is invalid
     * @throws SignatureException if signature operation fails
     * @throws InvalidKeySpecException if key specification is invalid
     */
    public String sha1(String s, String keyString) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {
        Signature sig = Signature.getInstance("SHA1withRSA");
        PrivateKey esbPrivatekey = KeyFactory.getInstance("RSA").generatePrivate(
                new RSAPrivateKeySpec(
                        new java.math.BigInteger(keyString),
                        new java.math.BigInteger("65537")
                )
        );
        sig.initSign(esbPrivatekey);
        sig.update(s.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(sig.sign());
    }
}

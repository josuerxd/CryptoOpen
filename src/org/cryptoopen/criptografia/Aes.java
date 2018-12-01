/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cryptoopen.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
/**
 *
 * @author Josue Daniel Roldan Ochoa.
 */
public class Aes {
private static final int keySize=128;
private static final int iterationCount=10000;
private static Cipher cipher; 
    public Aes(){
          try {
cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
}catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
System.out.println(e.getMessage());
        }
    }
 
   public static String encrypt(String salt, String iv, String passphrase, String plaintext) throws IllegalBlockSizeException, BadPaddingException {
      byte[] encrypted=null;  
       
    try {
            SecretKey key = generateKey(salt, passphrase);
         encrypted = doFinal(Cipher.ENCRYPT_MODE, key, iv, plaintext.getBytes("UTF-8"));
         base64(encrypted);
        }
        catch (UnsupportedEncodingException e) {
  System.out.println(e.getMessage());
        }
           return  base64(encrypted);
    }
    
public static String decrypt2(String salt, String iv, String passphrase, String ciphertext) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    byte[] decrypted=null;  
    try {
            SecretKey key = generateKey(salt, passphrase);
           decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64(ciphertext));
            return new String(decrypted, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
        
        }
  return new String(decrypted, "UTF-8");
    }
    
    private static byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) throws IllegalBlockSizeException, BadPaddingException {
          
        try {
            cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
            return cipher.doFinal(bytes);
        }
        catch (InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException e) {
          
        }
    return cipher.doFinal(bytes);
    }
    
    private static SecretKey generateKey(String salt, String passphrase) {
        SecretKey key=null ;
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
            key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
            return key;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
     
        }
    return key;
    }
    
    private static String random(int length) {
        byte[] salt = new byte[length];
        new SecureRandom().nextBytes(salt);
        return hex(salt);
    }
    
    private static String base64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
    
    private static byte[] base64(String str) {
        return Base64.decodeBase64(str);
    }
    
    private static String hex(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }
    
    private static byte[] hex(String str) {
        try {
            return Hex.decodeHex(str.toCharArray());
        }
        catch (DecoderException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private IllegalStateException fail(Exception e) {
        return new IllegalStateException(e);
    }
    
 
}

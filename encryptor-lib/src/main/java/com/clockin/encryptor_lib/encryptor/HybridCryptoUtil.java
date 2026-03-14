package com.clockin.encryptor_lib.encryptor;

import javax.crypto.SecretKey;

import java.security.PrivateKey;
import java.security.PublicKey;

public class HybridCryptoUtil {
	/**
     * CLIENT SIDE
     * Encrypt JSON using AES
     * Encrypt AES key using RSA Public Key
     */
    public static EncryptedRequest encrypt(String json, PublicKey publicKey, PrivateKey privateKey) throws Exception {
    	System.out.println("HybridCryptoUtil:encrypt");

        // 1️⃣ Generate AES key
        SecretKey aesKey = AesUtil.generateKey();

        // 2️⃣ Encrypt data using AES
        String encryptedData = AesUtil.encrypt(json, aesKey);

        // 3️⃣ Encrypt AES key using RSA public key
        String encryptedKey = RsaUtil.encryptKey(aesKey, publicKey);
        
        String signature = DigiSignUtil.sign(encryptedData,privateKey);

        // 4️⃣ Return wrapper
        return new EncryptedRequest(encryptedKey, encryptedData, signature);
    }

    /**
     * SERVER SIDE
     * Decrypt AES key using RSA Private Key
     * Decrypt data using AES key
     */
    public static String decrypt(EncryptedRequest request, PrivateKey privateKey, PublicKey publicKey) throws Exception {

    	boolean validSignature = DigiSignUtil.verify(
    			request.getData(),
    			request.getSignature(), 
    			publicKey
    			);
    	
    	if (!validSignature) {
            throw new SecurityException("Invalid Digital Signature!");
        }
    	
        // 1️⃣ Decrypt AES key
        SecretKey aesKey = RsaUtil.decryptKey(request.getKey(), privateKey);

        // 2️⃣ Decrypt payload
        return AesUtil.decrypt(request.getData(), aesKey);
    }

}

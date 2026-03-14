package com.clockin.encryptor_lib.encryptor;

public class EncryptedRequest {

    private String encryptedKey;
    private String cipherText;
    private String signature;
    
    public EncryptedRequest() {}

	public EncryptedRequest(String encryptedKey, String cipherText, String signature) {
        this.encryptedKey = encryptedKey;
        this.cipherText = cipherText;
        this.signature = signature;
    }

	public String getEncryptedKey() {
		return encryptedKey;
	}

	public void setEncryptedKey(String encryptedKey) {
		this.encryptedKey = encryptedKey;
	}

	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

   
}



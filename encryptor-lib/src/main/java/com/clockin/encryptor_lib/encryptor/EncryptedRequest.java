package com.clockin.encryptor_lib.encryptor;

public class EncryptedRequest {

    private String key;
    private String data;
    private String signature;

	public EncryptedRequest(String key, String data, String signature) {
        this.key = key;
        this.data = data;
        this.signature = signature;
    }

    public String getKey() { return key; }
    public String getData() { return data; }
    public String getSignature() { return signature; }

    public void setKey(String key) { this.key = key; }
    public void setData(String data) { this.data = data; }
    public void setSignature(String signature) { this.signature = signature; }
}



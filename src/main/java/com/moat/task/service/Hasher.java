package com.moat.task.service;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.UUID;

public class Hasher {
    private static final String HASH_ALGORITHM = "SHA-512";

    public String hash(String value, UUID salt) {
        byte[] hashValue = value.getBytes();
        byte[] hashSalt = salt.toString().getBytes();
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch(Exception ex) {
            throw new RuntimeException();
        }
        digest.update(hashValue);
        digest.update(hashSalt);
        byte[] hash = digest.digest();
        return Base64.getEncoder().encodeToString(hash);
    }
}

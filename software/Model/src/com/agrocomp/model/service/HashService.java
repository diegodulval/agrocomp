package com.agrocomp.model.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {

    private final static String PREFIX = "";

    private final static String SUFIX = "";

    public static String encode(String original) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        original = PREFIX + original + SUFIX;
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(original.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        String hash = hexString.toString();
        return hash;

    }

}

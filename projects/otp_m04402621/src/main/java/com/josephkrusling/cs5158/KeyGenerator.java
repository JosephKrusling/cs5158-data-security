package com.josephkrusling.cs5158;

import java.security.SecureRandom;

public class KeyGenerator {

    private static final SecureRandom defaultSecureRandom = new SecureRandom();

    public static boolean[] generateKey(int bitLength, SecureRandom secureRandom) {
        boolean[] out = new boolean[bitLength];
        for (int i = 0; i < bitLength; i++) {
            out[i] = secureRandom.nextBoolean();
        }
        return out;
    }

    public static boolean[] generateKey(int bitLength) {
        return generateKey(bitLength, defaultSecureRandom);
    }

}

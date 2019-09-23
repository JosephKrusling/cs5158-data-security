package com.josephkrusling.cs5158;

import java.security.SecureRandom;

public class KeyGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static boolean[] generateKey(int bitLength) {
        boolean[] out = new boolean[bitLength];
        for (int i = 0; i < bitLength; i++) {
            out[i] = secureRandom.nextBoolean();
        }
        return out;
    }

    public static String keyToString(boolean[] key) {
        StringBuilder out = new StringBuilder();
        for (boolean bit : key) {
            out.append(bit ? "1" : "0");
        }
        return out.toString();
    }

    public static boolean[] stringToKey(String key) {
        char[] keyAsCharArray = key.toCharArray();
        boolean[] out = new boolean[key.length()];
        for (int i = 0; i < keyAsCharArray.length; i++) {
            if (keyAsCharArray[i] == '0') {
                out[i] = false;
            } else if (keyAsCharArray[i] == '1') {
                out[i] = true;
            } else {
                throw new IllegalArgumentException("Key may only contain characters 0 and 1");
            }
        }
        return out;
    }
}

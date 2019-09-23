package com.josephkrusling.cs5158;

public class OneTimePad {

    public static boolean[] encrypt(boolean[] cleartext, boolean[] key) {
        if (cleartext.length != key.length) {
            throw new IllegalArgumentException("Key length must match cleartext length");
        }
        boolean[] encrypted = new boolean[cleartext.length];
        for (int i = 0; i < encrypted.length; i++) {
            encrypted[i] = xor(cleartext[i], key[i]);
        }
        return encrypted;
    }

    public static boolean[] decrypt(boolean[] ciphertext, boolean[] key) {
        return encrypt(ciphertext, key);
    }

    public static String encryptString(String cleartext, String key) {
        return BitConversion.bitArrayToBitString(
                encrypt(
                        BitConversion.asciiStringToBitArray(cleartext),
                        BitConversion.bitStringToBitArray(key)
                )
        );
    }

    public static String decryptString(String ciphertext, String key) {
        return BitConversion.bitArrayToAsciiString(
                encrypt(
                        BitConversion.bitStringToBitArray(ciphertext),
                        BitConversion.bitStringToBitArray(key)
                )
        );
    }

    private static boolean xor(boolean a, boolean b) {
        return (!a || !b) && (a || b);
    }

}

package com.josephkrusling.cs5158;

import java.util.Arrays;

public class BitConversion {

    public static String bitArrayToBitString(boolean[] bitArray) {
        StringBuilder out = new StringBuilder();
        for (boolean bit : bitArray) {
            out.append(bit ? "1" : "0");
        }
        return out.toString();
    }

    public static boolean[] bitStringToBitArray(String bitString) {
        char[] keyAsCharArray = bitString.toCharArray();
        boolean[] out = new boolean[bitString.length()];
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

    public static String bitArrayToAsciiString(boolean[] bitArray) {
        if (bitArray.length % 8 != 0) {
            throw new IllegalArgumentException("Length of bitArray must be a multiple of 8");
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < bitArray.length / 8; i++) {
            boolean[] characterBits = Arrays.copyOfRange(bitArray, i * 8, i * 8 + 8);
            int asciiCode = bitsToAsciiCode(characterBits);
            char asciiCharacter = (char) asciiCode;
            out.append(asciiCharacter);
        }
        return out.toString();
    }

    public static boolean[] asciiStringToBitArray(String asciiString) {
        char[] asciiStringAsChars = asciiString.toCharArray();
        boolean[] out = new boolean[asciiString.length() * 8]; // Ascii is 8 bits per character
        for (int i = 0; i < asciiStringAsChars.length; i++) {
            int charAsAscii = asciiStringAsChars[i];
            boolean[] charAsBits = asciiCodeToBits(charAsAscii);
            System.arraycopy(charAsBits, 0, out, i * 8, 8);
        }
        return out;
    }

    public static int bitsToAsciiCode(boolean[] bits) {
        return (bits[7] ? 1 : 0)
                + (bits[6] ? 2 : 0)
                + (bits[5] ? 4 : 0)
                + (bits[4] ? 8 : 0)
                + (bits[3] ? 16 : 0)
                + (bits[2] ? 32 : 0)
                + (bits[1] ? 64 : 0)
                + (bits[0] ? 128 : 0);
    }

    public static boolean[] asciiCodeToBits(int asciiCode) {
        boolean[] bits = new boolean[8];
        bits[7] = ((asciiCode & 0x01) != 0);
        bits[6] = ((asciiCode & 0x02) != 0);
        bits[5] = ((asciiCode & 0x04) != 0);
        bits[4] = ((asciiCode & 0x08) != 0);
        bits[3] = ((asciiCode & 0x10) != 0);
        bits[2] = ((asciiCode & 0x20) != 0);
        bits[1] = ((asciiCode & 0x40) != 0);
        bits[0] = ((asciiCode & 0x80) != 0);
        return bits;
    }
}

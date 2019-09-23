package com.josephkrusling.cs5158;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class OneTimePadTest {

    @Test
    void encryptAscciString() {
        String message = "go bearcats";
        boolean[] messageBits = BitConversion.asciiStringToBitArray(message);
        boolean[] key = BitConversion.asciiStringToBitArray("gewjklgsljs");
        boolean[] ciphertextBits = OneTimePad.encrypt(messageBits, key);
        String ciphertextBitString = BitConversion.bitArrayToBitString(ciphertextBits);
        String expectedCiphertextBitString = "0000000000001010010101110000100000001110000011010001010100010000000011010001111000000000";
        Assertions.assertEquals(expectedCiphertextBitString, ciphertextBitString);
    }

    @Test
    void encryptionIsReversible() {
        String message = "university of cincinnati";

        boolean[] key = KeyGenerator.generateKey(message.length() * 8);
        boolean[] encryptedBits = OneTimePad.encrypt(BitConversion.asciiStringToBitArray(message), key);
        boolean[] decryptedBits = OneTimePad.decrypt(encryptedBits, key);
        String recoveredMessage = BitConversion.bitArrayToAsciiString(decryptedBits);

        Assertions.assertEquals(message, recoveredMessage);
    }

    @Test
    void encryptString() {
        String message = "cats";
        String key = "01010101101010101111000000001111";
        String expectedCiphertext = "00110110110010111000010001111100";
        Assertions.assertEquals(expectedCiphertext, OneTimePad.encryptString(message, key));
    }

    @Test
    void decryptString() {
        String ciphertext = "00110110110010111000010001111100";
        String key = "01010101101010101111000000001111";
        String expectedMessage = "cats";
        Assertions.assertEquals(expectedMessage, OneTimePad.decryptString(ciphertext, key));
    }

    @Test
    void stringEncryptionIsReversible() {
        String message = "cats";
        String key = "01010101101010101111000000001111";
        String recoveredMessage = OneTimePad.decryptString(
                OneTimePad.encryptString(message, key),
                key
        );
        Assertions.assertEquals(message, recoveredMessage);
    }
}
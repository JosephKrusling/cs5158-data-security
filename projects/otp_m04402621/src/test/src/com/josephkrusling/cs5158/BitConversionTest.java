package com.josephkrusling.cs5158;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitConversionTest {

    @Test
    void bitArrayToBitStringConvertsCorrectly() {
        boolean[] bitArray = new boolean[] {false, false, true, false, true, true};
        String stringified = BitConversion.bitArrayToBitString(bitArray);
        assertEquals("001011", stringified);
    }

    @Test
    void bitArrayToBitStringReturnsEmptyStringOnEmptyInput() {
        boolean[] bitArray = new boolean[] {};
        String stringified = BitConversion.bitArrayToBitString(bitArray);
        assertEquals("", stringified);
    }

    @Test
    void bitStringToBitArrayConvertsCorrectly() {
        String bitString = "101100";
        boolean[] expectedBitArray = new boolean[] {true, false, true, true, false, false};
        Assertions.assertArrayEquals(expectedBitArray, BitConversion.bitStringToBitArray(bitString));
    }

    @Test
    void bitStringToBitArrayFailsOnBadInput() {
        String bitString = "1011a00";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BitConversion.bitStringToBitArray(bitString);
        });
    }


    @Test
    void bitArrayToBitString() {
        String expectedBitString = "011100";
        String actualBitString = BitConversion.bitArrayToBitString(
                new boolean[] { false, true, true, true, false, false }
        );
        Assertions.assertEquals(expectedBitString, actualBitString);
    }

    @Test
    void bitStringToBitArray() {
        boolean[] expectedBitArray = new boolean[] { false, true, true, true, false, false };
        boolean[] actualBitArray = BitConversion.bitStringToBitArray("011100");
        Assertions.assertArrayEquals(expectedBitArray, actualBitArray);
    }

    @Test
    void bitArrayToAsciiString() {
        String expectedString = "hello world";
        String actualString = BitConversion.bitArrayToAsciiString(BitConversion.bitStringToBitArray(
                "0110100001100101011011000110110001101111001000000111011101101111011100100110110001100100"
        ));
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    void asciiStringToBitArray() {
        boolean[] expectedBitArray = BitConversion.bitStringToBitArray("0110100001100101011011000110110001101111001000000111011101101111011100100110110001100100");
        boolean[] bitArray = BitConversion.asciiStringToBitArray("hello world");
        Assertions.assertArrayEquals(expectedBitArray, bitArray);
    }

    @Test
    void bitsToAsciiCode() {
        Assertions.assertEquals(0, BitConversion.bitsToAsciiCode(new boolean[] {false, false, false, false, false, false, false, false}));
        Assertions.assertEquals(1, BitConversion.bitsToAsciiCode(new boolean[] {false, false, false, false, false, false, false, true}));
        Assertions.assertEquals(134, BitConversion.bitsToAsciiCode(new boolean[] {true, false, false, false, false, true, true, false}));
        Assertions.assertEquals(255, BitConversion.bitsToAsciiCode(new boolean[] {true, true, true, true, true, true, true, true}));
    }

    @Test
    void asciiCodeToBits() {
        Assertions.assertArrayEquals(new boolean[] {false, false, false, false, false, false, false, false}, BitConversion.asciiCodeToBits(0));
        Assertions.assertArrayEquals(new boolean[] {false, false, false, false, false, false, false, true}, BitConversion.asciiCodeToBits(1));
        Assertions.assertArrayEquals(new boolean[] {true, false, false, false, false, true, true, false}, BitConversion.asciiCodeToBits(134));
        Assertions.assertArrayEquals(new boolean[] {true, true, true, true, true, true, true, true}, BitConversion.asciiCodeToBits(255));
    }
}

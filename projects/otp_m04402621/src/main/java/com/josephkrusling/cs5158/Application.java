package com.josephkrusling.cs5158;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {


    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Expected argument: encrypt OR decrypt OR keygen");
            printUsage();
            System.exit(1);
        }
        switch (args[0]) {
            case "enc":
            case "encrypt":
                if (args.length < 4) {
                    System.err.println("Expected at least 3 arguments after " + args[0]);
                    printUsage();
                }
                try {
                    encrypt(
                            Paths.get(args[1]),
                            Paths.get(args[2]),
                            Paths.get(args[3])
                    );
                } catch (IOException e) {
                    System.err.println("Encountered IO exception while encrypting");
                    e.printStackTrace();
                    System.exit(2);
                }
                break;
            case "dec":
            case "decrypt":
                if (args.length < 4) {
                    System.err.println("Expected at least 3 arguments after " + args[0]);
                    printUsage();
                }
                try {
                    decrypt(
                            Paths.get(args[1]),
                            Paths.get(args[2]),
                            Paths.get(args[3])
                    );
                } catch (IOException e) {
                    System.err.println("Encountered IO exception while decrypting");
                    e.printStackTrace();
                    System.exit(2);
                }
                break;
            case "keygen":
                if (args.length < 3) {
                    System.err.println("Expected at least 2 arguments after " + args[0]);
                    printUsage();
                }
                try {
                    keygen(
                            Integer.parseInt(args[1]),
                            Paths.get(args[2])
                    );
                } catch (IOException e) {
                    System.err.println("Encountered IO exception while generating key");
                    e.printStackTrace();
                    System.exit(2);
                }
                break;
            default:
                System.err.println("Expected argument: encrypt OR decrypt OR keygen");
                System.exit(1);
        }
    }

    private static void encrypt(Path keyPath, Path plaintextPath, Path outPath) throws IOException {
        String key = FileUtils.readFile(keyPath);
        String plaintext = FileUtils.readFile(plaintextPath);
        String ciphertext = OneTimePad.encryptString(plaintext, key);

        System.out.println("Encryption complete!");
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);
        FileUtils.writeFile(outPath, ciphertext);
    }

    private static void decrypt(Path keyPath, Path ciphertextPath, Path outPath) throws IOException {
        String key = FileUtils.readFile(keyPath);
        String ciphertext = FileUtils.readFile(ciphertextPath);
        String plaintext = OneTimePad.decryptString(ciphertext, key);

        System.out.println("Decryption complete!");
        System.out.println("Ciphertext: " + ciphertext);
        System.out.println("Plaintext: " + plaintext);

        FileUtils.writeFile(outPath, plaintext);
    }

    private static void keygen(int length, Path outPath) throws IOException {
        String keyString = BitConversion.bitArrayToBitString(KeyGenerator.generateKey(length));

        System.out.println("Key of " + length + " bits generated!");
        System.out.println("Key: " + keyString);

        FileUtils.writeFile(outPath, keyString);
    }

    private static void printUsage() {
        System.out.println("Usage: java -jar otp.jar enc KEY_PATH PLAINTEXT_PATH OUT_PATH");
        System.out.println("    OR java -jar otp.jar dec KEY_PATH CIPHERTEXT_PATH OUT_PATH");
        System.out.println("    OR java -jar otp.jar keygen KEY_LENGTH OUT_PATH");
    }
}

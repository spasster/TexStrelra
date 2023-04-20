package com.example.texstrelra.utils.server;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class CryptUtils {
    private static final String PASSWORD = "tele2";

    public static String encryptString(String text) {
        String cipherText = null;
        String salt = null;
        for (int i = 0; i < 10; i++) {
            salt = KeyGenerators.string().generateKey();
            TextEncryptor encryptor = Encryptors.text(PASSWORD, salt);
            cipherText = encryptor.encrypt(text);
        }
        //System.out.println("Salt: " + salt);
        return cipherText + salt;
    }

    public static String decryptString(String text) {
        //String cipherText = null;
        String salt = text.substring(text.length() - 16);
        String decryptedText = null;
        for (int i = 0; i < 10; i++) {
            TextEncryptor encryptor = Encryptors.text(PASSWORD, salt);
            decryptedText = encryptor.decrypt(text.substring(0, text.length() - 16));
        }
        return decryptedText;
    }
}
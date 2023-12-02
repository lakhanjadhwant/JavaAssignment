import java.util.Scanner;

public class EncryptDecrypt {
    static String message;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Input message
        System.out.println("Enter a message:");
        message = sc.nextLine();

        // Prompt for operation
        System.out.println("Enter 1 for Encrypt and 2 for Decrypt:");
        int operation = sc.nextInt();

        // Input key
        System.out.println("Enter encryption/decryption key:");
        int key = sc.nextInt();

        if (operation == 1) {
            String encryptedMessage = encrypt(key);
            System.out.println("The Encrypted message is " + encryptedMessage);
        } else if (operation == 2) {
            String decryptedMessage = decrypt(key);
            System.out.println("The Decrypted Message is " + decryptedMessage);
        } else {
            System.out.println("Invalid operation. Please enter 1 for Encrypt or 2 for Decrypt.");
        }

        sc.close();
    }

    private static String encrypt(int key) {
        char[] encrypt = new char[message.length()];
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char ch = (char) ((c - base + key) % 26 + base);
                encrypt[i] = ch;
            } else {
                encrypt[i] = c;
            }
        }
        return new String(encrypt);
    }

    private static String decrypt(int key) {
        char[] decrypt = new char[message.length()];
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char ch = (char) ((c - base - key + 26) % 26 + base);
                decrypt[i] = ch;
            } else {
                decrypt[i] = c;
            }
        }
        return new String(decrypt);
    }
}

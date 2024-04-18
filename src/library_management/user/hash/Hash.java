package library_management.user.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash {

    public static String hashString(String input) throws NoSuchAlgorithmException {
        // Create a MessageDigest instance for the desired hash algorithm (e.g.,
        // SHA-256)
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Convert the input string to bytes
        byte[] inputBytes = input.getBytes();

        // Compute the hash
        byte[] hashBytes = digest.digest(inputBytes);

        // Convert the hash bytes to a base64 encoded string for easy representation
        String hashString = Base64.getEncoder().encodeToString(hashBytes);

        return hashString;
    }

    public static void main(String[] args) {
        try {
            // Sample string to hash
            String sampleString = "Hello, World!";

            // Hash the string using SHA-256 algorithm
            String hashedString = hashString(sampleString);

            // Print the hashed string
            System.out.println("Hashed string (SHA-256): " + hashedString);
        } catch (NoSuchAlgorithmException e) {
            // Handle exception if the algorithm is not available
            System.err.println("Hashing algorithm not available: " + e.getMessage());
        }
    }
}
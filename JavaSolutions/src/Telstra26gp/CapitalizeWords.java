package Telstra26gp;

import java.util.Scanner;

public class CapitalizeWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read lines from standard input
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(capitalizeWords(line));
        }

        scanner.close();
    }

    // Helper method to capitalize the first letter of each word in the line
    private static String capitalizeWords(String line) {
        String[] words = line.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                // Capitalize the first letter and append the rest of the word
                String capitalized = Character.toUpperCase(word.charAt(0)) + word.substring(1);
                result.append(capitalized).append(" ");
            }
        }

        // Remove the trailing space
        return result.toString().trim();
    }
}

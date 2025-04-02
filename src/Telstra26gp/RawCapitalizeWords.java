package Telstra26gp;

public class RawCapitalizeWords {
    public static String capitalizeWords(String line) {
        String[] words = line.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                // Capitalize first character and append the rest
                String capitalized = Character.toUpperCase(word.charAt(0)) + word.substring(1);
                result.append(capitalized).append(" ");
            }
        }

        // Return result without trailing space
        return result.toString().trim();
    }
}

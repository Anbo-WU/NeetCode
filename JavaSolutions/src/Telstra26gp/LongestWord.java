package Telstra26gp;

import java.util.Scanner;
public class LongestWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();

        String result = findLongestWord(line);
        System.out.println(result);
    }

    // Finds the longest word in the sentence
    public static String findLongestWord(String sentence) {
        String[] words = sentence.split(" ");
        String longest = "";

        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }
}

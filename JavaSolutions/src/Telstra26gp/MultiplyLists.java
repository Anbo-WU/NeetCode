package Telstra26gp;

import java.util.Scanner;

public class MultiplyLists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the whole line of input
        String line = scanner.nextLine();
        scanner.close();

        // Split two lists by the pipe symbol
        String[] parts = line.split("\\|");
        String[] list1 = parts[0].trim().split(" ");
        String[] list2 = parts[1].trim().split(" ");

        // Multiply corresponding elements
        for (int i = 0; i < list1.length; i++) {
            int num1 = Integer.parseInt(list1[i]);
            int num2 = Integer.parseInt(list2[i]);
            System.out.print((num1 * num2) + " ");
        }
    }
}

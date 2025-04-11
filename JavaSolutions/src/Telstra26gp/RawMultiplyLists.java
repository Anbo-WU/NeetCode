package Telstra26gp;

public class RawMultiplyLists {
    public static String multiply(String input) {
        String[] parts = input.split("\\|");
        String[] list1 = parts[0].trim().split(" ");
        String[] list2 = parts[1].trim().split(" ");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < list1.length; i++) {
            int num1 = Integer.parseInt(list1[i]);
            int num2 = Integer.parseInt(list2[i]);
            result.append(num1 * num2).append(" ");
        }

        return result.toString().trim();
    }
}

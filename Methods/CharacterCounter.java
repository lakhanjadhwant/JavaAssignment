import java.util.Scanner;
public class CharacterCounter {
    public static void main(String[] args) {
        System.out.println("Enter your message:");
        Scanner sc = new Scanner(System.in);

        String inputString = sc.nextLine();
        countCharacters(inputString);
        sc.close();
    }

    public static int[] countCharacters(String input) {
        int[] counts = new int[5]; // Array to store counts for uppercase, lowercase, digits, spaces, and other symbols

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                counts[0]++;
            } else if (Character.isLowerCase(ch)) {
                counts[1]++;
            } else if (Character.isDigit(ch)) {
                counts[2]++;
            } else if (Character.isWhitespace(ch)) {
                counts[3]++;
            } else {
                counts[4]++;
            }
        }
        System.out.println("Uppercase Letters: " + counts[0]);
        System.out.println("Lowercase Letters: " + counts[1]);
        System.out.println("Digits: " + counts[2]);
        System.out.println("Spaces: " + counts[3]);
        System.out.println("Other Symbols: " + counts[4]);

        return counts;
    }
}

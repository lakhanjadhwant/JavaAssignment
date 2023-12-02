import java.util.Scanner;

public class OccurrenceCounter {
    private static String text;

    // Method to count the occurrences of a specified character in the stored string
    public static int countOccurrences(char targetChar) {
        int count = 0;

        // Check each character in the stored string
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == targetChar) {
                count++;
            }
        }

        return count;
    }

    // Method to count the occurrences of a specified string in the stored string
    public static int countOccurrences(String targetString) {
        int count = 0;
        int index = 0;

        // Iterate over the stored string until no more occurrences are found
        while ((index = text.indexOf(targetString, index)) != -1) {
            count++;
            index += targetString.length();
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println("Enter any string: ");
        Scanner input = new Scanner(System.in);
        text = input.nextLine();

        System.out.println("Enter a character or a string to count its occurrences: ");
        String target = input.nextLine();

        if (target.length() == 1) {
            // User entered a single character
            char targetChar = target.charAt(0);
            int charOccurrences = countOccurrences(targetChar);
            System.out.println("Occurrences of '" + targetChar + "': " + charOccurrences);
        } else {
            // User entered a string
            int stringOccurrences = countOccurrences(target);
            System.out.println("Occurrences of \"" + target + "\": " + stringOccurrences);
        }

        // Closing the Scanner (good practice)
        input.close();
    }
}

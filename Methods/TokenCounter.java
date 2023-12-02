import java.util.Scanner;

public class TokenCounter{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the input string:");
        String input = scanner.nextLine();
        System.out.println("Enter the key element");
        char keyElement = scanner.next().charAt(0);

        int tokenCount = countTokens(input, keyElement);

        System.out.println("Total number of tokens: " + tokenCount);

        scanner.close();
    }

    private static int countTokens(String input, char keyElement) {
        int tokenCount = 1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == keyElement) {
                tokenCount++;
            }
        }
        return tokenCount;
    }
}

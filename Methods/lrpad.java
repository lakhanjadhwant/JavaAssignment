import java.util.Scanner;

public class lrpad {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input string
        System.out.println("Enter a string:");
        String input = scanner.nextLine();

        // Padding length
        System.out.println("Enter padding length:");
        int paddingLength = scanner.nextInt();

        // Padding character
        System.out.println("Enter padding character:");
        char paddingChar = scanner.next().charAt(0);

        // Left padding
        String leftPadded = lpad(input, paddingLength, paddingChar);
        System.out.println("Left Padded Result: " + leftPadded);

        // Right padding
        String rightPadded = rpad(input, paddingLength, paddingChar);
        System.out.println("Right Padded Result: " + rightPadded);

        scanner.close();
    }

    public static String lpad(String input, int length, char padChar) {
        int k=input.length();
        for (int i = 1; i <= length-k; i++) {
           input=input+padChar;
        }
        return input;
        
    }
    
    public static String rpad(String input, int length, char padChar) {
        int k=input.length();
        for (int i = 1; i <= length-k; i++) {
           input=padChar+input;
        }
        return input;
        
    }
    
}

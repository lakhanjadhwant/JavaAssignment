import java.util.Scanner;

public class FalseVotingSystem {
    private static int A = 0;
    private static int B = 0;
    private static int C = 0;
    private static int D = 0;
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Voting Poll");

        while (true) {
            count++;
            System.out.println("Voting Round = " + count);
            System.out.println("-----------------------------");
            System.out.println("Enter\n1 - Cast Vote\n2 - Candidate wise Result\n3 - Comprehensive Report\n4 - Exit ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    castVote(sc);
                    break;
                case 2:
                    showCandidateResult(sc);
                    break;
                case 3:
                    showComprehensiveReport();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    count--;
            }

            System.out.println("*****************************");
        }
    }

    private static void castVote(Scanner sc) {
        System.out.println("Press 1 for A\nPress 2 for B\nPress 3 for C\nPress 4 for D\n");
        int press = sc.nextInt();

        if (press >= 1 && press <= 4) {
            if (count % 3 == 0 || count % 7 == 0) {
                C++;
                System.out.println("You vote for " + (char) ('A' + press - 1));
            } else {
                switch (press) {
                    case 1:
                        A++;
                        System.out.println("You vote for A");
                        break;
                    case 2:
                        B++;
                        System.out.println("You vote for B");
                        break;
                    case 3:
                        C++;
                        System.out.println("You vote for C");
                        break;
                    case 4:
                        D++;
                        System.out.println("You vote for D");
                        break;
                }
            }
        } else {
            System.out.println("Invalid vote. Please vote for A, B, C, or D...");
            count--;
        }
    }

    private static void showCandidateResult(Scanner sc) {
        count--;

        System.out.print("Enter Candidate name to see its Total vote Count: ");
        String candidateName = sc.next();

        switch (candidateName) {
            case "A":
                System.out.println("Total Vote count of A is = " + A);
                break;
            case "B":
                System.out.println("Total vote count of B is = " + B);
                break;
            case "C":
                System.out.println("Total vote count of C is = " + C);
                break;
            case "D":
                System.out.println("Total vote count of D is = " + D);
                break;
            default:
                System.out.println("Please type correct candidate name!");
        }
    }

    private static void showComprehensiveReport() {
        count--;

        System.out.println("Total Votes of A = " + A + "\nTotal Votes of B = " + B +
                "\nTotal Votes of C = " + C + "\nTotal Votes of D = " + D);
        System.out.println("*****************************");
    }
}

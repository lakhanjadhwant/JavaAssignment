import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    String name;
    int age;
    int rollNo;
    String[] preferences;
    String allocatedCenter;
}

class ExamCenter {
    String name;
    int totalSeats;
    int filledSeats;

    ExamCenter(String name, int totalSeats) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.filledSeats = 0;
    }
}

public class ExamCenterSelection {
    private static Map<String, ExamCenter> examCenters = new HashMap<>();
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        initializeCenters();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter your choice:");
            System.out.println("1. Enter Profile");
            System.out.println("2. Enter Center Choice");
            System.out.println("3. Display Candidate status");
            System.out.println("4. Display Center Information");
            System.out.println("5. Center Status");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    enterProfile(scanner);
                    break;
                case 2:
                    enterCenterChoice(scanner);
                    break;
                case 3:
                    displayCandidateStatus(scanner);
                    break;
                case 4:
                    displayCenterInformation();
                    break;
                case 5:
                    centerStatus(scanner);
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void initializeCenters() {
        examCenters.put("Ujjain", new ExamCenter("Ujjain", 2));
        examCenters.put("Indore", new ExamCenter("Indore", 2));
        examCenters.put("Dewas", new ExamCenter("Dewas", 3));
        
    }

    private static void enterProfile(Scanner scanner) {
        Student student = new Student();
        System.out.println("Enter your name:");
        scanner.nextLine();
        student.name = scanner.nextLine();
        System.out.println("Enter your age:");
        student.age = scanner.nextInt();
        System.out.println("Enter your roll number:");
        int rollNo = scanner.nextInt();

        boolean duplicateRollNo = students.stream().anyMatch(s -> s.rollNo == rollNo);

        if (duplicateRollNo) {
            System.out.println("Student with the same roll number already exists. Profile not added.");
        } else {
            student.rollNo = rollNo;
            students.add(student);
            System.out.println("Profile entered successfully!");
        }
    }

    private static void enterCenterChoice(Scanner scanner) {
        System.out.println("Enter your roll number to choose the center:");
        int rollNo = scanner.nextInt();
        boolean found = false;

        for (Student student : students) {
            if (student.rollNo == rollNo) {
                if (student.allocatedCenter != null) {
                    System.out.println("Center already allocated: ");
                } else {
                    enterPreferences(student, scanner);
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found. Please enter a valid roll number.");
        }
    }

    private static void enterPreferences(Student student, Scanner scanner) {
        int numberOfCenters = examCenters.size();
        student.preferences = new String[numberOfCenters];

        for (int i = 0; i < numberOfCenters; i++) {
            boolean validPreference;
            do {
                System.out.println("Enter your " + (i + 1) + " priority:");
                System.out.println("Available Centers:");

                int j = 1;
                for (ExamCenter center : examCenters.values()) {
                    System.out.println("Enter " + j + " for " + center.name);
                    j++;
                }

                int choice = scanner.nextInt();
                String centerName = getCenterNameByChoice(choice);

                validPreference = isValidPreference(student.preferences, centerName);

                if (validPreference) {
                    student.preferences[i] = centerName;
                } else {
                    System.out.println("This center preference is already entered. Please choose a different one.");
                }

            } while (!validPreference);
        }

        allocateCenter(student);
    }

    private static String getCenterNameByChoice(int choice) {
        int index = 1;
        for (ExamCenter center : examCenters.values()) {
            if (index == choice) {
                return center.name;
            }
            index++;
        }
        return null;
    }

    private static boolean isValidPreference(String[] preferences, String centerName) {
        for (String preference : preferences) {
            if (preference != null && preference.equals(centerName)) {
                return false;
            }
        }
        return true;
    }

    private static void allocateCenter(Student student) {
        for (String preference : student.preferences) {
            if (examCenters.containsKey(preference) && examCenters.get(preference).filledSeats < examCenters.get(preference).totalSeats) {
                student.allocatedCenter = preference;
                examCenters.get(preference).filledSeats++;
                System.out.println("Center allocated successfully: ");
                return;
            }
        }

        System.out.println("No available centers. Unable to allocate.");
    }

    private static void displayCandidateStatus(Scanner scanner) {
        System.out.println("Enter your roll number to display candidate status:");
        int rollNo = scanner.nextInt();
        boolean found = false;

        for (Student student : students) {
            if (student.rollNo == rollNo) {
                System.out.println("+-------------------+-----------------+-------------+------------------+");
                System.out.println("| Roll No           | Name            | Age         | Allocated Center |");
                System.out.println("+-------------------+-----------------+-------------+------------------+");
                System.out.printf("| %-17d | %-15s | %-11d | %-16s |%n", student.rollNo, student.name, student.age, student.allocatedCenter);
                System.out.println("+-------------------+-----------------+-------------+------------------+");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found. Please enter a valid roll number.");
        }
    }

    private static void displayCenterInformation() {
        System.out.println("Center-wise Seat Status:");
        System.out.println("+----------------------+--------------+--------------+-----------------+");
        System.out.println("| Center Name          | Total Seats  | Filled Seats | Remaining Seats |");
        System.out.println("+----------------------+--------------+--------------+-----------------+");

        for (ExamCenter center : examCenters.values()) {
            System.out.printf("| %-20s | %-12d | %-12d | %-15d |\n",
                    center.name, center.totalSeats, center.filledSeats, center.totalSeats - center.filledSeats);
        }

        System.out.println("+----------------------+--------------+--------------+-----------------+");
    }

    private static void centerStatus(Scanner scanner) {
        System.out.println("Enter the center name to display status:");
        scanner.nextLine(); // Consume the newline character left by previous nextInt()
        String centerName = scanner.nextLine();

        if (examCenters.containsKey(centerName)) {
            ExamCenter center = examCenters.get(centerName);
            System.out.println("+----------------------+--------------+--------------+-----------------+");
            System.out.println("| Center Name          | Total Seats  | Filled Seats | Remaining Seats |");
            System.out.println("+----------------------+--------------+--------------+-----------------+");
            System.out.printf("| %-20s | %-12d | %-12d | %-15d |%n",
                    center.name, center.totalSeats, center.filledSeats, center.totalSeats - center.filledSeats);
            System.out.println("+----------------------+--------------+--------------+-----------------+");
        } else {
            System.out.println("Center not found. Please enter a valid center name.");
        }
    }
}

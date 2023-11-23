import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    String name;
    int age;
    int rollNo; 
    String preferences;
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

    public static void main(String[] args) {
        initializeCenters();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> StudentArray = new ArrayList<>(); 

        while (true) {
            System.out.println("\nEnter your choice:");
            System.out.println("1. Enter Profile");
            System.out.println("2. Enter Center Choice");
            System.out.println("3. Display Candidate status");
            System.out.println("4. Display Complete Status");
            System.out.println("5. Display Center");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    enterProfile(StudentArray, scanner); // Change here
                    break;
                case 2:
                    enterCenterChoice(StudentArray, scanner); // Change here
                    break;
                case 3:
                    displayCandidateStatus(StudentArray); // Change here
                    break;
                case 4:
                    displayCompleteStatus(StudentArray); // Change here
                    break;
                case 5:
                    displayCenter();
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

    private static void enterProfile(ArrayList<Student> StudentArray, Scanner scanner) {
        Student student = new Student();
        System.out.println("Enter your name:");
        student.name = scanner.next();
        System.out.println("Enter your age:");
        student.age = scanner.nextInt();
        System.out.println("Enter your roll number:");
        int rollNo = scanner.nextInt();
    
        // Check for duplicate roll numbers
        boolean duplicateRollNo = false;
        for (Student existingStudent : StudentArray) {
            if (existingStudent.rollNo == rollNo) {
                duplicateRollNo = true;
                break;
            }
        }
    
        if (duplicateRollNo) {
            System.out.println("Student with the same roll number already exists. Profile not added.");
        } else {
            student.rollNo = rollNo;
            StudentArray.add(student);
            System.out.println("Profile entered successfully!");
        }
    }
    

    private static void enterCenterChoice(ArrayList<Student> StudentArray, Scanner scanner) { // Change here
        System.out.println("Enter the roll number to choose the center:");
        int rollNo = scanner.nextInt();
        boolean found = false;

        for (Student student : StudentArray) { // Change here
            if (student.rollNo == rollNo) {
                enterPreferences(student, scanner);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found. Please enter a valid roll number.");
        }
    }

    private static void enterPreferences(Student student, Scanner scanner) {
        boolean validOrder;
        do {
            
            System.out.println("Available Centers are: \na) Ujjain \nb) Indore \nc) Dewas.");
            System.out.println("Give the preference of the centers, for example, 'a,b,c' for Ujjain, Indore, Dewas.");
            
            student.preferences = scanner.nextLine().toLowerCase();
            validOrder = isValidOrder(student.preferences);

            if (!validOrder) {
                System.out.println("Please enter a valid order for your preferences.");
            }
        } while (!validOrder);

        allocateCenter(student);
    }

    private static boolean isValidOrder(String order) {
        String[] validOrders = {"a,b,c", "a,c,b", "b,a,c", "b,c,a", "c,a,b", "c,b,a"};
        for (String valid : validOrders) {
            if (valid.equals(order)) {
                return true;
            }
        }
        return false;
    }

    private static void allocateCenter(Student student) {
        for (char preference : student.preferences.toCharArray()) {
            String center = getCenterByPreference(preference);
            if (center != null && examCenters.containsKey(center) && examCenters.get(center).filledSeats < examCenters.get(center).totalSeats) {
                student.allocatedCenter = center;
                examCenters.get(center).filledSeats++;
                System.out.println("Center allocated successfully: ");
                return;
            }
        }

        System.out.println("No available centers. Unable to allocate.");
    }

    private static String getCenterByPreference(char preference) {
        switch (preference) {
            case 'a':
                return "Ujjain";
            case 'b':
                return "Indore";
            case 'c':
                return "Dewas";
            default:
                return null;
        }
    }

    private static void displayCandidateStatus(ArrayList<Student> StudentArray) { // Change here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your roll number to display candidate status:");
        int rollNo = scanner.nextInt();
        boolean found = false;


        for (Student student : StudentArray) { // Change here
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

    private static void displayCompleteStatus(ArrayList<Student> StudentArray) { // Change here
        System.out.println("+-------------------+-----------------+-------------+----------------------+");
        System.out.println("| Roll No           | Name            | Age         | Allocated Center     |");
        System.out.println("+-------------------+-----------------+-------------+----------------------+");

        for (Student student : StudentArray) { // Change here
            System.out.printf("| %-17d | %-15s | %-11d | %-20s |%n", student.rollNo, student.name, student.age, student.allocatedCenter);
        }

        System.out.println("+-------------------+-----------------+-------------+----------------------+");
    }

    private static void displayCenter() {
        System.out.println("Center-wise Seat Status:");
        System.out.println("+----------------------+--------------+--------------+-----------------+");
        System.out.println("| Center Name          | Total Seats  | Filled Seats | Remaining Seats |");
        System.out.println("+----------------------+--------------+--------------+-----------------+");

        for (ExamCenter center : examCenters.values()) {
            System.out.printf("| %-20s | %-12d | %-12d | %-15d |%n",
                    center.name, center.totalSeats, center.filledSeats, center.totalSeats - center.filledSeats);
        }

        System.out.println("+----------------------+--------------+--------------+-----------------+");
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Person {
    String name;
    int age;
    int loginId;
    String allocatedTrain = "";
    int boogieNumber;
    int seatNumber;
    int trainNumber;  // Added train number
}

class Train {
    String name;
    int totalBoogies;
    int boogieSize;  // Default size of each boogie
    int filledSeats;

    Train(String name, int boogieSize, int totalBoogies) {
        this.name = name;
        this.boogieSize = boogieSize;
        this.totalBoogies = totalBoogies;
        this.filledSeats = 0;
    }
}

public class TrainAllocation {
    private static Map<Integer, Person> persons = new HashMap<>();
    private static Map<String, Train> trains = new HashMap<>();
    private static int nextLoginId = 101;
    private static final int DEFAULT_BOOGIE_SIZE = 2;  // Default size of each boogie
    private static final int DEFAULT_TOTAL_BOOGIES = 3;  // Default number of boogies

    public static void main(String[] args) {
        initializeTrains();  // Use the default trains

        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> personList = new ArrayList<>();

        while (true) {
            System.out.println("\nEnter your choice:");
            System.out.println("1. Enter Profile");
            System.out.println("2. Enter Train Choice");
            System.out.println("3. Display Person Information");
            System.out.println("4. Display Complete Information");
            System.out.println("5. Display Train Status");
            System.out.println("6. Dispaly Comprehensive Report");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    enterProfile(personList, scanner);
                    break;
                case 2:
                    enterTrainChoice(personList, scanner);
                    break;
                case 3:
                    displayPersonInformation(personList, scanner);
                    break;
                case 4:
                    displayCompleteInformation(personList);
                    break;
                case 5:
                    displayTrainStatus(scanner);
                    break;
                case 6:
                    comprehensiveReport();
                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void initializeTrains() {
        // Manually update trains as needed
        trains.put("Ujjain", new Train("Ujjain", DEFAULT_BOOGIE_SIZE, DEFAULT_TOTAL_BOOGIES));
        trains.put("Indore", new Train("Indore", DEFAULT_BOOGIE_SIZE, DEFAULT_TOTAL_BOOGIES));
        trains.put("Dewas", new Train("Dewas", DEFAULT_BOOGIE_SIZE, DEFAULT_TOTAL_BOOGIES));
        // You can add or remove trains here
    }

    private static void enterProfile(ArrayList<Person> personList, Scanner scanner) {
        Person person = new Person();
        System.out.println("Enter your name:");
        scanner.nextLine();
        person.name = scanner.nextLine();
        System.out.println("Enter your age:");
        person.age = scanner.nextInt();

        // Generate login id as an integer starting from 101
        person.loginId = nextLoginId++;

        personList.add(person);
        persons.put(person.loginId, person);

        System.out.println("Profile entered successfully! Your login id is: " + person.loginId);
    }

    private static void enterTrainChoice(ArrayList<Person> personList, Scanner scanner) {
        System.out.println("Enter your login id:");
        int loginId = scanner.nextInt();

        Person person = persons.get(loginId);

        if (person != null) {
            if (person.allocatedTrain != null && !person.allocatedTrain.isEmpty()) {
                System.out.println("You have already selected a train. Seat is already allocated.");
            } else {
                System.out.println("Choose your train (Enter the number):");
                int i = 1;
                for (Map.Entry<String, Train> entry : trains.entrySet()) {
                    System.out.println(i + ". " + entry.getKey());
                    i++;
                }

                int trainChoice = scanner.nextInt();

                String trainName = getTrainName(trainChoice);
                if (!trainName.isEmpty()) {
                    allocateSeat(person, trainName);
                } else {
                    System.out.println("Invalid train choice. Please enter a valid option.");
                }
            }
        } else {
            System.out.println("Invalid login id. Please enter a valid login id.");
        }
    }

    private static void allocateSeat(Person person, String trainName) {
        if (person.allocatedTrain != null && !person.allocatedTrain.isEmpty()) {
            System.out.println("Seat is already allocated for this user.");
        } else {
            Train train = trains.get(trainName);

            if (train != null && train.filledSeats < train.totalBoogies * train.boogieSize) {
                int seatNumber = (train.filledSeats % train.boogieSize) + 1;
                int boogieNumber = (train.filledSeats / train.boogieSize) % train.totalBoogies + 1;

                person.allocatedTrain = trainName;
                person.boogieNumber = boogieNumber;
                person.seatNumber = seatNumber;
                person.trainNumber = getTrainNumber(trainName);

                train.filledSeats++;

                System.out.println("Seat allocated successfully!");
            } else {
                System.out.println("No available seats in the chosen train. Unable to allocate.");
            }
        }
    }

    private static void displayPersonInformation(ArrayList<Person> personList, Scanner scanner) {
        System.out.println("Enter your login id to display person information:");
        int loginId = scanner.nextInt();

        Person person = persons.get(loginId);

        if (person != null) {
            System.out.println("+---------------+-----------------+-----+-----------+----------------+------------+-----------+");
            System.out.println("| Login ID      | Name            | Age | Train No. | Allocated Train| Boogie No. | Seat No.  |");
            System.out.println("+---------------+-----------------+-----+-----------+----------------+------------+-----------+");
            System.out.printf("| %-13d | %-15s | %-3d | %-9d | %-14s | %-10d | %-8d  |%n",
                    person.loginId, person.name, person.age, person.trainNumber, person.allocatedTrain,
                    person.boogieNumber, person.seatNumber);
            System.out.println("+---------------+-----------------+-----+-----------+----------------+------------+-----------+");
        } else {
            System.out.println("Person not found. Please enter a valid login id.");
        }
    }

    private static void displayCompleteInformation(ArrayList<Person> personList) {
        System.out.println("+---------------+-----------------+-----+-----------+----------------+------------+-----------+");
        System.out.println("| Login ID      | Name            | Age | Train No. | Allocated Train| Boogie No. | Seat No.  |");
        System.out.println("+---------------+-----------------+-----+-----------+----------------+------------+-----------+");

        for (Person person : personList) {
            System.out.printf("| %-13d | %-15s | %-3d | %-9d | %-14s | %-10d | %-8d  |%n",
                    person.loginId, person.name, person.age, person.trainNumber, person.allocatedTrain,
                    person.boogieNumber, person.seatNumber);
        }

        System.out.println("+---------------+-----------------+-----+-----------+----------------+------------+-----------+");
    }

    private static void displayTrainStatus(Scanner scanner) {
        System.out.println("Enter the train number to display train status:");
        int trainNumber = scanner.nextInt();

        String trainName = getTrainName(trainNumber);
        Train train = trains.get(trainName);

        if (train != null) {
            System.out.println("+------------------+-------------------+------------------+------------------+------------------+-----------------+");
            System.out.println("| Train Number     | Train             | Total Boogies    | Total Seats      | Total Booked     | Total Available |");
            System.out.println("+------------------+-------------------+------------------+------------------+------------------+-----------------+");
            System.out.printf("| %-16d | %-16s | %-16d | %-16d | %-16d | %-16d |%n",
                    trainNumber, train.name, train.totalBoogies, train.totalBoogies * train.boogieSize,
                    train.filledSeats, train.totalBoogies * train.boogieSize - train.filledSeats);
            System.out.println("+------------------+------------------+------------------+------------------+------------------+------------------+");
        } else {
            System.out.println("Train not found. Please enter a valid train number.");
        }
    }

    private static void comprehensiveReport() {
        System.out.println("+------------------+-------=-----------+------------------+------------------+------------------+-----------------+");
        System.out.println("| Train Number     | Train             | Total Boogies    | Total Seats      | Total Booked     | Total Available |");
        System.out.println("+------------------+-------------------+------------------+------------------+------------------+-----------------+");

        for (Map.Entry<String, Train> entry : trains.entrySet()) {
            Train train = entry.getValue();
            int totalSeats = train.totalBoogies * train.boogieSize;

            System.out.printf("| %-16d | %-16s | %-16d | %-16d | %-16d | %-16d |%n",
                    getTrainNumber(entry.getKey()), entry.getKey(), train.totalBoogies, totalSeats,
                    train.filledSeats, totalSeats - train.filledSeats);
        }

        System.out.println("+------------------+------------------+------------------+------------------+------------------+------------------+");
    }

    private static String getTrainName(int trainNumber) {
        int i = 1;
        for (Map.Entry<String, Train> entry : trains.entrySet()) {
            if (i == trainNumber) {
                return entry.getKey();
            }
            i++;
        }
        return "";
    }

    private static int getTrainNumber(String trainName) {
        int i = 1;
        for (Map.Entry<String, Train> entry : trains.entrySet()) {
            if (entry.getKey().equals(trainName)) {
                return i;
            }
            i++;
        }
        return 0;
    }
}

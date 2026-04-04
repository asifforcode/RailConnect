import java.util.List;
import java.util.Scanner;

// this is the main class, it shows the menu and takes input from user
public class RailConnect {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        new RailConnect().run();
    }

    public void run() {
        while (true) {
            System.out.println("\n=============================");
            System.out.println("     Welcome to RailConnect  ");
            System.out.println("=============================");
            if (!userService.isLoggedIn()) {
                showGuestMenu();
            } else {
                showUserMenu();
            }
        }
    }

    private void showGuestMenu() {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        int choice = readInt("Enter your choice: ");

        switch (choice) {
            case 1 -> register();
            case 2 -> login();
            case 3 -> exitApp();
            default -> System.out.println("wrong choice, try again");
        }
    }

    private void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine().trim();
        System.out.print("Enter contact number: ");
        String contact = scanner.nextLine().trim();

        userService.registerUser(username, password, fullName, contact);
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        userService.loginUser(username, password);
    }

    private void showUserMenu() {
        while (userService.isLoggedIn()) {
            System.out.println("\n----- RailConnect Menu -----");
            System.out.println("1. Search Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. View My Tickets");
            System.out.println("4. Cancel My Ticket");
            System.out.println("5. List All Trains");
            System.out.println("6. Logout");

            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> searchTrains();
                case 2 -> bookTicket();
                case 3 -> viewMyTickets();
                case 4 -> cancelTicket();
                case 5 -> bookingService.listAllTrains();
                case 6 -> userService.logOutUser();
                default -> System.out.println("wrong choice, try again");
            }
        }
    }

    private void searchTrains() {
        List<Train> trains = fetchAndDisplayTrains();
        if (trains.isEmpty()) {
            return;
        }

        String answer = readLine("want to book a ticket for this train? (yes/no): ");
        if (answer.equalsIgnoreCase("yes")) {
            bookFromListedTrains(trains);
        }
    }

    private void bookTicket() {
        List<Train> trains = fetchAndDisplayTrains();
        if (trains.isEmpty()) {
            return;
        }
        bookFromListedTrains(trains);
    }

    private List<Train> fetchAndDisplayTrains() {
        String source = readLine("Enter source station: ");
        String destination = readLine("Enter destination station: ");
        List<Train> trains = bookingService.searchTrains(source, destination);

        if (trains.isEmpty()) {
            System.out.println("No trains found from " + source + " to " + destination + ".");
            return trains;
        }

        System.out.println("\nAvailable Trains:");
        System.out.println("----------------------------------------------------");
        for (Train train : trains) {
            System.out.println(train);
        }
        System.out.println("----------------------------------------------------");
        return trains;
    }

    private void bookFromListedTrains(List<Train> trains) {
        int trainId = readInt("Enter the Train ID you want to book: ");
        boolean found = false;
        for (Train train : trains) {
            if (train.getTrainId() == trainId) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("that train id is not in the list above");
            return;
        }

        int seats = readInt("Enter number of seats: ");
        Ticket ticket = bookingService.bookTicket(userService.getCurrentUser(), trainId, seats);
        if (ticket != null) {
            System.out.println("done! ticket is booked.");
            System.out.println(ticket);
        }
    }

    private void viewMyTickets() {
        List<Ticket> myTickets = bookingService.getTicketsByUser(userService.getCurrentUser());
        if (myTickets.isEmpty()) {
            System.out.println("you haven't booked any tickets yet");
            return;
        }

        System.out.println("\nYour Booked Tickets:");
        System.out.println("----------------------------------------------------");
        for (Ticket ticket : myTickets) {
            System.out.println(ticket);
        }
        System.out.println("----------------------------------------------------");
    }

    private void cancelTicket() {
        int ticketId = readInt("Enter Ticket ID to cancel: ");
        bookingService.cancelTicket(ticketId, userService.getCurrentUser());
    }

    private void exitApp() {
        System.out.println("bye! closing the app now.");
        scanner.close();
        System.exit(0);
    }

    // this method keeps asking until user enters a proper number
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // reads a line from the user
    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}

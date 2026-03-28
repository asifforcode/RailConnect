import java.util.List;
import java.util.Scanner;

// Main app class that shows menus and takes user input.
public class IRCTCAPP {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        new IRCTCAPP().start();
    }

    public void start() {
        while (true) {
            System.out.println("\nWelcome to IRCTC App");
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
            default -> System.out.println("Invalid choice.");
        }
    }

    private void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine().trim();
        System.out.print("Enter contact: ");
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
            System.out.println("\n----- User Menu -----");
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
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void searchTrains() {
        List<Train> trains = readAndSearchTrains();
        if (trains.isEmpty()) {
            return;
        }

        String choice = readLine("Do you want to book ticket? (yes/no): ");
        if (choice.equalsIgnoreCase("yes")) {
            bookFromListedTrains(trains);
        }
    }

    private void bookTicket() {
        List<Train> trains = readAndSearchTrains();
        if (trains.isEmpty()) {
            return;
        }
        bookFromListedTrains(trains);
    }

    private List<Train> readAndSearchTrains() {
        String source = readLine("Enter source station: ");
        String destination = readLine("Enter destination station: ");
        List<Train> trains = bookingService.searchTrains(source, destination);

        if (trains.isEmpty()) {
            System.out.println("No trains found between " + source + " and " + destination + ".");
            return trains;
        }

        System.out.println("Available trains:");
        for (Train train : trains) {
            System.out.println(train);
        }
        return trains;
    }

    private void bookFromListedTrains(List<Train> trains) {
        int trainId = readInt("Enter train ID to book: ");
        boolean trainExists = false;
        for (Train train : trains) {
            if (train.getTrainId() == trainId) {
                trainExists = true;
                break;
            }
        }

        if (!trainExists) {
            System.out.println("Please choose a train ID from the listed trains.");
            return;
        }

        int seats = readInt("Enter number of seats to book: ");
        Ticket ticket = bookingService.bookTicket(userService.getCurrentUser(), trainId, seats);
        if (ticket != null) {
            System.out.println("Booking successful!");
            System.out.println(ticket);
        }
    }

    private void viewMyTickets() {
        List<Ticket> ticketsByUser = bookingService.getTicketsByUser(userService.getCurrentUser());
        if (ticketsByUser.isEmpty()) {
            System.out.println("No tickets booked yet.");
            return;
        }

        System.out.println("Your tickets:");
        for (Ticket ticket : ticketsByUser) {
            System.out.println(ticket);
        }
    }

    private void cancelTicket() {
        int ticketId = readInt("Enter ticket ID to cancel: ");
        bookingService.cancelTicket(ticketId, userService.getCurrentUser());
    }

    private void exitApp() {
        System.out.println("Thank you for using IRCTC App.");
        scanner.close();
        System.exit(0);
    }

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

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Keeps all train search, booking, and cancellation logic.
public class BookingService {
    private final List<Train> trains = new ArrayList<>();
    private final List<Ticket> tickets = new ArrayList<>();

    public BookingService() {
        seedTrains();
    }

    public List<Train> searchTrains(String source, String destination) {
        String src = safeTrim(source);
        String dest = safeTrim(destination);
        List<Train> result = new ArrayList<>();
        for (Train train : trains) {
            if (train.getSource().equalsIgnoreCase(src) && train.getDestination().equalsIgnoreCase(dest)) {
                result.add(train);
            }
        }
        return result;
    }

    // Old method name kept so existing code still works.
    public List<Train> searchTrain(String source, String destination) {
        return searchTrains(source, destination);
    }

    public Ticket bookTicket(User user, int trainId, int seatCount) {
        if (user == null) {
            System.out.println("Please login first.");
            return null;
        }

        if (seatCount <= 0) {
            System.out.println("Seat count must be greater than zero.");
            return null;
        }

        Train train = findTrainById(trainId);
        if (train == null) {
            System.out.println("Train ID not found.");
            return null;
        }

        if (!train.bookSeats(seatCount)) {
            System.out.println("Not enough seats available.");
            return null;
        }

        Ticket ticket = new Ticket(user, train, seatCount);
        tickets.add(ticket);
        return ticket;
    }

    public List<Ticket> getTicketsByUser(User user) {
        List<Ticket> result = new ArrayList<>();
        if (user == null) {
            return result;
        }

        for (Ticket ticket : tickets) {
            if (ticket.getUser() != null
                    && ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                result.add(ticket);
            }
        }
        return result;
    }

    // Old method name kept so existing code still works.
    public List<Ticket> getTicketByUser(User user) {
        return getTicketsByUser(user);
    }

    public boolean cancelTicket(int ticketId, User user) {
        if (user == null) {
            System.out.println("Please login first.");
            return false;
        }

        Iterator<Ticket> iterator = tickets.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.getUser() == null || ticket.getTrain() == null) {
                continue;
            }

            if (ticket.getTicketId() == ticketId
                    && ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                Train train = ticket.getTrain();
                train.cancelSeats(ticket.getSeatsBooked());
                iterator.remove();
                System.out.println("Ticket " + ticketId + " cancelled successfully.");
                return true;
            }
        }

        System.out.println("Ticket not found or does not belong to current user.");
        return false;
    }

    public void listAllTrains() {
        System.out.println("List of all trains:");
        for (Train train : trains) {
            System.out.println(train);
        }
    }

    private void seedTrains() {
        trains.add(new Train(101, "Rajdhani Express", "Delhi", "Nagpur", 100));
        trains.add(new Train(102, "Shatabdi Express", "Delhi", "Mumbai", 60));
        trains.add(new Train(103, "Duronto Express", "Agra", "Delhi", 70));
        trains.add(new Train(104, "Vande Bharat Express", "Goa", "Nagpur", 90));
        trains.add(new Train(105, "Tejas Express", "Delhi", "Howrah", 80));
    }

    private Train findTrainById(int trainId) {
        for (Train train : trains) {
            if (train.getTrainId() == trainId) {
                return train;
            }
        }
        return null;
    }

    private String safeTrim(String value) {
        return value == null ? "" : value.trim();
    }
}

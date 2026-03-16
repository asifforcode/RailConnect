import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingService {
    private final List<Train> trainList = new ArrayList<>();
    private final List<Ticket> ticketList = new ArrayList<>();

    public BookingService() {
        seedTrains();
    }

    public List<Train> searchTrain(String source, String destination) {
        String src = safeTrim(source);
        String dest = safeTrim(destination);
        List<Train> res = new ArrayList<>();
        for (Train train : trainList) {
            if (train.getSource().equalsIgnoreCase(src) && train.getDestination().equalsIgnoreCase(dest)) {
                res.add(train);
            }
        }
        return res;
    }

    public Ticket bookTicket(User user, int trainID, int seatCount) {
        if (user == null) {
            System.out.println("Please login first.");
            return null;
        }

        if (seatCount <= 0) {
            System.out.println("Seat count must be greater than zero.");
            return null;
        }

        Train train = findTrainById(trainID);
        if (train == null) {
            System.out.println("Train ID not found.");
            return null;
        }

        if (!train.bookSeats(seatCount)) {
            System.out.println("Not enough seats available.");
            return null;
        }

        Ticket ticket = new Ticket(user, train, seatCount);
        ticketList.add(ticket);
        return ticket;
    }

    public List<Ticket> getTicketByUser(User user) {
        List<Ticket> res = new ArrayList<>();
        if (user == null) {
            return res;
        }

        for (Ticket ticket : ticketList) {
            if (ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                res.add(ticket);
            }
        }
        return res;
    }

    public boolean cancelTicket(int ticketId, User user) {
        if (user == null) {
            System.out.println("Please login first.");
            return false;
        }

        Iterator<Ticket> iterator = ticketList.listIterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.getTicketId() == ticketId
                    && ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                Train train = ticket.getTrain();
                train.cancelSeats(ticket.getSeatBooked());
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
        for (Train train : trainList) {
            System.out.println(train);
        }
    }

    private void seedTrains() {
        trainList.add(new Train(101, "Rajdhani Express", "Delhi", "Nagpur", 100));
        trainList.add(new Train(102, "Shatabdi Express", "Delhi", "Mumbai", 60));
        trainList.add(new Train(103, "Duronto Express", "Agra", "Delhi", 70));
        trainList.add(new Train(104, "Vande Bharat Express", "Goa", "Nagpur", 90));
        trainList.add(new Train(105, "Tejas Express", "Delhi", "Howrah", 80));
    }

    private Train findTrainById(int trainID) {
        for (Train train : trainList) {
            if (train.getTrainID() == trainID) {
                return train;
            }
        }
        return null;
    }

    private String safeTrim(String value) {
        return value == null ? "" : value.trim();
    }
}

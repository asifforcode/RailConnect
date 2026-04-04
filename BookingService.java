import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// this class handles everything related to trains and tickets
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

        // go through all trains and pick the ones that match
        for (Train train : trains) {
            if (train.getSource().equalsIgnoreCase(src) && train.getDestination().equalsIgnoreCase(dest)) {
                result.add(train);
            }
        }
        return result;
    }

    public Ticket bookTicket(User user, int trainId, int seatCount) {
        if (user == null) {
            System.out.println("you need to login first");
            return null;
        }

        if (seatCount <= 0) {
            System.out.println("seat count can't be 0 or less");
            return null;
        }

        // find the train by id
        Train train = findTrainById(trainId);
        if (train == null) {
            System.out.println("couldn't find that train");
            return null;
        }

        // try booking seats, it returns false if not enough seats
        if (!train.bookSeats(seatCount)) {
            System.out.println("not enough seats left on this train");
            return null;
        }

        // create the ticket and save it
        Ticket ticket = new Ticket(user, train, seatCount);
        tickets.add(ticket);
        return ticket;
    }

    public List<Ticket> getTicketsByUser(User user) {
        List<Ticket> result = new ArrayList<>();
        if (user == null) {
            return result;
        }

        // collect all tickets that belong to this user
        for (Ticket ticket : tickets) {
            if (ticket.getUser() != null
                    && ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                result.add(ticket);
            }
        }
        return result;
    }

    public boolean cancelTicket(int ticketId, User user) {
        if (user == null) {
            System.out.println("you need to login first");
            return false;
        }

        // using iterator here because we can't remove from a list while looping with for-each
        Iterator<Ticket> iterator = tickets.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.getUser() == null || ticket.getTrain() == null) {
                continue;
            }

            if (ticket.getTicketId() == ticketId
                    && ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                // put the seats back in the train
                Train train = ticket.getTrain();
                train.cancelSeats(ticket.getSeatsBooked());
                iterator.remove();
                System.out.println("ticket " + ticketId + " has been cancelled");
                return true;
            }
        }

        System.out.println("couldn't find that ticket or it's not yours");
        return false;
    }

    public void listAllTrains() {
        System.out.println("here are all available trains:");
        for (Train train : trains) {
            System.out.println(train);
        }
    }

    // adding some default trains
    private void seedTrains() {
        trains.add(new Train(101, "Rajdhani Express", "Delhi", "Nagpur", 100));
        trains.add(new Train(102, "Shatabdi Express", "Delhi", "Mumbai", 60));
        trains.add(new Train(103, "Duronto Express", "Agra", "Delhi", 70));
        trains.add(new Train(104, "Vande Bharat Express", "Goa", "Nagpur", 90));
        trains.add(new Train(105, "Tejas Express", "Delhi", "Howrah", 80));
    }

    // loops through trains list and finds one by id
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

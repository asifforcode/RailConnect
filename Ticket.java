// Stores booking details for one user and one train.
public class Ticket {
    private final int ticketId;
    private final User user;
    private final Train train;
    private final int seatsBooked;
    private static int counter = 1001;

    public Ticket(User user, Train train, int seatsBooked) {
        if (user == null) {
            throw new IllegalArgumentException("User is required for ticket creation.");
        }
        if (train == null) {
            throw new IllegalArgumentException("Train is required for ticket creation.");
        }
        if (seatsBooked <= 0) {
            throw new IllegalArgumentException("Booked seats must be greater than zero.");
        }

        this.ticketId = counter++;
        this.user = user;
        this.train = train;
        this.seatsBooked = seatsBooked;
    }

    public int getTicketId() {
        return ticketId;
    }

    public User getUser() {
        return user;
    }

    public Train getTrain() {
        return train;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    // Old getter name kept so existing code still works.
    public int getSeatBooked() {
        return getSeatsBooked();
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId
            + " | Train: " + train.getName()
            + " | Route: " + train.getSource() + " -> " + train.getDestination()
            + " | Seats: " + seatsBooked
            + " | Booked By: " + user.getFullName();

    }
}

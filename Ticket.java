public class Ticket {
    private final int ticketId;
    private final User user;
    private final Train train;
    private final int seatBooked;
    private static int counter = 1001;

    public Ticket(User user, Train train, int seatBooked) {
        this.ticketId = counter++;
        this.user = user;
        this.train = train;
        this.seatBooked = seatBooked;
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

    public int getSeatBooked() {
        return seatBooked;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId
            + " | Train: " + train.getName()
            + " | Route: " + train.getSource() + " -> " + train.getDestination()
            + " | Seats: " + seatBooked
            + " | Booked By: " + user.getFullname();

    }
}

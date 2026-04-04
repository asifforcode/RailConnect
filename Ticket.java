// this class holds all the booking details - who booked, which train, how many seats
public class Ticket {
    private final int ticketId;
    private final User user;
    private final Train train;
    private final int seatsBooked;
    private static int counter = 1001; 

    public Ticket(User user, Train train, int seatsBooked) {
        if (user == null) {
            throw new IllegalArgumentException("user can't be null");
        }
        if (train == null) {
            throw new IllegalArgumentException("train can't be null");
        }
        if (seatsBooked <= 0) {
            throw new IllegalArgumentException("seats should be 1 or more");
        }

        this.ticketId = counter++;
        this.user = user;
        this.train = train;
        this.seatsBooked = seatsBooked;
    }

    // getter method for ticket id
    public int getTicketId() {
        return ticketId;
    }

    // getter method for the user who booked this ticket
    public User getUser() {
        return user;
    }

    // getter method for the train this ticket is booked on
    public Train getTrain() {
        return train;
    }

    // getter method for number of seats booked
    public int getSeatsBooked() {
        return seatsBooked;
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

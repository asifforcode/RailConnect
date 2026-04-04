// this class stores all info about a train like name, route, and seats
public class Train {
    private final int trainId;
    private final String name;
    private final String source;
    private final String destination;
    private final int totalSeats;
    private int availableSeats;

    public Train(int trainId, String name, String source, String destination, int totalSeats) {
        if (trainId <= 0) {
            throw new IllegalArgumentException("train id should be more than 0");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("train name can't be empty");
        }
        if (source == null || source.isBlank() || destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("source and destination both are needed");
        }
        if (totalSeats <= 0) {
            throw new IllegalArgumentException("seats should be at least 1");
        }

        this.trainId = trainId;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats; 
    }

    // getter method for train id
    public int getTrainId() {
        return trainId;
    }

    // getter method for train name
    public String getName() {
        return name;
    }

    // getter method for source station
    public String getSource() {
        return source;
    }

    // getter method for destination station
    public String getDestination() {
        return destination;
    }

    // getter method for total seats in the train
    public int getTotalSeats() {
        return totalSeats;
    }

    // getter method for available seats
    public int getAvailableSeats() {
        return availableSeats;
    }

    // reduces seat count when someone books seats
    public boolean bookSeats(int count) {
        if (count <= 0 || count > availableSeats) {
            return false;
        }
        availableSeats -= count;
        return true;
    }

    // adds seats back when a ticket is cancelled
    public void cancelSeats(int count) {
        if (count <= 0) {
            return;
        }
        availableSeats = Math.min(totalSeats, availableSeats + count);
    }

    @Override
    public String toString() {
        return trainId + " | " + name + " | " + source + " -> " + destination
                + " | Seats Available: " + availableSeats;
    }
}

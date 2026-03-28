// Stores train info and manages available seats.
public class Train {
    private final int trainId;
    private final String name;
    private final String source;
    private final String destination;
    private final int totalSeats;
    private int availableSeats;

    public Train(int trainId, String name, String source, String destination, int totalSeats) {
        if (trainId <= 0) {
            throw new IllegalArgumentException("Train ID must be positive.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Train name cannot be empty.");
        }
        if (source == null || source.isBlank() || destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("Source and destination are required.");
        }
        if (totalSeats <= 0) {
            throw new IllegalArgumentException("Total seats must be greater than zero.");
        }

        this.trainId = trainId;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public int getTrainId() {
        return trainId;
    }

    // Old getter name kept so existing code still works.
    public int getTrainID() {
        return getTrainId();
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean bookSeats(int count) {
        if (count <= 0 || count > availableSeats) {
            return false;
        }
        availableSeats -= count;
        return true;
    }

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

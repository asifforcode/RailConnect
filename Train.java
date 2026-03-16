public class Train {
    private final int trainID;
    private final String name;
    private final String source;
    private final String destination;
    private final int totalSeats;
    private int availableSeats;

    public Train(int trainID, String name, String source, String destination, int totalSeats) {
        this.trainID = trainID;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public int getTrainID() {
        return trainID;
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
        return trainID + " | " + name + " | " + source + " -> " + destination
                + " | Seats Available: " + availableSeats;
    }
}

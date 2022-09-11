package org.garage;

public final class Floor {
    private final int numberOfParkingSpot;
    private int numberOfAvailableParkingSpot;

    public Floor(int numberOfParkingSpot) {
        this.numberOfParkingSpot = numberOfParkingSpot;
        this.numberOfAvailableParkingSpot = numberOfParkingSpot;
    }

    public int getNumberOfAvailableParkingSpot() {
        return numberOfAvailableParkingSpot;
    }

    public void setNumberOfAvailableParkingSpot(int numberOfAvailableParkingSpot) {
        this.numberOfAvailableParkingSpot = numberOfAvailableParkingSpot;
    }

    public boolean hasAvailableParkingSpot() {
        return getNumberOfAvailableParkingSpot() > 0;
    }
}

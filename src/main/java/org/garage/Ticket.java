package org.garage;

import java.util.UUID;


public final class Ticket {
    private final UUID id;
    private final int floorLevel;


    public Ticket(UUID id, int floorNumber) {
        this.id = id;
        this.floorLevel = floorNumber;
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return this.id.equals(ticket.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

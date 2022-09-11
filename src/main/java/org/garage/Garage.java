package org.garage;

import java.util.*;

public class Garage {
    private final Map<Integer, Floor> floors;
    private final Set<Ticket> ticketCache;

    public Garage(Map<Integer, Floor> floors) {
        this.floors = floors;
        this.ticketCache = new LinkedHashSet<>();
    }

    public Garage(Map<Integer, Floor> floors, Set<Ticket> ticketCache) {
        this.floors = floors;
        this.ticketCache = ticketCache;
    }

    /**
     * Apologies if functional programming hurt your eyes.
     * We find the first Optional<Floor> with an available parking spot and map it to an Optional<Ticket>
     * If there is a parking spot available, we create a new ticket and insert it into the Ticket Cache.
     *
     * @return an Optional<Ticket>
     */
    public Optional<Ticket> enter() {
        return floors.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .filter(floor -> floor.getValue().hasAvailableParkingSpot())
                .findFirst()
                .map(optFloor -> {
                            Ticket ticket = new Ticket(UUID.randomUUID(), optFloor.getKey());
                            if (!ticketCache.add(ticket)) {
                                //This should never happen unless there is a hash collision, we should probably add some logging!
                            }
                            return ticket;
                        }
                );
    }

    public void exit(Ticket ticket) {
        var floorLevel = ticket.getFloorLevel();
        if (ticketCache.remove(ticket)) {
            var floor = floors.get(floorLevel);
            floor.setNumberOfAvailableParkingSpot(floor.getNumberOfAvailableParkingSpot() + 1);
        }
    }
}

import org.garage.Floor;
import org.garage.Garage;
import org.garage.Ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//This is just a simple test setup.
public class GarageTest {

    private Garage garage;
    private Map<Integer, Floor> floorList;
    private Set<Ticket> ticketCache;


    @BeforeEach
    void setUp() {
        floorList = IntStream
            .range(-6, 8)
            .filter(i -> i != 0)
            .boxed()
            .collect(Collectors.toMap(Function.identity(), i -> {
                        var numberOfParkingSpot = i < 0 ? 40 : 20;
                        return new Floor(numberOfParkingSpot);
                    })
            );

        ticketCache = new LinkedHashSet<>();
        garage = new Garage(floorList, ticketCache);
    }

    @Test
    void whenEmptyParkingExpectToGetATicket() {
        //Arrange

        //Act
        var optTicket = garage.enter();
        var ticket = optTicket.orElseThrow();

        //Assert
        assertEquals(-6, ticket.getFloorLevel());
    }

    @Test
    void whenFullParkingExpectToNotGetATicket() {
        //Arrange
        floorList.forEach((key, floor) -> floor.setNumberOfAvailableParkingSpot(0));

        //Act
        var optTicket = garage.enter();

        //Assert
        assertTrue(optTicket.isEmpty());
    }


    //TODO: Add more unit tests and later integration tests.

    @AfterEach
    void tearDown() {
        ticketCache.clear();
        floorList.clear();
    }
}

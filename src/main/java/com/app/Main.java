package com.app;

import org.garage.Floor;
import org.garage.Garage;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //I don't remember the exact number of floors that was asked in the interview.
        Map<Integer, Floor> floorList = IntStream.range(-6, 8)
                .filter(i -> i != 0)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> {
                    var numberOfParkingSpot = i < 0 ? 40 : 20;
                    return new Floor(numberOfParkingSpot);
                })
        );
        Garage garage = new Garage(floorList);
    }
}
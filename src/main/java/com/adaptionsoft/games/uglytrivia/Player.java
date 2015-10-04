package com.adaptionsoft.games.uglytrivia;

public class Player {
    public static final int NUMBER_OF_PLACES = 12;
    private final String name;

    int place = 0;
    int purse = 0;

    public Player(String name) {

        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    void advanceByRoll(int roll) {
        place = (place + roll) % NUMBER_OF_PLACES;
    }
}

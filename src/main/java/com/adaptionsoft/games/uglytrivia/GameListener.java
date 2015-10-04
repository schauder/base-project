package com.adaptionsoft.games.uglytrivia;

public class GameListener {
    void playerAdded(String playerName, int numberOfPlayers) {
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + numberOfPlayers);
    }

    void rolled(int roll, Player player) {
        System.out.println(player + " is the current player");
        System.out.println("They have rolled a " + roll);
    }
}

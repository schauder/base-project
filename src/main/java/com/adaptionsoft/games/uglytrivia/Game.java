package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private GameListener listener = new GameListener();

    private List<Player> players = new ArrayList<>();
    boolean[] inPenaltyBox = new boolean[6];


    private Questions questions = new Questions();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    private Player currentPlayer() {
        return players.get(currentPlayer);
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName));
        inPenaltyBox[howManyPlayers()] = false;
        listener.playerAdded(playerName, players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        listener.rolled(roll, currentPlayer());

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer() + " is getting out of the penalty box");
                currentPlayer().advanceByRoll(roll);

                System.out.println(currentPlayer()
                        + "'s new location is "
                        + currentPlayer().place);
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(currentPlayer() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            currentPlayer().advanceByRoll(roll);

            System.out.println(currentPlayer()
                    + "'s new location is "
                    + currentPlayer().place);
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        System.out.println(questions.get(currentCategory()).removeFirst());
    }


    private Questions.Categories currentCategory() {
        return Questions.Categories.values()[currentPlayer().place % 4];
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                currentPlayer().purse++;
                System.out.println(currentPlayer()
                        + " now has "
                        + currentPlayer().purse
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            System.out.println("Answer was corrent!!!!");
            currentPlayer().purse++;
            System.out.println(currentPlayer()
                    + " now has "
                    + currentPlayer().purse
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer() + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(currentPlayer().purse == 6);
    }
}
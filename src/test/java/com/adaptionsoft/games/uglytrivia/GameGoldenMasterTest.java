package com.adaptionsoft.games.uglytrivia;

import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameGoldenMasterTest {

    public static final String OUTPUT = "output.txt";
    public static final String MASTER = "master.txt";

    @Test
    public void testRandomGames() throws IOException {
        runGame(OUTPUT);
        compare(MASTER, OUTPUT);
    }

    private void compare(String master, String output) throws IOException {
        try (
                BufferedReader masterReader = new BufferedReader(new FileReader(new File(master)));
                BufferedReader outputReader = new BufferedReader(new FileReader(new File(output)))
        ) {
            String line;
            while ((line = masterReader.readLine()) != null) {
                assertEquals(line, outputReader.readLine());
            }
            assertNull("master is longer then output", masterReader.readLine());
        }
    }

    @Test
    @Ignore
    public void createMaster() throws FileNotFoundException {
        runGame(MASTER);
    }

    private void runGame(String fileName) throws FileNotFoundException {
        final PrintStream out = new PrintStream(new File(fileName));
        System.setOut(out);

        for (int seed = 0; seed < 1000; seed++) {
            randomRun(seed);
        }

        out.close();
    }

    public void randomRun(long seed) {
        Random rand = new Random(seed);

        Game aGame = new Game();

        for (int i = 0; i < rand.nextInt(5) + 1; i++) {
            aGame.add("Player" + i);
        }

        while (rollOnceAndCheckIfShouldContinueWinning(rand, aGame)) {
        }
    }

    private boolean rollOnceAndCheckIfShouldContinueWinning(Random rand, Game aGame) {
        aGame.roll(rand.nextInt(5) + 1);
        if (rand.nextInt(9) == 7) {
            return aGame.wrongAnswer();
        } else {
            return aGame.wasCorrectlyAnswered();
        }
    }
}
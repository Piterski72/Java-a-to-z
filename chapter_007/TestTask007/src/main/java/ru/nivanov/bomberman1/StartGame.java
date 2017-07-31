package ru.nivanov.bomberman1;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Nikolay Ivanov on 31.07.2017.
 */
public class StartGame {

    private final ReentrantLock[][] board;
    private final boolean[][] status;
    private final int size;
    private final Random rnd = new Random();
    private final int numberOfHeroes;
    private final Hero[] gameHeroes;

    /**
     * Constructor.
     * @param size ..
     * @param heroes ..
     */
    private StartGame(int size, int heroes) {

        this.size = size;
        this.board = new ReentrantLock[size][size];
        this.status = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new ReentrantLock();
                this.status[i][j] = false;
            }
        }

        this.numberOfHeroes = heroes;
        this.gameHeroes = new Hero[heroes];

        for (int i = 0; i < this.gameHeroes.length; i++) {
            this.gameHeroes[i] = new Hero(this.board, String.valueOf(i));
        }
    }

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {

        final int size = 9;
        final int numOfHeroes = 2;
        final long gameTime = 10000;

        StartGame game = new StartGame(size, numOfHeroes);
        game.initGame();
        game.start();
        try {
            Thread.sleep(gameTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        game.stopGame();

    }

    /**
     * Set up heroes on board.
     */
    private void initGame() {

        int count = this.numberOfHeroes - 1;

        while (count >= 0) {

            int xCoord = rnd.nextInt(this.size);
            int yCoord = rnd.nextInt(this.size);

            if (!this.status[xCoord][yCoord]) {
                this.gameHeroes[count].setHero(this.board[xCoord][yCoord], xCoord, yCoord);
                this.status[xCoord][yCoord] = true;
                System.out.println(String.format("Hero %s set up in %d, %d", this.gameHeroes[count], xCoord, yCoord));
                count--;
            }
        }
    }

    /**
     * Starting the game.
     */
    private void start() {

        for (Hero hero : this.gameHeroes) {
            new Thread(hero).start();
        }
    }

    /**
     * Stop the game.
     */
    private void stopGame() {
        for (Hero hero : this.gameHeroes) {
            hero.setStop();
        }

    }

}

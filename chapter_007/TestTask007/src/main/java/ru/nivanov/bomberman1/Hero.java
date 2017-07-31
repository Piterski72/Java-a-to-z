package ru.nivanov.bomberman1;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Nikolay Ivanov on 26.07.2017.
 */
class Hero implements Runnable {

    private final ReentrantLock[][] board;
    private final String name;
    private ReentrantLock lock;
    private int xCoord;
    private int yCoord;
    private boolean isStopped;

    /**
     * Constructor.
     * @param board ..
     */
    Hero(ReentrantLock[][] board, String name) {
        this.board = board;
        this.name = name;
    }

    /**
     * Hero makes move.
     */
    private void move() {

        boolean result;
        int xNewPos;
        int yNewPos;
        final long delay = 500;
        final long sleepTime = 1000;


        this.lock.lock();

        while (!this.isStopped) {

            do {
                xNewPos = this.xCoord + ThreadLocalRandom.current().nextInt(-1, 2);
                yNewPos = this.yCoord + ThreadLocalRandom.current().nextInt(-1, 2);
                result = validateNextMove(xNewPos, yNewPos);

            } while (!result);

            try {
                if (this.board[xNewPos][yNewPos].tryLock(delay, TimeUnit.MILLISECONDS)) {
                    this.lock.unlock();
                    this.lock = this.board[xNewPos][yNewPos];
                    this.xCoord = xNewPos;
                    this.yCoord = yNewPos;
                    System.out.println(String.format("Hero %s now in %d, %d", this.name, this.xCoord, this.yCoord));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set up hero on board.
     * @param lock ..
     * @param x ..
     * @param y ..
     */
    void setHero(ReentrantLock lock, int x, int y) {
        this.lock = lock;
        this.xCoord = x;
        this.yCoord = y;
    }

    /**
     * Checks for correct cell coordinates.
     * @param xPos ..
     * @param yPos ..
     * @return ..
     */
    private boolean validateNextMove(int xPos, int yPos) {
        return ((xPos < this.board.length && xPos >= 0) && (yPos < this.board.length && yPos >= 0));
    }

    /**
     * Stop the hero.
     */
    void setStop() {
        this.isStopped = true;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     * @see Thread#run()
     */
    @Override
    public void run() {
        move();
    }
}


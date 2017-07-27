package ru.nivanov.bomberman2;

import java.util.List;

/**
 * Created by Nikolay Ivanov on 24.07.2017.
 */
public class BomberMan extends Figure {
    /**
     * Constructor.
     * @param name ..
     * @param cells ..
     * @param currentPosCell ..
     */
    BomberMan(String name, Cell[][] cells, Cell currentPosCell) {
        super(name, cells, currentPosCell);
    }

    /**
     * Name getter.
     * @return name.
     */
    public String getName() {
        return name;
    }

    @Override
    public String id() {
        return "player";
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
        List<Cell> possibleMoves;

        while (!Game.isIsFinished()) {

            possibleMoves = validateMove(currentPosCell);
            for (Cell move : possibleMoves) {
                if (move != null) {
                    synchronized (move) {
                        if (!move.isBlocked() && move.getContent() == null) {
                            tryMove(move);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            }
            if (Game.isIsFinished()) {
                break;
            }

        }

    }
}

package ru.nivanov.bomberman2;

import java.util.List;
import java.util.Random;

/**
 * Created by Nikolay Ivanov on 24.07.2017.
 */
public class Monster extends Figure {

    /**
     * Constructor.
     * @param name ..
     * @param cells ..
     * @param position ..
     */
    Monster(String name, Cell[][] cells, Cell position) {
        super(name, cells, position);
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
        Random rnd;

        while (!Game.isIsFinished()) {

            possibleMoves = validateMove(currentPosCell);

            if (!possibleMoves.isEmpty()) {

                while (true) {
                    rnd = new Random();
                    int select = rnd.nextInt(possibleMoves.size());
                    if (possibleMoves.get(select) != null) {

                        Cell destination = possibleMoves.get(select);
                        synchronized (destination) {
                            if (!destination.isBlocked() && destination.getContent() == null) {
                                tryMove(destination);
                                break;
                            } else if (destination.getContent() != null && destination.getContent().id().equals(
                                    "player")) {
                                System.out.println(String.format("monster %s kills the player!", this.getName()));
                                Game.setIsFinished(true);
                                break;

                            }
                            if (destination.getContent() != null && destination.getContent().id().equals("monster")) {
                                try {
                                    Thread.sleep(5000);
                                    System.out.println(String.format("Monster %s is sleeping", this.getName()));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }

                        }
                    }
                }
            }
        }
    }

    /**
     * Name getter.
     * @return name.
     */
    protected String getName() {
        return this.name;
    }

    @Override
    public String id() {
        return "monster";
    }

}

package ru.nivanov.bomberman2;

/**
 * Created by Nikolay Ivanov on 24.07.2017.
 */
public class Game {

    private static volatile boolean isFinished = false;
    private final int numberOfBlocks;
    private final Cell[][] cells;
    private final int size;
    private final int numberOfMonsters;
    private Figure[] monsters;

    /**
     * Constructor.
     * @param size ..
     * @param monsters ..
     */
    private Game(int size, int monsters, int blocks) {
        this.size = size;
        this.cells = new Cell[size][size];
        this.numberOfMonsters = monsters;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }
        this.numberOfBlocks = blocks;
    }

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {

        Game game = new Game(9, 3, 4);
        game.initBlocks();
        game.setUpMonsters();
        for (int i = 0; i < game.size; i++) {
            for (int j = 0; j < game.size; j++) {
                System.out.println(String.format("cell %d %d status %s", i, j, game.cells[i][j].isBlocked()));
                System.out.println(String.format("cell %d %d content %s", i, j, game.cells[i][j].getContent()));
            }
        }
        game.startGame();
    }

    /**
     * Check if game is finished.
     * @return ..
     */
    static boolean isIsFinished() {
        return isFinished;
    }

    /**
     * Set finish flag.
     * @param isFinished ..
     */
    static synchronized void setIsFinished(boolean isFinished) {
        Game.isFinished = isFinished;
    }

    /**
     * Start game method.
     */
    private void startGame() {

        new Thread(this.initPlayer()).start();

        for (Figure value : this.monsters) {
            new Thread(value).start();
        }
    }

    /**
     * Setting up blocks.
     */
    private void initBlocks() {
        Cell cell;
        int count = this.numberOfBlocks;
        while (count != 0) {
            cell = getRandomCell();

            if (!cell.isBlocked()) {
                cell.setBlocked(true);
                count--;
            }
        }
    }

    /**
     * Generate random cell.
     * @return ..
     */
    private Cell getRandomCell() {

        int xCoord;
        int yCoord;
        xCoord = (int) (Math.random() * this.size);
        yCoord = (int) (Math.random() * this.size);

        return this.cells[xCoord][yCoord];
    }

    /**
     * Setting up monsters.
     */
    private void setUpMonsters() {
        this.monsters = new Monster[this.numberOfMonsters];
        Monster monster;
        Cell cell;
        int count = 0;
        while (count != this.numberOfMonsters) {
            cell = getRandomCell();
            if (!cell.isBlocked() && cell.getContent() == null) {
                monster = new Monster(String.valueOf(count), this.cells, cell);
                this.monsters[count] = monster;
                count++;
            }
        }
    }

    /**
     * Set up player.
     * @return ..
     */
    private BomberMan initPlayer() {
        BomberMan bomberMan = null;
        Cell cell;
        boolean result = false;
        while (!result) {
            cell = getRandomCell();
            if (!cell.isBlocked() && cell.getContent() == null) {
                bomberMan = new BomberMan("User", this.cells, cell);
                result = true;
                System.out.println(
                        String.format("User %s is in cell %d %d ", bomberMan.id(), cell.getxPos(), cell.getyPos()));
            }
        }
        return bomberMan;
    }

}

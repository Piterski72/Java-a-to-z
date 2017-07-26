package ru.nivanov.bomberman2;

/**
 * Created by Nikolay Ivanov on 24.07.2017.
 */
class Cell {

    private final int xPos;
    private final int yPos;
    private volatile boolean isBlocked = false;
    private Figure content;

    /**
     * Constructor for a new cell.
     */
    Cell(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Checks for block in cell.
     * @return result.
     */
    boolean isBlocked() {
        return isBlocked;
    }

    /**
     * Setting a block in cell.
     * @param blocked ..
     */
    void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    /**
     * Returns cell content.
     * @return ..
     */
    Figure getContent() {
        synchronized (this) {
            return content;
        }
    }

    /**
     * Set up cell content.
     * @param content ..
     */
    void setContent(Figure content) {
        this.content = content;
    }

    /**
     * Get x position.
     * @return x coord.
     */
    int getxPos() {
        return xPos;
    }

    /**
     * Get y position.
     * @return y coord.
     */
    int getyPos() {
        return yPos;
    }
}

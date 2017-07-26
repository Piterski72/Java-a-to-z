package ru.nivanov.bomberman2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikolay Ivanov on 24.07.2017.
 */
abstract class Figure implements Runnable {

    final String name;
    private final Cell[][] cells;
    Cell currentPosCell;

    /**
     * Constructor.
     * @param name ..
     * @param cells ..
     * @param currentPosCell ..
     */
    Figure(String name, Cell[][] cells, Cell currentPosCell) {
        this.name = name;
        this.cells = cells;
        this.currentPosCell = currentPosCell;
        this.currentPosCell.setContent(this);
    }

    /**
     * Return figure id.
     * @return id.
     */
    protected abstract String id();

    /**
     * Name getter.
     * @return ..
     */
    protected abstract String getName();

    /**
     * Checks for cell to move to.
     * @param from ..
     * @return ..
     */
    List<Cell> validateMove(Cell from) {
        List<Cell> results = new ArrayList<>();

        results.add(validateNextCell(from.getxPos() - 1, from.getyPos()));
        results.add(validateNextCell(from.getxPos() + 1, from.getyPos()));
        results.add(validateNextCell(from.getxPos(), from.getyPos() - 1));
        results.add(validateNextCell(from.getxPos(), from.getyPos() + 1));

        return results;
    }

    /**
     * Checks for correct cell coordinates.
     * @param xPos ..
     * @param yPos ..
     * @return ..
     */
    private Cell validateNextCell(int xPos, int yPos) {
        Cell result = null;
        if ((xPos < this.cells.length && xPos >= 0) && (yPos < this.cells.length && yPos >= 0)) {
            result = this.cells[xPos][yPos];
        }
        return result;
    }

    /**
     * Move figure to next cell.
     * @param cell ..
     */
    synchronized void tryMove(Cell cell) {

        this.currentPosCell.setContent(null);
        this.currentPosCell = cell;
        this.currentPosCell.setContent(this);
        System.out.println(
                String.format("%s %s is now in cell %d %d", id(), this.getName(), this.currentPosCell.getxPos(),
                        this.currentPosCell.getyPos()));

    }

}

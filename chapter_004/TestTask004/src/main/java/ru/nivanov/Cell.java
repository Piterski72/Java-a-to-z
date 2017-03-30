package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 27.03.2017.
 */
public class Cell {
    /**
     * x and y coordinates.
     */
    private int x, y;
    private String cellContent;

    /**
     * Creates new Cell.
     * @param x x coordinate.
     * @param y y coordinate.
     */
    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter for x.
     * @return x coordinate.
     */
    int getX() {
        return this.x;
    }

    /**
     * getter for y.
     * @return y coordinate.
     */
    int getY() {
        return this.y;
    }

    /**
     * Getter for cell content.
     * @return cell content.
     */
    String getCellContent() {
        return cellContent;
    }

    /**
     * Cell content setter.
     * @param cellContent ..
     */
    void setCellContent(String cellContent) {
        this.cellContent = cellContent;
    }

    @Override
    public String toString() {
        return cellContent;
    }
}

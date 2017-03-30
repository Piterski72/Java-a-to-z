package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 27.03.2017.
 */
class GameBoard {
    private Cell[][] cells;

    /**
     * Creates new Field object.
     */
    GameBoard(Cell[][] cells) {
        this.cells = cells;
    }

    /**
     * Verifies that cell is not occupied.
     * @param x X coordinate.
     * @param y Y coordinates
     * @return true if cell(x,y) has mark.
     */
    boolean verifyEmpty(int x, int y) {
        return (this.cells[x][y].getCellContent().equals(""));
    }

    /**
     * Returns cells size.
     * @return size of cells.
     */
    int getSize() {
        return this.cells.length;
    }

    /**
     * Cell getter.
     * @param x ..
     * @param y ..
     * @return ..
     */
    Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

    /**
     * Setter for cell content with string mark.
     * @param x ..
     * @param y ..
     * @param mark ..
     */
    void setCell(int x, int y, String mark) {
        this.cells[x][y].setCellContent(mark);
    }

    /**
     * Checks horizontal, vertical, diagonal lines.
     * If line contains equal Strings method will return true.
     * @param cell ..
     * @return true if some line contains of equal Strings.
     */
    boolean isWinner(Cell cell) {
        boolean winCondition = false;
        int matchOne = 0;
        int matchTwo = 0;
        if (checkLines(cell, matchOne, matchTwo) || checkDiagLines(cell, matchOne, matchTwo)) {
            winCondition = true;
        }
        return winCondition;
    }

    /**
     * Horizontal and vertical lines check.
     * @param cell ..
     * @param matchOne is horizontal line condition.
     * @param matchTwo is vertical line condition
     * @return ..
     */
    private boolean checkLines(Cell cell, int matchOne, int matchTwo) {
        boolean result = false;
        int x = cell.getX();
        int y = cell.getY();
        for (int i = 0; i < this.cells.length; i++) {
            if (cell.getCellContent().equals(this.cells[x][i].getCellContent())) {
                matchOne++;
            }
            if (cell.getCellContent().equals(this.cells[i][y].getCellContent())) {
                matchTwo++;
            }
        }
        if (matchOne == cells.length || matchTwo == cells.length) {
            result = true;
        }
        return result;
    }

    /**
     * Diagonal lines check.
     * @param cell ..
     * @param matchOne is diagonal line condition.
     * @param matchTwo is reverse diagonal line condition.
     * @return ..
     */
    private boolean checkDiagLines(Cell cell, int matchOne, int matchTwo) {
        boolean result = false;
        int x = cell.getX();
        int y = cell.getY();
        if (x == y || (x + y) == (this.cells.length - 1)) {
            for (int i = 0; i < this.cells.length; i++) {
                if (cell.getCellContent().equals(this.cells[i][i].getCellContent())) {
                    matchOne++;
                }
                if (cell.getCellContent().equals(this.cells[i][this.cells.length - i - 1].getCellContent())) {
                    matchTwo++;
                }
            }
        }
        if (matchOne == cells.length || matchTwo == cells.length) {
            result = true;
        }
        return result;
    }

    /**
     * Verifies empty cells.
     * @return true is some cells are empty else false.
     */
    boolean isFull() {
        for (Cell[] cell : this.cells) {
            for (int j = 0; j < this.cells.length; j++) {
                if (cell[j].getCellContent().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] x : this.cells) {
            sb.append("|");
            for (Cell y : x) {
                if (y.getCellContent().equals("")) {
                    sb.append(" ");
                } else {
                    sb.append(y);
                }
                sb.append("|");
            }
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}

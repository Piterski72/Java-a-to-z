package ru.nivanov.PersonalTask004;

/**
 * Created by Nikolay Ivanov on 03.04.2017.
 */
class CheckWinRecurs {
    private final int three = 3;
    private final int four = 4;
    private final String[][] field;
    private boolean[] results = new boolean[four];

    /**
     * Constructor.
     * @param field ..
     */
    CheckWinRecurs(String[][] field) {
        this.field = field;
    }

    /**
     * @return result of win condition.
     */
    boolean winCombination() {
        boolean hasWinComb = false;
        checkHor(0, 0, 0);
        checkVert(0, 0, 0);
        checkDiagonal(0, 0, 0);
        checkReverseDiagonal(0, 0, 0);
        if (this.results[0] || this.results[1] || this.results[2] || this.results[three]) {
            hasWinComb = true;
        }
        return hasWinComb;
    }

    /**
     * check horizontal win condition.
     * @param row ..
     * @param column ..
     * @param foundMatches ..
     */
    private void checkHor(int row, int column, int foundMatches) {
        if (foundMatches < (this.field.length - 1)) {
            String currentCell = this.field[row][column];
            column++;
            if (column < this.field.length) {
                if (currentCell.equals(this.field[row][column])) {
                    foundMatches++;
                } else {
                    foundMatches = 0;
                    column = 0;
                    row++;
                }
                if (row < this.field.length) {
                    checkHor(row, column, foundMatches);
                }
            }
        } else {
            this.results[0] = true;
        }
    }

    /**
     * check vertical win condition.
     * @param row ..
     * @param column ..
     * @param foundMatches ..
     */
    private void checkVert(int row, int column, int foundMatches) {
        if (foundMatches < (this.field.length - 1)) {
            String currentCell = this.field[row][column];
            row++;
            if (row < this.field.length) {
                if (currentCell.equals(this.field[row][column])) {
                    foundMatches++;
                } else {
                    foundMatches = 0;
                    row = 0;
                    column++;
                }
                if (column < this.field.length) {
                    checkVert(row, column, foundMatches);
                }
            }
        } else {
            this.results[1] = true;
        }
    }

    /**
     * check diagonal win condition.
     * @param row ..
     * @param column ..
     * @param foundMatches ..
     */
    private void checkDiagonal(int row, int column, int foundMatches) {
        String currentCell = this.field[row][column];
        if (foundMatches == this.field.length - 1) {
            results[2] = true;
        }
        row++;
        column++;
        if (row < this.field.length && column < this.field.length) {
            if (currentCell.equals(this.field[row][column])) {
                foundMatches++;
                checkDiagonal(row, column, foundMatches);
            }
        }
    }

    /**
     * check reverse diagonal win condition.
     * @param row ..
     * @param column ..
     * @param foundMatches ..
     */
    private void checkReverseDiagonal(int row, int column, int foundMatches) {
        String currentCell = this.field[row][this.field.length - column - 1];
        if (foundMatches == this.field.length - 1) {
            results[three] = true;
        }
        row++;
        column++;
        if (row < this.field.length && column < this.field.length) {
            if (currentCell.equals(this.field[row][this.field.length - column - 1])) {
                foundMatches++;
                checkReverseDiagonal(row, column, foundMatches);
            }
        }
    }
}

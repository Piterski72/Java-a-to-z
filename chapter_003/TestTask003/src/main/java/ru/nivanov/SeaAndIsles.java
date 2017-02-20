package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 15.02.2017.
 */
class SeaAndIsles {
    private final int[][] sea;
    private final boolean[][] visitedArray;
    private int countMax;

    SeaAndIsles(int[][] sea) {
        this.sea = sea;
        this.visitedArray = new boolean[this.sea.length][this.sea[0].length];
    }

    /**
     * method for finding the biggest isle.
     * @return result
     */
    int findBiggestIsle() {
        int count;
        for (int i = 0; i < this.sea.length; i++) {
            for (int j = 0; j < this.sea[0].length; j++) {
                count = passArray(i, j);
                if (count > this.countMax) {
                    this.countMax = count;
                }
            }
        }
        return this.countMax;
    }

    /**
     * @param i is raw.
     * @param j is column
     * @return square
     */
    private int passArray(int i, int j) {
        int count = 0;
        if (i >= 0 & i < this.sea.length && j >= 0 & j < this.sea[0].length) {
            if (!this.visitedArray[i][j]) {
                this.visitedArray[i][j] = true;
                if (this.sea[i][j] == 1) {
                    count++;
                    count += passArray(i, j + 1);
                    count += passArray(i + 1, j);
                    count += passArray(i, j - 1);
                    count += passArray(i - 1, j);
                }
            }
        }
        return count;
    }
}

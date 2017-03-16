package ru.nivanov.start;

/**
 * Cell class.
 * @author nivanov.
 */
public class Cell {
    private final int hPos;
    private final int vPos;

    public Cell(int hPos, int vPos) {
        this.hPos = hPos;
        this.vPos = vPos;
    }

    public int getHpos() {
        return this.hPos;
    }

    public int getVpos() {
        return this.vPos;
    }

    /**
     * Check if cells are equals.
     * @param cell ..
     * @return result of comparison
     */
    boolean cellCompare(Cell cell) {
        boolean itog = false;
        itog = this.getHpos() == cell.getHpos() && this.getVpos() == cell.getVpos();
        return itog;
    }
}
package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 30.03.2017.
 */
public class GameBoardTest {
    private Cell[][] cells = new Cell[2][2];

    /**
     * test for checking if cell is empty.
     */
    @Test
    public void whenVerifyEmptyThenReturnResult() {
        cells[0][0] = new Cell(0, 0);
        cells[0][0].setCellContent("");
        boolean expected = true;
        boolean result = new GameBoard(this.cells).verifyEmpty(0, 0);
        assertThat(result, is(expected));
    }

    /**
     * test for size.
     */
    @Test
    public void whenGetSizeThenReturnResult() {
        int expected = 2;
        int result = new GameBoard(this.cells).getSize();
        assertThat(result, is(expected));
    }

    /**
     * test for getting cell.
     */
    @Test
    public void whenGetCellThenReturnResult() {
        cells[0][1] = new Cell(0, 1);
        Cell result = cells[0][1];
        assertThat(result, is(new GameBoard(this.cells).getCell(0, 1)));
    }

    /**
     * test for setting sel content.
     */
    @Test
    public void whenSetCellThenReturnResult() {
        cells[1][1] = new Cell(1, 1);
        cells[1][1].setCellContent("X");
        String expected = "X";
        assertThat(new GameBoard(this.cells).getCell(1, 1).toString(), is(expected));
    }

    /**
     * test for win condition.
     */
    @Test
    public void whenIsWinnerThenReturnResult() {
        Cell[][] underTest = new Cell[2][2];
        underTest[0][0] = new Cell(0, 0);
        underTest[0][0].setCellContent("O");

        underTest[0][1] = new Cell(0, 1);
        underTest[0][1].setCellContent("X");

        underTest[1][0] = new Cell(1, 0);
        underTest[1][0].setCellContent("X");

        underTest[1][1] = new Cell(1, 1);
        underTest[1][1].setCellContent("");
        boolean expected = true;
        assertThat(new GameBoard(underTest).isWinner(underTest[0][1]), is(expected));
    }

    /**
     * test for win condition2.
     */
    @Test
    public void whenNoWinnerThenReturnResult() {
        Cell[][] underTest = new Cell[2][2];
        underTest[0][0] = new Cell(0, 0);
        underTest[0][0].setCellContent("O");

        underTest[0][1] = new Cell(0, 1);
        underTest[0][1].setCellContent("a");

        underTest[1][0] = new Cell(1, 0);
        underTest[1][0].setCellContent("X");

        underTest[1][1] = new Cell(1, 1);
        underTest[1][1].setCellContent("");
        boolean expected = false;
        assertThat(new GameBoard(underTest).isWinner(underTest[0][1]), is(expected));
    }

    /**
     * test for board with no empty cells.
     */
    @Test
    public void isFull() {
        Cell[][] newCells = new Cell[1][1];
        newCells[0][0] = new Cell(0, 0);
        newCells[0][0].setCellContent("Z");
        boolean expected = true;
        assertThat(new GameBoard(newCells).isFull(), is(expected));

    }
}
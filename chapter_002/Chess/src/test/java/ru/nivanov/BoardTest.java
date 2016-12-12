package ru.nivanov.start;
import ru.nivanov.models.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
/**
 * BoardTest class.
 * @author nivanov.
 * @since
 * @version
 */
 public class BoardTest {
	/**
	* Test for bishop move ok.
	* @throws ImpossibleMoveException ..
	* @throws OccupiedWayException ..
	* @throws FigureNotFoundException ..
	*/
	@Test
	public void whenMoveCorrectThenReturnOk() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
		Board board = new Board();
		final int three = 3;
		final int four = 4;
		final int five = 5;
		final int eight = 8;
		Bishop bishop = new Bishop(new Cell(four, four));
		board.fillBoard(bishop);
		Cell source = new Cell(four, four);
		Cell dist = new Cell(eight, eight);
		board.move(source, dist);
		Figure[] test = board.getFigures();
		assertThat(test[0].getPosition(), is(dist));
	}
	/**
	* Test for bishop move FigureNotFound error.
	* @throws ImpossibleMoveException ..
	* @throws OccupiedWayException ..
	* @throws FigureNotFoundException ..
	*/
	@Test
	public void whenFigureNotFoundThenReturnOk() {
		Board board = new Board();
		final int four = 4;
		final int eight = 8;
		board.fillBoard(new Bishop(new Cell(four, four)));
		Figure[] test = board.getFigures();
		Cell source = new Cell(1, four);
		Cell dist = new Cell(eight, eight);
		Cell expect = test[0].getPosition();
		assertThat(expect.cellCompare(source), is(false));
	}
	/**
	* Test for bishop move when way is not free.
	* @throws ImpossibleMoveException ..
	*/
	@Test
	public void whenOccupiedWayThenReturnOk() throws ImpossibleMoveException {
		Board board = new Board();
		final int four = 4;
		final int seven = 7;
		final int eight = 8;
		board.fillBoard(new Bishop(new Cell(four, four)));
		board.fillBoard(new Bishop(new Cell(seven, seven)));
		Cell dist = new Cell(eight, eight);
		Figure[] test = board.getFigures();
		Cell[] cell = test[0].way(dist);
		Cell expect = test[1].getPosition();
		assertThat(cell[2].cellCompare(expect), is(true));
	}
	/**
	* Test for checkway method.
	* @throws ImpossibleMoveException ..
	* @throws OccupiedWayException ..
	*/
	@Test
	public void whenCheckWayCorrectThenReturnOk() throws ImpossibleMoveException, OccupiedWayException {
		Board board = new Board();
		final int four = 4;
		final int eight = 8;
		board.fillBoard(new Bishop(new Cell(four, four)));
		Figure[] test = board.getFigures();
		Cell source = new Cell(four, four);
		Cell dist = new Cell(eight, eight);
			Cell[] res = test[0].way(dist);
			boolean what = board.checkWay(res);
			assertThat(what, is(true));
	}
	/**
	* Test for cell compare.
	*
	*/
	@Test
	public void whenCompareCellsCorrectThenReturnOk() {
		final int four = 4;
		final int eight = 8;
		Cell source = new Cell(four, four);
		Cell dist = new Cell(eight, eight);
		Cell trtr = new Cell(four, four);
		boolean result = trtr.cellCompare(source);
		assertThat(result, is(true));
	}
	/**
	* Test for exception.
	* @throws ImpossibleMoveException ..
	* @throws OccupiedWayException ..
	* @throws FigureNotFoundException ..
	*/
	@Test(expected = ImpossibleMoveException.class)
	public void whenImpossibleMoveThenReturnOk() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
		Board board = new Board();
		final int four = 4;
		final int eight = 8;
		board.fillBoard(new Bishop(new Cell(four, four)));
		Cell source = new Cell(four, four);
		Cell dist = new Cell(eight, four);
		board.move(source, dist);
	}
	/**
	* Test for exception.
	* @throws ImpossibleMoveException ..
	* @throws OccupiedWayException ..
	* @throws FigureNotFoundException ..
	*/
	@Test(expected = OccupiedWayException.class)
	public void whenOccupiedWayExistsThenReturnOk() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
		Board board = new Board();
		final int four = 4;
		final int seven = 7;
		final int eight = 8;
		board.fillBoard(new Bishop(new Cell(four, four)));
		board.fillBoard(new Bishop(new Cell(seven, seven)));
		Cell source = new Cell(four, four);
		Cell dist = new Cell(eight, eight);
		board.move(source, dist);
	}
	/**
	* Test for exception.
	* @throws ImpossibleMoveException ..
	* @throws OccupiedWayException ..
	* @throws FigureNotFoundException ..
	*/
	@Test(expected = FigureNotFoundException.class)
	public void whenFigureNotFoundExceptionExistsThenReturnOk() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
		Board board = new Board();
		final int four = 4;
		final int seven = 7;
		final int eight = 8;
		board.fillBoard(new Bishop(new Cell(four, four)));
		board.fillBoard(new Bishop(new Cell(seven, seven)));
		Cell source = new Cell(1, four);
		Cell dist = new Cell(eight, eight);
		board.move(source, dist);
	}
}
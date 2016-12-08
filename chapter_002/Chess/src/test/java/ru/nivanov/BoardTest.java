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
	*
	*/
	@Test
	public void whenMoveCorrectThenReturnOk() {
		Board board = new Board();
		final int three = 3;
		final int four = 4;
		final int five = 5;
		final int eight = 8;
		board.fillBoard(new Bishop(new Cell(four, four)));
		Figure[] test = board.getFigures();
		Cell source = new Cell(four, four);
		Cell dist = new Cell(0, eight);
		try {
			board.move(source, dist);
		} catch (ImpossibleMoveException ime) {
			System.out.println("ime error");
		} catch (OccupiedWayException owe) {
			System.out.println("owe error");
		} catch (FigureNotFoundException fnfe) {
			System.out.println("fnfe error");
		}
		assertThat(test[0].getPosition(), is(dist));
	}
	/**
	* Test for bishop move FigureNotFound error.
	*
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
		boolean err = true;
		try {
			board.move(source, dist);
		} catch (ImpossibleMoveException ime) {
			System.out.println("ime error");
		} catch (OccupiedWayException owe) {
			System.out.println("owe error");
		} catch (FigureNotFoundException fnfe) {
			System.out.println("FigureNotFound error");
			err = false;
		}
		assertThat(err, is(false));
	}
	/**
	* Test for bishop move OccupiedWayException error.
	*
	*/
	@Test
	public void whenOccupiedWayThenReturnOk() {
		Board board = new Board();
		final int four = 4;
		final int seven = 7;
		final int eight = 8;
		board.fillBoard(new Bishop(new Cell(four, four)));
		board.fillBoard(new Bishop(new Cell(eight, eight)));
		board.fillBoard(new Bishop(new Cell(four, seven)));
		Cell source = new Cell(four, four);
		Cell dist = new Cell(eight, eight);
		boolean err = true;
		try {
			board.move(source, dist);
		} catch (ImpossibleMoveException ime) {
			System.out.println("ime error");
		} catch (OccupiedWayException owe) {
			System.out.println("owe error");
			err = false;
		} catch (FigureNotFoundException fnfe) {
			System.out.println("FigureNotFound error");
		}
		assertThat(err, is(false));
	}
	/**
	* Test for bishop move ImpossibleMoveException error.
	*
	*/
	@Test
	public void whenImpossibleMoveThenReturnOk() {
		Board board = new Board();
		final int four = 4;
		final int eight = 8;
		board.fillBoard(new Bishop(new Cell(four, four)));
		board.fillBoard(new Bishop(new Cell(eight, eight)));
		Figure[] test = board.getFigures();
		Cell source = new Cell(four, four);
		Cell dist = new Cell(eight, four);
		boolean err = true;
		try {
			board.move(source, dist);
		} catch (ImpossibleMoveException ime) {
			err = false;
			System.out.println("ime error");
		} catch (OccupiedWayException owe) {
			System.out.println("owe error");
		} catch (FigureNotFoundException fnfe) {
			System.out.println("FigureNotFound error");
		}
		assertThat(err, is(false));
	}
	/**
	* Test for way method.
	*
	*/
	@Test
	public void whenWayCorrectThenReturnOk() {
		Board board = new Board();
		final int four = 4;
		final int five = 5;
		final int eight = 8;
		board.fillBoard(new Bishop(new Cell(four, four)));
		Figure[] test = board.getFigures();
		Cell source = new Cell(four, four);
		Cell dist = new Cell(eight, eight);
		Cell expect = new Cell(five, four);
		try {
			Cell[] res = test[0].way(dist);
			assertThat(res[four - 1].cellCompare(dist), is(true));
			} catch (ImpossibleMoveException ime) {
			System.out.println("ime error");
		}
	}
	/**
	* Test for checkway method.
	*
	*/
	@Test
	public void whenCheckWayCorrectThenReturnOk() {
		Board board = new Board();
		final int four = 4;
		final int eight = 8;
		board.fillBoard(new Bishop(new Cell(four, four)));
		Figure[] test = board.getFigures();
		Cell source = new Cell(four, four);
		Cell dist = new Cell(eight, eight);
		try {
			Cell[] res = test[0].way(dist);
			int what = board.checkWay(res);
			System.out.println(what);
			assertThat(what, is(0));
		} catch (OccupiedWayException owe) {
			System.out.println("owe error");
		} catch (ImpossibleMoveException ime) {
			System.out.println("ime error");
		}
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
}
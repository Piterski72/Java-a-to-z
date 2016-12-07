package ru.nivanov.models;
import ru.nivanov.start.*;
import static java.lang.Math.abs;

/**
 * Queen figure class.
 * @author nivanov.
 * @since
 * @version
 */
 public class Bishop extends Figure {
	public Bishop(Cell position) {
		super(position);
	}
	/**
	 * Way method checks correct way.
	 * @param dist ..
	 * @return passedCells ..
	 * @throws ImpossibleMoveException ..
	 */
	public Cell[] way(Cell dist) throws ImpossibleMoveException {
		int shiftH = dist.getHpos() - position.getHpos();
		int shiftV = dist.getVpos() - position.getVpos();
		Cell[] result = new Cell[abs(shiftH)];
		if (abs(shiftH) == abs(shiftV)) {
			for (int i = 0; i < shiftH; i++) {
				result[i] = new Cell((position.getHpos() + (i + 1) * shiftH / abs(shiftH)), (position.getVpos() + (i + 1) * shiftV / abs(shiftV)));
			}
		}	else {
				throw new ImpossibleMoveException("Destination cell is wrong!");
		}
		return result;
	}
}
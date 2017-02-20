package ru.nivanov.models;

import ru.nivanov.start.Cell;
import ru.nivanov.start.Figure;
import ru.nivanov.start.ImpossibleMoveException;

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
		int shiftH = dist.getHpos() - getPosition().getHpos();
		int shiftV = dist.getVpos() - getPosition().getVpos();
		Cell[] result = new Cell[abs(shiftH)];
		if (abs(shiftH) == abs(shiftV)) {
			for (int i = 0; i < abs(shiftH); i++) {
				result[i] = new Cell((getPosition().getHpos() + (i + 1) * shiftH / abs(shiftH)), (getPosition().getVpos() + (i + 1) * shiftV / abs(shiftV)));
			}
		}	else {
				throw new ImpossibleMoveException("Destination cell is wrong!");
		}
		return result;
	}
	/**
	 * Change figure position.
	 * @param dist is destination
	 * @return new figure
	 */
	public Figure clone(Cell dist) {
		return new Bishop(dist);
	}
}
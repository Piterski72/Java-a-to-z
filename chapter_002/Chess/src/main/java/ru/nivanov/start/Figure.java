package ru.nivanov.start;
/**
 * Figure abstract class.
 * @author nivanov.
 * @since
 * @version
 */
 public abstract class Figure {
	private Cell position;
	public Figure(Cell position) {
		this.position = position;
	}
	public Cell getPosition() {
		return this.position;
	}
	/**
	 * Change figure position.
	 * @param dist is destination
	 *
	 */
	public void clone(Cell dist) {
		this.position = dist;
	}
	/**
	 * Check if the way possible.
	 * @param dist is destination
	 * @return Cell massive is figure passed way
	 * @throws ImpossibleMoveException ..
	 */
	 public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;
}
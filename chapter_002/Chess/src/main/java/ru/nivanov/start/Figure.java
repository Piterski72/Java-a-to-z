package ru.nivanov.start;
/**
 * Figure abstract class.
 * @author nivanov.
 * @since
 * @version
 */
 public abstract class Figure {
	private final Cell position;
	public Figure(Cell position) {
		this.position = position;
	}
	/**
	 * Check if the way possible.
	 * @param dist is destination
	 * @return Cell massive is figure passed way
	 * @throws ImpossibleMoveException ..
	 */
	 public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;
}
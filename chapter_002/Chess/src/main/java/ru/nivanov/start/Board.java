package ru.nivanov.start;
/**
 * Board class.
 * @author nivanov.
 * @since
 * @version
 */
 public class Board {
	private final int half = 32;
	private Figure[] figures = new Figure[half];
	public Board(Figure[] figures) {
		this.figures = figures;
	}
	/**
	 * move method checks correct moving.
	 * @param source ..
	 * @param dist ..
	 * @return smthg
	 * @throws ImpossibleMoveException ..
	 * @throws OccupiedWayException ..
	 * @throws FigureNotFoundException ..
	 */
	public boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
		if (source.equals(figures.position)) {
			Cell[] figPass = figures.way(dist);
				for (int i = 0; i < figures.length; i++) {
					if (figPass[i].equals(figures[i].position)) {
						System.out.println("error");
						break;
					} else {
					 figures[i].position = dist;
					}
				}
		}
	}
}
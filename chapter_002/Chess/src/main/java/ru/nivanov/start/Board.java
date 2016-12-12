package ru.nivanov.start;
import ru.nivanov.models.*;
/**
 * Board class.
 * @author nivanov.
 * @since
 * @version
 */
 public class Board {
	private final int four = 4;
	private final int five = 5;
	private final int seven = 7;
	private final int half = 32;
	private Figure[] figures = new Figure[half];
	private int pos = 0;
	public void setFigures(Figure[] figures) {
		this.figures = figures;
	}
	/**
	 * fill board with figures.
	 * @param figure ..
	 */
	public void fillBoard(Figure figure) {
		figures[pos++] = figure;
	}
	/**
	 * get number of figures.
	 * @return pos ..
	 */
	public int getPos() {
		return this.pos;
	}
	/**
	 * get figures.
	 * @return result ..
	 */
	public Figure[] getFigures() {
		Figure[] result = new Figure[getPos()];
		for (int i = 0; i < result.length; i++) {
			result[i] = figures[i];
		}
		return result;
	}
	/**
	 * check for free way for figure.
	 * @param test cell massive
	 * @return check result
	 * @throws OccupiedWayException ..
	 */
	public boolean checkWay(Cell[] test) throws OccupiedWayException {
		boolean checkW = false;
		for (Cell cell : test) {
			for (Figure figs : this.getFigures()) {
				if (figs != null && !figs.getPosition().cellCompare(cell)) {
					checkW = true;
				} else {
					throw new OccupiedWayException("the way is not free!");
				}
			}
		}
		return checkW;
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
		for (Figure fig : this.getFigures()) {
				if (fig.getPosition().cellCompare(source)) {
				System.out.println("log1");
				Cell[] figPass = fig.way(dist);
					if (checkWay(figPass)) {
						fig = fig.clone(dist);
						System.out.println("log2");
						System.out.println(fig.clone(dist).getPosition().getVpos());
						break;
					}
				} else {
					System.out.println("log3");
				throw new FigureNotFoundException("no figure found!");
				}
		}
		return true;
	}
 }
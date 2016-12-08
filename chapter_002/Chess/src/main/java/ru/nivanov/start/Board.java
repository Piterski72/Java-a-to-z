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
	public int checkWay(Cell[] test) throws OccupiedWayException {
		int checkW = 1;
		for (int j = 0; j < test.length; j++) {
			for (int i = 0; i < getPos(); i++) {
				if (!figures[i].getPosition().cellCompare(test[j])) {
					checkW = 0;
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
		for (int i = 0; i < getPos(); i++) {
				if (figures[i].getPosition().cellCompare(source)) {
				Cell[] figPass = figures[i].way(dist);
					if (checkWay(figPass) == 0) {
						figures[i].setPosition(dist);
						break;
					}
				} else {
				throw new FigureNotFoundException("no figure found!");
				}
		}
		return true;
	}
 }
package ru.nivanov.start;

/**
 * Board class.
 * @author nivanov.
 * @since
 * @version
 */
class Board {
	private final int four = 4;
	private final int five = 5;
	private final int seven = 7;
	private final int half = 32;
	private Figure[] figures = new Figure[half];
	private int pos = 0;

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
	private int getPos() {
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

	public void setFigures(Figure[] figures) {
		this.figures = figures;
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
		boolean isFound = false;
		for (int i = 0; i < this.getFigures().length; i++) {
			if (figures[i].getPosition().cellCompare(source)) {
				isFound = true;
					Cell[] figPass = figures[i].way(dist);
					if (checkWay(figPass)) {
						figures[i] = figures[i].clone(dist);
						break;
					}
				}
		}
		if (!isFound) {
			throw new FigureNotFoundException("no figure found!");
		}
		return isFound;
	}
}
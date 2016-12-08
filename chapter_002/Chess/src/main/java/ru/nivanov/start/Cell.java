package ru.nivanov.start;
/**
 * Cell class.
 * @author nivanov.
 * @since
 * @version
 */
 public class Cell {
	private int hPos;
	private int vPos;
	   	public Cell(int hPos, int vPos) {
			this.hPos = hPos;
			this.vPos = vPos;
		}
	public int getHpos() {
		return this.hPos;
	}
	public int getVpos() {
		return this.vPos;
	}
	/**
	 * Check if cells are equals.
	 * @param cell ..
	 * @return result of comparison
	 */
	boolean cellCompare(Cell cell) {
		boolean itog = false;
		if (this.getHpos() == cell.getHpos() && this.getVpos() == cell.getVpos()) {
			itog = true;
		} else {
			itog = false;
		}
		return itog;
	}
}
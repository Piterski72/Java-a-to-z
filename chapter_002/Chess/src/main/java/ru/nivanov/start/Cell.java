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
}
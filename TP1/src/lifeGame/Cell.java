package lifeGame;

/**
 * MSSN 22/23 TP1
 *
 * Trabalho realizado por:
 * Roman Ishchuk 43498
 * Eduardo Marques 45977
 *
 * Docente Paulo Vieira
 */
import processing.core.PApplet;
public class Cell {
	private int row,col, state,w,h;
	private CellularAutomata ca;
	private Cell[] neighbors;

	/**
	 * @param ca CellularAutomata
	 * @param col cell's collumn
	 * @param row cell's row
	 * @param w cell's width
	 * @param h cell's heigth
	 *
	 * This is the constructor of the class Cell.
	 */
	public Cell(CellularAutomata ca,int col, int row,int w, int h){
		this.ca = ca;
		this.row = row;
		this.col = col;
		this.w = w;
		this.h = h;
		this.state = 0;
	}

	/**
	 * The function getState will return the state of the cell.
	 * @return state of the cell
	 */
	public int getState() {
		return this.state;
	}

	/**
	 * @param neigh Array of cells
	 * The function setNeighbors will receive an array of cells and
	 * store them. This will include the cell itself.
	 */
	public void setNeighbors(Cell[] neigh) {
		this.neighbors = neigh;
	}

	/**
	 * The function getNeighbors will return the array of neighbors.
	 * @return Cell array of neightbors
	 */
	public Cell[] getNeighbors() {
		return neighbors;
	}

	/**
	 * @param state Either dead of alive (0 or 1)
	 * The function setState will change the state of the cell.
	 */
	public void setState(int state){
		this.state = state;
	}

	/**
	 * @param p Papplet
	 * The function display will draw the cell as a rectangle calculating
	 * its position on the grid using the cell's column/row number multypling
	 * by the cell's width/height.
	 */
	public void display(PApplet p){
		p.rect(col*w,row*h, w,h);
	}
}
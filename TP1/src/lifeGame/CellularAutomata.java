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

import mssn.IProcessingApp;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.round;

public class CellularAutomata implements IProcessingApp {
	private final int cols, rows,w,h,WJ,HJ;
	private final Cell[][] cell;
	private final int[]colors;
	private boolean start = false;

	/**
	 * @param ncols number of columns
	 * @param nrows number of rows
	 * @param width width of the window
	 * @param heigth height of the window
	 *
	 * This is the constructor of the class CellularAutomata.
	 * The real width/heigth of the window is calculated by the number
	 * of columns/rows multiplied by a round number of the width/height
	 * of the Cell.
	 */
	public CellularAutomata(int ncols, int nrows, int width, int heigth){
		this.cols = ncols;
		this.rows = nrows;
		this.w = width/ncols;
		this.h = heigth/nrows;
		this.WJ = round((float) w) * ncols;
		this.HJ = round((float) h) * nrows;
		cell = new Cell[cols][rows];
		colors = new int[5];
	}

	/**
	 * The function createGrid will create a grid of cells, and also
	 * call the fucntion setMooreNeighbors.
	 */
	public void createGrid(){
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				cell[i][j] = new Cell(this,i,j,w,h);
			}
		}
		setMooreNeighbors();
	}

	/**
	 * @param p PApplet
	 * The function setStateColors will define the RGB elements of
	 * a colour, and store it.
	 */
	public void setStateColors(PApplet p) {
		colors[0]=p.color(0, 0, 0);
		colors[1]=p.color(10, 240, 10);
		colors[2]=p.color(220, 220, 40);
		colors[3]=p.color(95, 179, 209);
	}

	/**
	 * The function getStateColors will return an array of the colours
	 * @return int array colors
	 */
	public int[] getStateColors() {
		return colors;
	}

	/**
	 * The function setRandomStates will assign random states (0 or 1) to every
	 * cell of the grid.
	 */
	public void setRandomStates(){
		Random r = new Random();
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				cell[i][j].setState(r.nextInt(0, 2));
			}
		}
	}

	/**
	 * The function setStateDead will assign the state 0 to every cell of the grid.
	 */
	public void setStateDead(){
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				cell[i][j].setState(0);
			}
		}
	}

	/**
	 * The function display will call the createGrid function,
	 * therefore creating the grid.
	 */
	public void display(){
		createGrid();
	}

	/**
	 * @param p PApplet
	 * The function settings will set the window size.
	 */
	@Override
	public void settings(PApplet p) {
		p.size(WJ, HJ);
	}

	/**
	 * @param p PApplet
	 * The function setup will run once, and starts by calling the display function,
	 * then assing all random states to the cells, and set their colours.
	 *
	 * Note: uncomment the setStateDead for all the cells to appear with state 0 in the beggining, and
	 * comment the setRandomStates.
	 */
	@Override
	public void setup(PApplet p) {
		display();
		setRandomStates();
		//setStateDead();
		setStateColors(p);
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if(cell[i][j].getState() == 0) {
					p.fill(getStateColors()[0]);
					cell[i][j].display(p);
				}else{
					p.fill(getStateColors()[3]);
					cell[i][j].display(p);
				}
			}
		}
	}

	/**
	 * @param x coordinates
	 * @param y coordinates
	 * @param p PApplet
	 *
	 * The pixel12Cell function will "select" the cell in the x,y coordinates,
	 * by returing the cell.
	 * @return cell
	 */
	public Cell pixel12Cell(int x, int y, PApplet p) {
		int col = x/w;
		int row = y/h;
		if(col >= p.height) col = cols -1;
		if(row >= p.width) row = rows -1;
		return cell[col][row];
	}

	/**
	 * @param p PApplet
	 * The function mousePressed will always change the state of the "selected "cell
	 * to 1.
	 */
	@Override
	public void mousePressed(PApplet p) {
		Cell cell = pixel12Cell(p.mouseX, p.mouseY, p);
		p.fill(getStateColors()[1]);
		cell.display(p);
		cell.setState(1);
	}

	/**
	 * @param p PApplet
	 * The function keyPressed will pause/resume the process.
	 */
	@Override
	public void keyPressed(PApplet p){
		start= !start;
	}

	/**
	 * The function setMooreNeighbors will set the neighbors of the cells that
	 * are in the corners or the edges.
	 */
	private void setMooreNeighbors() {
		int radiusNeigh = 1;
		int NN = (int)Math.pow(2* radiusNeigh +1, 2);
		for(int i=0;i<cols;i++) {
			for(int j=0;j<rows;j++) {
				Cell[] neigh = new Cell[NN];
				int n = 0;
				for(int ii = -radiusNeigh; ii<= radiusNeigh; ii++) {
					int row = (i+ii+cols)%cols;
					for(int jj = -radiusNeigh; jj<= radiusNeigh; jj++) {
						int col = (j+jj+rows)%rows;
						neigh[n++] = cell[row][col];
					}
				}
				cell[i][j].setNeighbors(neigh);
			}
		}
	}

	/**
	 * @param p PApplet
	 * The function draw is a forever loop. It has 3 Lists, one for the cell that's changing,
	 * one for the state it will be changing to, and one for the colour it will change to.
	 *
	 * It starts by getting the number of neighbors with state 1 of cell in question, depending
	 * on the condition, the cell is stored in the changing array list in an index. The colour is
	 * stored in the nextC array list and the state is stored in the nextS array list, BOTH with
	 * the same index.
	 *
	 * When all the cells have been checked, it is time to change what we see on the screen for real.
	 * It goes through the changing arraylist, and fills the cell with the correspondind colours.
	 *
	 * The function draw will only fulfill the above if the flag start is true (activated by the keyPressed function).
	 */
	@Override
	public void draw(PApplet p) {
		if(start) {
			List<Cell> changing = new ArrayList<Cell>();
			List<Integer> nextC = new ArrayList<Integer>();
			List<Integer> nextS= new ArrayList<Integer>();

			for (int i = 0; i < cols; i++) {
				for (int j = 0; j < rows; j++) {
					int n = 0;
					for (int k = 0; k < cell[i][j].getNeighbors().length; k++) {
						if (cell[i][j].getNeighbors()[k] != cell[i][j] && cell[i][j].getNeighbors()[k].getState() == 1) {
							n++;
						}
					}
					if (cell[i][j].getState() == 0) {
						if(n == 3) {
							changing.add(cell[i][j]);
							nextC.add(1);
							nextS.add(1);
						}
					} else {
						if (n < 2 || n > 3) {
							changing.add(cell[i][j]);
							nextC.add(0);
							nextS.add(0);
						}
						if (n == 2){
							changing.add(cell[i][j]);
							nextC.add(2);
							nextS.add(1);
						}
						if (n == 3){
							changing.add(cell[i][j]);
							nextC.add(3);
							nextS.add(1);
						}
					}
				}
			}

			//mundanÃ§a de estados
			for (int i = 0; i < changing.size(); i++) {
				if (nextS.get(i) == 0) {
					p.fill(getStateColors()[nextC.get(i)]);
				} else {
					p.fill(getStateColors()[nextC.get(i)]);
				}
				changing.get(i).display(p);
				changing.get(i).setState(nextS.get(i));
			}
			//p.delay(50);
		}
	}
}
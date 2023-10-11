package dla;
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

import java.util.Random;
public class Walker{

	public int state, STOP = 0, WANDER = 1;
	private final int colour;
	private final int[] dot;

	/**
	 * @param x position
	 * @param y position
	 *
	 * This is the first constructor of the class Walker, creating a single
	 * dot.
	 */
	public Walker(int x, int y){
		colour = 0;
		dot = new int[]{x,y,colour,state};
	}

	/**
	 * @param p PApplet
	 *
	 * This is the second constructor of the clas Walker. It creates a walker.
	 */
	public Walker(PApplet p){
		Random random = new Random();
		int x = random.nextInt(901);
		x = PApplet.constrain(x,0,p.width-1);
		int y = random.nextInt(901);
		y = PApplet.constrain(y,0,p.width-1);
		state = WANDER;
		colour = 110;
		dot = new int[]{x, y, colour, state};
	}

	/**
	 * The method getX returns the X position of the walker.
	 * @return x position
	 */
	public int getX(){
		return this.dot[0];
	}
	/**
	 * The method getY returns the Y position of the walker.
	 * @return y position
	 */
	public int getY(){
		return this.dot[1];
	}

	/**
	 * The method getState returns the state of the walker.
	 * @return state (0 or 1)
	 */
	public int getState(){
		return this.dot[3];
	}

	/**
	 * @param state 0 or 1
	 * The method setState sets the state of the walker.
	 */
	public void setState(int state){
		dot[3] = state;
	}
	/**
	 * @param colour colour value
	 * The method setColour sets the colour of the walker.
	 */
	public void setColour(int colour){
		dot[2] = colour;
	}

	/**
	 * @param dot walker
	 * The function nextStep generates two random numbers, wich will
	 * be the movement of the walker (x and y).
	 */
	public void nextStep(int[] dot){
		Random random = new Random();
		dot[0] = dot[0] + random.nextInt(-15,16);
		dot[1] = dot[1] + random.nextInt(-15,16);
	}

	/**
	 * @param p PApplet
	 * The function display will draw the walker as an elipse, filling with
	 * the correct colour, and size.
	 */
	public void display(PApplet p) {
		if(dot[3] == WANDER){
			nextStep(dot);
		}
		p.fill(dot[2]);
		p.ellipse(dot[0], dot[1], 10, 10);
	}
}


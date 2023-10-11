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

import mssn.IProcessingApp;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

public class DLA implements IProcessingApp {

	private ArrayList<Walker> walkers;
	private final int WIDTH = 800, HEIGHT = 800, NUM_WALKERS = 2000;
	private final float stickiness = 0.5f;

	/**
	 * @param p PApplet
	 * The function settings will set the window size.
	 */
	@Override
	public void settings(PApplet p) {
		p.size(WIDTH,HEIGHT);
	}

	/**
	 * @param p PApplet
	 * The function setup will create all the walkers. It will run once.
	 * It has 3 possible starting positions:
	 * Center - 1 walker with state 0
	 * Bottom - the bottom edge of the screen is filled with walkers with state 0
	 * Square - all the edges of the screen are filled with walkers with state 0
	 */
	@Override
	public void setup(PApplet p) {
		walkers = new ArrayList<Walker>();
		walkers.add(center());
		//setupBottom();
		//setupSquare();
		for (int i = 0; i < NUM_WALKERS; i++) {
			walkers.add(new Walker(p));

		}
	}

	/**
	 * The function center creates a waklker in the center of the window.
	 * @return wakker
	 */
	public Walker center(){
		return new Walker(WIDTH/2,HEIGHT/2);
	}

	/**
	 * The function setupBottom creates walkers at the bottom edge of the window.
	 */
	public void setupBottom(){
		for (int i = 0; i < WIDTH; i = i + 20) {
			walkers.add(new Walker(i,HEIGHT-2));
		}
	}

	/**
	 * The function setupSquare creates walkers at all the edges of the window.
	 */
	public void setupSquare(){
		for (int i = 0; i < WIDTH; i = i+20) {
			walkers.add(new Walker(i,2));
			walkers.add(new Walker(i,HEIGHT-2));
		}
		for (int i = 0; i < HEIGHT; i = i+20) {
			walkers.add(new Walker(2,i));
			walkers.add(new Walker(WIDTH -2,i));
		}
	}

	/**
	 * @param p PApplet
	 * The function draw will run forever. It will set the background colour,
	 * call the stateChange function, and display all the walkers on the window.
	 */
	@Override
	public void draw(PApplet p) {
		p.background(150);
		stateChange(walkers);
		for (int i = 0; i < NUM_WALKERS; i++) {
			walkers.get(i).display(p);
		}
	}

	@Override
	public void keyPressed(PApplet p) {

	}

	@Override
	public void mousePressed(PApplet p) {

	}

	/**
	 * @param walkers ArrayList of all the walkers
	 * Checks if the walkers are within 10 px of the dead walkers.
	 * If they are, changes the state and the colour.
	 * Also a concept of stickiness is aplied.
	 * To adjust the stickiness, change the float value at the top of the page.
	 */
	public void stateChange(ArrayList<Walker> walkers){
		Random r = new Random();
		float aux;
		for (int i = 0; i < walkers.size(); i++) {
			for (int j = 0; j < walkers.size(); j++) {
				if(walkers.get(i).getState() == 0 && walkers.get(j).getState() == 1){
					int dx = walkers.get(i).getX() - walkers.get(j).getX();
					int dy = walkers.get(i).getY() - walkers.get(j).getY();
					if(dx >= -10 && dx <= 10 && dy >= -10 && dy <= 10){
						aux = r.nextFloat(1);
						if(aux<stickiness) {
							walkers.get(j).setState(0);
							walkers.get(j).setColour(0);
						}
					}
				}
			}
		}
	}
}

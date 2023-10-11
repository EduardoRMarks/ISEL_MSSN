/**
 * MSSN 22/23 TP3
 *
 * Trabalho realizado por:
 * Roman Ishchuk 43498
 * Eduardo Marques 45977
 *
 * Docente Paulo Vieira
 */
package exercicioB;

import mssn.IProcessingApp;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class exercicioB1 implements IProcessingApp {
	private ChaosGame chaosGame;
	ArrayList<PVector> initialPoints;
	@Override
	public void setup(PApplet p) {
		initialPoints = new ArrayList();
		initialPoints.add(new PVector(0, p.height));
		initialPoints.add(new PVector(p.width/2, 0));
		initialPoints.add(new PVector(p.width, p.height));

		chaosGame = new ChaosGame(initialPoints, p);
	}

	@Override
	public void mousePressed(PApplet p) {

	}

	@Override
	public void mouseReleased(PApplet p) {

	}

	@Override
	public void mouseDragged(PApplet p) {

	}

	@Override
	public void keyPressed(PApplet p) {

	}

	@Override
	public void draw(PApplet p, float dt) {
		chaosGame.draw(p);
	}
}

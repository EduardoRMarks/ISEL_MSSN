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

public class exercicioB2 implements IProcessingApp {
	private ChaosGame chaosGame;
	private boolean start = false;
	ArrayList<PVector> initialPoints;
	@Override
	public void setup(PApplet p) {
		p.background(0);
		initialPoints = new ArrayList();
	}

	@Override
	public void mousePressed(PApplet p) {
		initialPoints.add(new PVector(p.mouseX, p.mouseY));
	}

	@Override
	public void mouseReleased(PApplet p) {

	}

	@Override
	public void mouseDragged(PApplet p) {

	}

	@Override
	public void keyPressed(PApplet p) {
		if((p.key == 'S' || p.key == 's') && initialPoints.size() > 3) {
			chaosGame = new ChaosGame(initialPoints, p);
			start = true;
		}
		else if((p.key == 'R' || p.key == 'r')) {
			p.background(0);
			initialPoints.clear();
			start = false;
		}
	}

	@Override
	public void draw(PApplet p, float dt) {
		p.textSize(20);
		p.fill(255);
		p.text("Use the mouse to chose at least 3 starting points to the Chaos Game", 10, 20);
		p.text("Press the 'S' key to star the Chaos Game", 10, 35);
		p.text("Press the 'R' key to restart the Chaos Game", 10, 50);
		if (start) {
			chaosGame.draw(p);
		}
	}
}

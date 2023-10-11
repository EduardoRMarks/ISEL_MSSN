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

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class ChaosGame {
	PVector currentPoint;
	ArrayList<PVector> initialPoints;

	public ChaosGame(ArrayList<PVector> initialPoints, PApplet p) {
		p.background(0);
		this.initialPoints = initialPoints;
		initShape(p);
		drawInitialRandomPoint(p);
	}
	private void initShape(PApplet p) {
		for (PVector p1 : initialPoints){
			p.point(p1.x, p1.y);
		}
	}

	private void drawInitialRandomPoint(PApplet p) {
		PVector randomInitialPoint = new PVector(p.random(p.width), p.random(p.height));
		p.point(randomInitialPoint.x, randomInitialPoint.y);
		currentPoint = randomInitialPoint;
	}


	public void draw(PApplet p) {
		for (int i = 0; i < 1000; i++) {
			//roll dice
			int rand = (int) p.random(initialPoints.size());
			//lerp
			float x = PApplet.lerp(currentPoint.x, initialPoints.get(rand).x, 0.5f);
			float y = PApplet.lerp(currentPoint.y, initialPoints.get(rand).y, 0.5f);

			if (rand == 0){
				p.stroke(255,0,0);
			}
			else if (rand == 1){
				p.stroke(0,255,0);
			}
			else if (rand == 2){
				p.stroke(0,0,255);
			}
			else if(rand % 2 == 0){
				p.stroke(255/(rand/5+1),0,200);
			}
			else if (rand % 3 == 0){
				p.stroke(200,255/(rand/5+1),0);
			}
			else{
				p.stroke(0,200,255/(rand/5+1));
			}
			p.point(x, y);
			currentPoint = new PVector(x, y);
		}
	}
}

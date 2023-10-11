
package exercicioE;

import boids.Boid;
import boids.Flock;
import mssn.IProcessingApp;
import mssn.SubPlot;
import processing.core.PApplet;

public class DeBugApp implements IProcessingApp{
	private Flock flock;
	private Boid boid;
	private float[] sacWeights = {1f, 1f, 1f, 0f};
	private double[] window = {-10, 10, -10, 10};
	private float[] view1 = {0f, 0f, 1f, 1f};
	private SubPlot plt;
	
	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, view1, p.width, p.height);
		flock = new Flock(100, .1f, .3f, p.color(0), sacWeights, p, plt);

		boid = flock.getBoid(50);
	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(255);
		p.text("Blue Boids are inside vision.", 10, 20);
		p.text("Green Boids are inside Safe Distance.", 10, 40);
		p.textSize(20);
		p.fill(0);
		
		for(Boid b : flock.getBoids()) {
			if(boid.getEye().farSight(b.getPos())) {
				if(boid.getEye().nearSight(b.getPos())){
					b.setShape(p, plt, .5f, p.color(0, 255, 0));
				}
				else{
					b.setShape(p, plt, .4f, p.color(0, 0, 255));
				}
			}
			else {
				b.setShape(p, plt, .3f, p.color(0));
			}
		}
		flock.applyBehavior(dt);
		boid.getEye().display(p, plt);
		boid.setShape(p, plt, .5f, p.color(255,0,0));
		flock.display(p, plt);
	}

	@Override
	public void mousePressed(PApplet p) {
	}

	@Override
	public void keyPressed(PApplet p) {
	}

}

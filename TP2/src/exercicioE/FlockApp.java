package exercicioE;

import boids.Flock;
import mssn.IProcessingApp;
import mssn.SubPlot;
import processing.core.PApplet;

public class FlockApp implements IProcessingApp {
	private Flock flock;
	//pesos dos comportamentos
	private float[] sacWeights = {0.33f, 0.33f, 0.33f};
	private double[] window = {-10, 10, -10, 10};
	private float[] viewport = {0, 0, 1, 1};
	private SubPlot plt;


	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		flock = new Flock(150, 0.1f, 0.3f, p.color(220, 0, 0), sacWeights,p, plt);
	}

	@Override
	public void mousePressed(PApplet p) {

	}

	@Override
	public void keyPressed(PApplet p) {

	}

	@Override
	public void draw(PApplet p, float dt) {

		//float[] bb = plt.getBoundingBox();
		//p.fill(150,150,255,16);
		//p.rect(bb[0], bb[1], bb[2], bb[3]);
		p.background(0,0,150);
		flock.applyBehavior(dt);
		flock.display(p,plt);
	}
}

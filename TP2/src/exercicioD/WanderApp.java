package exercicioD;

import boids.*;
import mssn.IProcessingApp;
import mssn.SubPlot;
import physics.Body;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class WanderApp implements IProcessingApp {
	private Boid b;
	private double[] window = {-10,10,-10,10};
	private float[] viewport = {0,0,1,1};
	private SubPlot plt;
	private Body target;
	private List<Body> allTrackingBodies;
	private int index = 2;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		b = new Boid(new PVector(0,0), 1, 0.3f, p.color(0), plt,p);
		b.addBehavior(new Seek(1f));
		b.addBehavior(new Flee(1f));
		b.addBehavior(new Wander(1f));
		target = new Body(new PVector(0,0), new PVector(0,0), 1f, 0.2f, p.color(255,255,0));
		allTrackingBodies = new ArrayList<Body>();
		allTrackingBodies.add(target);
		Eye eye = new Eye(b, allTrackingBodies);
		b.setEye(eye);
	}

	@Override
	public void mousePressed(PApplet p) {
		//coordenadas do mundo
		double[] wc = plt.getWorldCoord(p.mouseX, p.mouseY);
		target.setPos(new PVector((float) wc[0],(float) wc[1]));
	}

	@Override
	public void keyPressed(PApplet p) {

	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(255);
		b.applyBehavior(dt, index);
		b.display(p,plt);

	}
}

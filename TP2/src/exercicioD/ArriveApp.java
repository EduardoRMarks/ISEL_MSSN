package exercicioD;

import boids.*;
import mssn.*;
import physics.Body;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class ArriveApp implements IProcessingApp {
	private Boid boid;
	private Body target;
	private List<Body> allTrackingBodies;


	private float[] sacWeights = {1f, 1f, 1f, 0f};
	private double[] window = {-10, 10, -10, 10};
	private float[] view1 = {0f, 0f, 1f, 1f};
	private SubPlot plt;
	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, view1, p.width, p.height);
		boid = new Boid(new PVector(0, 0), 0.1f, 0.3f, p.color(255,0,0), plt, p);
		boid.addBehavior(new Arrive(1f));

		target = new Body(new PVector(5,5), new PVector(0,0), 1f, 0.1f, p.color(0, 0, 255));
		allTrackingBodies = new ArrayList<Body>();
		allTrackingBodies.add(target);
		boid.setEye(new Eye(boid, allTrackingBodies));
	}

	@Override
	public void mousePressed(PApplet p) {
		double[] pos = plt.getWorldCoord(p.mouseX, p.mouseY);
		target.setPos(new PVector((float) pos[0], (float) pos[1]));
	}

	@Override
	public void keyPressed(PApplet p) {

	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(255);
		boid.display(p,plt);
		boid.applyBehaviors(dt);
		target.display(p, plt);
	}
}

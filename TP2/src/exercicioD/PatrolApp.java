package exercicioD;

import boids.Boid;
import mssn.IProcessingApp;
import mssn.SubPlot;
import physics.Body;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class PatrolApp implements IProcessingApp {

	private Boid b;
	private double [] window = {-10,10,-10,10};
	private float [] viewport = {0,0,1,1};
	private SubPlot plt;
	private List<Body> allTrackingBodies;
	private Patrol patrol;


	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		b = new Boid( new PVector(), 1f, 0.5f, p.color(200,150,0), plt, p);
		patrol = new Patrol(1f);
		allTrackingBodies = new ArrayList<Body>();
		b.addBehavior(patrol);
	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(0);
		
		b.applyBehaviors(dt);
		
		b.display(p, plt);

		if(allTrackingBodies.size()!=0) {
			for (Body body : allTrackingBodies) {
				body.display(p, plt);
			}
		}
	}

	@Override
	public void mousePressed(PApplet p) {
		double[] coordsPatrol = plt.getWorldCoord(p.mouseX, p.mouseY);
		patrol.addToPath( (float) coordsPatrol[0],(float) coordsPatrol[1]);
		allTrackingBodies.add(new Body(new PVector((float) coordsPatrol[0],(float) coordsPatrol[1]), new PVector(0, 0), 1f, 0.1f, p.color(255,0,0)));
	}

	@Override
	public void keyPressed(PApplet p) {
		
	}


		
}

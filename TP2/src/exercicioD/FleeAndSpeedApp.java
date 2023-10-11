package exercicioD;

import boids.*;
import mssn.IProcessingApp;
import mssn.SubPlot;
import physics.Body;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class FleeAndSpeedApp implements IProcessingApp{
	private Boid wander,runner;
	private double[] window = {-10, 10, -10, 10};
	private float[] viewport = {0, 0, 1, 1};
	private SubPlot plt;
	private Body target;
	private int index = 2;



	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);


		wander = new Boid (new PVector( p.random( (float)window[0],(float)window[1]), p.random((float)window[2],(float)window[3])),
				0.1f,0.5f,p.color(255,0,0),plt,p);
		wander.addBehavior(new Wander(1f));

		runner = new Boid (new PVector( p.random( (float)window[0],(float)window[1]),
				p.random((float)window[2],(float)window[3])),
				0.5f,0.2f,p.color(0,255,255),plt,p);

		runner.addBehavior(new Wander(0.5f));
		runner.addBehavior(new Flee(0.5f));

		List<Body> allTrackingBodies = new ArrayList<Body>();
		allTrackingBodies.add(wander);;
		runner.setEye(new Eye(runner, allTrackingBodies));
	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(0);
		p.text("Press 'a' do accelerate and 'd' to decrease the speed", 10, 20);
		p.textSize(20);
		p.fill(255);

		wander.applyBehaviors(dt);
		runner.applyBehaviors(dt);
		wander.display(p, plt);
		runner.display(p, plt);
	}

	@Override
	public void mousePressed(PApplet p) {

	}

	@Override
	public void keyPressed(PApplet p) {
		if(p.key == 'd' ) {
			runner.dna.maxSpeed = (runner.dna.maxSpeed - 1);
		}
		if(p.key == 'a' ) {
			runner.dna.maxSpeed = (runner.dna.maxSpeed + 1);
		}
	}

}



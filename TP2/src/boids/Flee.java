package boids;

import processing.core.PVector;

public class Flee extends Behavior {

	public Flee(float weight) {
		super(weight);
	}

	@Override
	public PVector getDesiredVelocity(Boid me) {
		PVector vd = PVector.sub(me.eye.target.getPos(), me.pos);
		return vd.mult(-1);
	}
}

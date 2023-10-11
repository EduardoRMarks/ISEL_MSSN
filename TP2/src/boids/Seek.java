package boids;

import processing.core.PVector;

public class Seek extends Behavior{

	public Seek(float weight) {
		super(weight);
	}

	@Override
	public PVector getDesiredVelocity(Boid me) {
		return PVector.sub(me.eye.target.getPos(), me.pos);
	}
}

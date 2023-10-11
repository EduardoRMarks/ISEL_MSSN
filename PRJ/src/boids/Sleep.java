package boids;

import processing.core.PVector;

public class Sleep extends Behavior{
    public Sleep(float weight) {
        super(weight);
    }

    @Override
    public PVector getDesiredVelocity(Boid me) {

        return PVector.mult(me.vel,-1);
    }
}
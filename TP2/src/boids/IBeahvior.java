package boids;

import processing.core.PVector;

public interface IBeahvior {
    public PVector getDesiredVelocity(Boid me);
    public void setWeight(float weight);
    public float getWeight();
}

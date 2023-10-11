package ecosystem;

import boids.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import tools.SubPlot;

public abstract class Animal extends Boid implements IAnimal {
	protected float energy;
	private Animal target;

	protected PImage img;

	protected Animal(PVector pos, float vel, float mForce, float mass, float radius, int color, PApplet p, SubPlot plt){
		super(pos, vel, mForce, mass, radius, color, plt, p);

	}

	protected Animal(Animal a, boolean mutate, PApplet p, SubPlot plt){
		super(a.pos, a.getMass(), a.radius, a.color, plt, p);
		for (Behavior b : a.behaviors){
			this.addBehavior(b);
		}
		if (a.eye != null){
			eye = new Eye(this, a.eye);
		}
		dna = new DNA(a.dna, mutate);
	}


	@Override
	public void energy_consumption(float dt, Terrain terrain) {
		energy -= dt;
		energy -= getMass()*Math.pow(vel.mag(), 2)*0.3*dt;
		Patch patch = (Patch) terrain.world2Cell(pos.x, pos.y);
		if (patch.getState() == WorldConstants.PatchType.OBSTACLE.ordinal()){
			if (this instanceof Predator){
				energy-= 10*dt;
			}
			else if(this instanceof Monster){
			}
			else {
				energy -= 50 * dt;
			}
		}
	}

	@Override
	public boolean die() {
		return (energy < 0);
	}

	public void setTarget(Animal target) {
		this.target = target;
	}

	public Animal getTarget(){
		return target;
	}

	public float getEnergy(){
		return energy;
	}

	public void setImg(PImage img) {
		this.img = img;
	}
}
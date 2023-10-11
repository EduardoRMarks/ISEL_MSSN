package exercicioD;

import boids.Behavior;
import boids.Boid;
import processing.core.PVector;

import java.util.ArrayList;

public class Patrol extends Behavior {
	protected ArrayList<PVector> path;
	private int index;
	

	public Patrol(float weight) {
		super(weight);
		path = new ArrayList <PVector>();
		index = 0;
		
		}
	
	@Override
	public PVector getDesiredVelocity(Boid me) {
		PVector target = nextTarget(me);
		PVector vd = PVector.sub(target, me.getPos());
		return 	vd;
			
		}
	
	private PVector nextTarget(Boid me) {
		if(path.size() > 0) {
			float dist = PVector.sub(me.getPos(),path.get(index)).mag();
			if(dist < 1) {
				index++;
				index = index % path.size();
			}
		 	return path.get(index);
		}
		return me.getPos();
	}
	
	public void addToPath(float posicaoX, float posicaoY) {
		path.add(new PVector(posicaoX, posicaoY));
	}
		
}
	
	


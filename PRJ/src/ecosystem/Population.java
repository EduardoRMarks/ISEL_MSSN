package ecosystem;

import boids.*;
import physics.Body;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import tools.SubPlot;

import java.util.ArrayList;
import java.util.List;

public class Population {
	protected List<Animal> allAnimals;
	protected Animal target;
	private double[] window;
	private boolean mutate = true;
	private Animal targetAux;
	protected List<Body> obstacles = new ArrayList<>();

	float aux;
	private boolean parar = false;
	protected Animal prey, predator, monster;

	protected PImage imgPrey, imgPredator, imgMonster;
	protected List<PImage> images = new ArrayList<>();

	public Population(PApplet parent, SubPlot plt, Terrain terrain){
		imgPrey = parent.loadImage("prey.png");
		images.add(imgPrey);
		imgPredator = parent.loadImage("predator.png");
		images.add(imgPredator);
		imgMonster = parent.loadImage("monster.png");
		images.add(imgMonster);

		window = plt.getWindow();
		allAnimals = new ArrayList<Animal>();

		obstacles = terrain.getObstacles();

		for (int i = 0; i < WorldConstants.INI_PREY_POPULATION; i++) {
			PVector pos = new PVector(parent.random((float) window[0], (float) window[1]),
					parent.random((float) window[2], (float) window[3]));
			int color = parent.color(WorldConstants.PREY_COLOR[0],
									 WorldConstants.PREY_COLOR[1],
									 WorldConstants.PREY_COLOR[2]);
			prey = new Prey(pos, WorldConstants.PREY_VELOCITY, WorldConstants.PREY_FORCE, WorldConstants.PREY_MASS, WorldConstants.PREY_SIZE, color, parent, plt, imgPrey);
			prey.addBehavior(new Wander(4));
			prey.addBehavior(new AvoidObstacle(0));
			prey.addBehavior(new Flee(0.1f));
			Eye eye = new Eye(prey, obstacles);
			prey.setEye(eye);
			allAnimals.add(prey);
		}

		for (int i = 0; i < WorldConstants.INI_PREDATOR_POPULATION; i++) {
			PVector pos = new PVector(parent.random((float) window[0], (float) window[1]),
					parent.random((float) window[2], (float) window[3]));
			int color = parent.color(WorldConstants.PREDATOR_COLOR[0],
					WorldConstants.PREDATOR_COLOR[1],
					WorldConstants.PREDATOR_COLOR[2]);
			predator = new Predator(pos, WorldConstants.PREDATOR_VELOCITY, WorldConstants.PREDATOR_FORCE, WorldConstants.PREDATOR_MASS, WorldConstants.PREDATOR_SIZE, color, parent, plt, imgPredator);
			predator.addBehavior(new Wander(1));
			predator.addBehavior(new Pursuit(6));

			List<Body> allTrackingBodies = new ArrayList<Body>();
			for (Animal a : allAnimals){
				if (a instanceof Prey){
					allTrackingBodies.add(a);
				}
			}
			Eye eye = new Eye(predator, allTrackingBodies);
			predator.setEye(eye);
			allAnimals.add(predator);
			target = (Animal) predator.getEye().nextTarget();
			predator.setTarget(target);
		}

		for (int i = 0; i < WorldConstants.INI_MONSTER_POPULATION; i++) {
			PVector pos = new PVector(parent.random((float) window[0], (float) window[1]),
					parent.random((float) window[2], (float) window[3]));

			int color = parent.color(WorldConstants.MONSTER_COLOR[0],
					WorldConstants.MONSTER_COLOR[1],
					WorldConstants.MONSTER_COLOR[2]);
			monster = new Monster(pos, WorldConstants.MONSTER_VELOCITY, WorldConstants.MONSTER_FORCE, WorldConstants.MONSTER_MASS, WorldConstants.MONSTER_SIZE, color, parent, plt, imgMonster);
			monster.addBehavior(new Sleep(1));
			monster.addBehavior(new Pursuit(1));

			List<Body> allTrackingBodies = new ArrayList<Body>(allAnimals);
			Eye eye = new Eye(monster, allTrackingBodies);
			monster.setEye(eye);
			target = (Animal) monster.getEye().nextTarget();
			monster.setTarget(target);
			allAnimals.add(monster);
		}

		for(int i = WorldConstants.INI_PREY_POPULATION; i < allAnimals.size(); i++){
			Body b = new Body(allAnimals.get(i).getPos());
			obstacles.add(b);
		}
		Eye eye = new Eye(prey, obstacles);
		prey.setEye(eye);

	}

	public void newTarget(Animal child) {
		List<Body> allTrackingBodies = new ArrayList<Body>();
		for (Animal a : allAnimals){
			if (a instanceof Prey){
				allTrackingBodies.add(a);
			}
		}
		Eye eye = new Eye(child, allTrackingBodies);
		child.setEye(eye);
		target = (Animal) child.getEye().nextTarget();
		child.setTarget(target);
	}

	private void nextTargetPredator(Animal predator) {
		if (predator.getTarget().isDead()){
			//
			targetAux = predator.getTarget();
			//
			List<Body> allTrackingBodies = new ArrayList<Body>();
			for (Animal a : allAnimals){
				if (a instanceof Prey && a != predator.getTarget() && !a.isDead()){
					allTrackingBodies.add(a);
				}
			}
			Eye eye = new Eye(predator, allTrackingBodies);
			predator.setEye(eye);
			target = (Animal) predator.getEye().nextTarget();
			predator.setTarget(target);
		}else if(PVector.sub(predator.getPos(),predator.getTarget().getPos()).mag() > 15f){
			target = (Animal) predator.getEye().nextTarget();
			predator.setTarget(target);
		}
	}

	private void nextTargetMonster(Animal monster) {
		if (monster.getTarget().isDead()){
			//
			targetAux = monster.getTarget();
			//
			List<Body> allTrackingBodies = new ArrayList<Body>();
			for (Animal a : allAnimals){
				if ((a instanceof Prey || a instanceof Predator) && a != monster.getTarget() && !a.isDead()){
					allTrackingBodies.add(a);
				}
			}
			Eye eye = new Eye(monster, allTrackingBodies);
			monster.setEye(eye);
			target = (Animal) monster.getEye().nextTarget();
			monster.setTarget(target);
		}else if(PVector.sub(monster.getPos(),monster.getTarget().getPos()).mag() > 15f){
			target = (Animal) monster.getEye().nextTarget();
			monster.setTarget(target);
		}
	}

	public void updateImage(PApplet p){
		for (Animal a : allAnimals){
			if (a instanceof Prey){
				a.setImg(imgPrey);
			}
		}
	}

	public void update(float dt, Terrain terrain){

		move(terrain, dt);
		for (Animal a : allAnimals){
			if (a instanceof Predator) {
				if (getNumPrey() > 0) {
					nextTargetPredator(a);
				} else {
					//se não houver presas, vagueia
					a.applyBehavior(dt, 0);
				}
			}
			if (a instanceof Monster){
				//System.out.println("Energia do monstro: "+ a.getEnergy());
				if (getNumPrey() > 0 || getNumPred() > 0) {
					if (a.getEnergy() < 20f){
						//System.out.println("hunt");
						nextTargetMonster(a);
						a.applyBehavior(dt, 1);
						aux = a.getEnergy();
						parar = false;
					} else if (a.getEnergy() > 50f && !parar){
						//System.out.println("Barriga cheia");
						a.applyBehavior(dt, 0);
						aux = a.getEnergy();
						parar = true;
					} else if (a.getEnergy() > 20f && aux > 50f) {
						//System.out.println("Ainda estou a dormir");
					} else if (parar) {
					} else{
						//System.out.println("voltei");
						nextTargetMonster(a);
						a.applyBehavior(dt, 1);
						aux = a.getEnergy();
					}
				}
				else {
					System.out.println("Não há mais comida");
					a.applyBehavior(dt, 0);
				}
			}
		}
		eat(terrain);
		energy_consumtion(dt, terrain);
		reproduce(mutate);
		die();
	}

	private void move(Terrain terrain, float dt) {
		for (Animal a : allAnimals){
			if(a instanceof Prey || a instanceof Predator) a.applyBehaviors(dt);
		}
	}

	private void eat(Terrain terrain) {
		for (Animal a : allAnimals){
			if (a instanceof Prey){
				a.eat(terrain, null);
			}
			else if (a instanceof Predator) {
				a.eat(terrain, a.getTarget());
			}
			else if (a instanceof Monster){
				a.eat(terrain, a.getTarget());
			}
		}
	}

	private void energy_consumtion(float dt, Terrain terrain) {
		for (Animal a : allAnimals){
			a.energy_consumption(dt, terrain);
		}
	}

	private void reproduce(boolean mutate) {
		for (int i = allAnimals.size()-1; i >= 0 ; i--) {
			Animal a = allAnimals.get(i);
			if (!(a instanceof Monster)) {
				Animal child = a.reproduce(mutate);
				if (child != null) {
					if (child instanceof Predator) {
						newTarget(child);
					}
					allAnimals.add(child);
				}
			}
		}
	}

	private void die() {
		for (int i = allAnimals.size()-1; i >= 0 ; i--) {
			Animal a = allAnimals.get(i);
			if(a.isDead()){
				allAnimals.remove(a);
			}
			else if(a.die()){
				allAnimals.remove(a);
			}
		}
	}

	public void display(PApplet p, SubPlot plt){
		for (Animal a : allAnimals)
			a.display(p, plt);
	}

	public List<PImage> getImages() {
		return images;
	}

	public int getNumAnimals(){
		return allAnimals.size();
	}
	public int getNumPrey(){
		List<Animal> prey = new ArrayList<>();
		for (Animal a : allAnimals){
			if (a instanceof Prey){
				prey.add(a);
			}
		}
		return prey.size();
	}

	public int getNumPred(){
		List<Animal> predator = new ArrayList<>();
		for (Animal a : allAnimals){
			if (a instanceof Predator){
				predator.add(a);
			}
		}
		return predator.size();
	}

	public float getMeanMaxSpeed() {
		float sum = 0;
		for (Animal a : allAnimals) {
			sum += a.getDna().maxSpeed;
		}
		return sum/allAnimals.size();
	}

	public float getStdMaxSpeed() {
		float mean = getMeanMaxSpeed();
		float sum = 0;
		for (Animal a : allAnimals) {
			sum += Math.pow(a.getDna().maxSpeed - 2, 2);
		}
		return (float) Math.sqrt(sum/allAnimals.size());
	}

	public float[] getMeanWeights(){
		float[] sums = new float[2];
		for (Animal a : allAnimals){
			sums[0] += a.getBehaviors().get(0).getWeight();
			sums[1] += a.getBehaviors().get(1).getWeight();
		}
		sums[0] /= allAnimals.size();
		sums[1] /= allAnimals.size();
		return sums;
	}

	public Animal getMonster(){
		for(Animal a: allAnimals){
			if (a instanceof Monster) return a;
		}
		return null;
	}
}

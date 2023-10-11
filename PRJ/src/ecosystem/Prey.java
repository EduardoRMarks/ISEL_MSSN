package ecosystem;

import physics.Body;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import tools.SubPlot;

import java.util.List;

public class Prey extends Animal {

	private PApplet parent;
	private SubPlot plt;
	protected PImage img;

	public Prey(PVector pos, float preyVel, float preyForce, float preyMass, float preySize, int color, PApplet parent, SubPlot plt, PImage img) {
		super(pos, preyVel, preyForce, preyMass, preySize, color, parent, plt);
		this.parent = parent;
		this.plt = plt;
		energy = WorldConstants.INI_PREY_ENERGY;
		this.img = img;
	}

	public Prey(Prey prey, boolean mutate, PApplet parent, SubPlot plt, PImage img) {
		super(prey, mutate, parent, plt);
		this.parent = parent;
		this.plt = plt;
		energy = WorldConstants.INI_PREY_ENERGY;
		this.img = img;
	}

	@Override
	public void eat(Terrain terrain, Animal target){
		Patch patch = (Patch) terrain.world2Cell(this.pos.x, this.pos.y);
		if (patch.getState() == WorldConstants.PatchType.FOOD.ordinal()){
			energy += WorldConstants.ENERGY_FROM_PLANT;
			patch.setFertile();
		}
	}

	@Override
	public Animal reproduce(boolean mutate){
		Animal child = null;
		if (energy > WorldConstants.PREY_ENERGY_TO_REPRODUCE){
			energy -= WorldConstants.INI_PREY_ENERGY;
			child = new Prey(this, mutate, parent, plt, img);
			if (mutate) child.mutateBehaviors();
		}
		return child;
	}

	@Override
	public void display(PApplet p, SubPlot plt) {
		float[] pp = plt.getPixelCoord(pos.x, pos.y);
		//a,b coordenadas da imagem x,y  ;   c,d largura e altura de cada imagem
		p.image(img, pp[0]-10f, pp[1]-10f, 20f, 20f);
	}

	@Override
	public void setImg(PImage img) {
		this.img = img;
	}
}

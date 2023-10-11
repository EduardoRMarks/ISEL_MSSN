package ecosystem;

import boids.*;
import physics.Body;
import physics.PSControl;
import physics.ParticleSystem;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;
import tools.IProcessingApp;
import tools.SubPlot;
import tools.TimeGraph;

import java.util.ArrayList;
import java.util.List;

public class TestEcosystemAPP implements IProcessingApp {

	private boolean start = false;
	private boolean debug = true;
	private float widthSize, heightSize;


	private float timeDuration = 60;
	private float refPopulation = 300;
	private float refPrey = 288;
	private float refPred = 40;
	private float refMeanMaxSpeed = 2f;
	private float refStdMaxSpeed = .2f;
	private float[] viewportNormal = {0f, 0f, 1f, 1f};
	private float[] viewportDebug = {0f, 0f, .7f, 1f};
	private double[] winInBetween = {0, 1, 0, 1};
	private double[] winGraph1 = {0, timeDuration, 0, 2*refPopulation};
	private double[] winGraph2 = {0, timeDuration, 0, 2*refPred};
	private double[] winGraph3 = {0, timeDuration, 0, 2*refPrey};

	private float[] viewInBetween = {.7f, 0f, .03f, 1f};
	private float[] viewGraph1 = {.74f, .04f, .23f, .28f};
	private float[] viewGraph2 = {.74f, .37f, .23f, .28f};
	private float[] viewGraph3 = {.74f, .70f, .23f, .28f};

	private SubPlot plt, pltInBetween, pltGraph1, pltGraph2, pltGraph3;
	private TimeGraph tg1, tg2, tg3;

	private Terrain terrain;
	private List<ParticleSystem> pss;
	private float[] velParams = {PApplet.radians(360), PApplet.radians(360), 1, 1};
	private float[] velParams2 = {PApplet.radians(90), PApplet.radians(90), 1, 1};
	private float[] lifetimeParams = {0.3f, 0.5f};
	private float[] radiusParams = {0.05f, 0.1f};
	private float[] radiusParams2 = {0.05f, 0.2f};
	private float flow = 20;
	private float flow2 = 100;
	private Population population;
	private float timer, updateGraphTime;
	private float intervalUpdate = 1;
	private PFont title, textInit, text;
	PSControl psc,psc2;
	int pssMaxSize;


	protected PImage imgGrass;
	@Override
	public void setup(PApplet p) {

		title = p.createFont("Arial Black", 40);
		textInit = p.createFont("Arial", 25);
		text = p.createFont("Arial", 15);

		imgGrass = p.loadImage("grass.png");

		if (debug) {
			widthSize = (float) ((p.width * 0.7)/WorldConstants.NCOLS);
			heightSize = (float) ((p.height * 0.7)/WorldConstants.NROWS);

			plt = new SubPlot(WorldConstants.WINDOW, viewportDebug, p.width, p.height);
			pltInBetween = new SubPlot(winInBetween, viewInBetween, p.width, p.height);
			pltGraph1 = new SubPlot(winGraph1, viewGraph1, p.width, p.height);
			pltGraph2 = new SubPlot(winGraph2, viewGraph2, p.width, p.height);
			pltGraph3 = new SubPlot(winGraph3, viewGraph3, p.width, p.height);

			/*tg1 = new TimeGraph(p, pltGraph1, p.color(255,0,0), refPopulation);
			tg2 = new TimeGraph(p, pltGraph2, p.color(255,0,0), refMeanMaxSpeed);
			tg3 = new TimeGraph(p, pltGraph3, p.color(255,0,0), refStdMaxSpeed);*/

			tg1 = new TimeGraph(p, pltGraph1, p.color(255, 0, 0), refPopulation);
			tg2 = new TimeGraph(p, pltGraph2, p.color(255, 0, 0), refPred);
			tg3 = new TimeGraph(p, pltGraph3, p.color(255, 0, 0), refPrey);
		}
		else {
			widthSize = (float) ((p.width)/WorldConstants.NCOLS);
			heightSize = (float) ((p.height)/WorldConstants.NROWS);
			plt = new SubPlot(WorldConstants.WINDOW, viewportNormal, p.width, p.height);
		}

		terrain = new Terrain(p, plt);
		terrain.setStateColors(getColors(p));
		terrain.initRandomCustom(WorldConstants.PATCH_TYPE_PROB);
		for (int i = 0; i < 5; i++) {
			terrain.majorityRule();
		}
		population = new Population(p, plt, terrain);
		timer = 0;
		updateGraphTime = timer + intervalUpdate;


		pss = new ArrayList<>();
		int fireColor = p.color(255, 65, 0);
		psc = new PSControl(velParams, lifetimeParams, radiusParams, flow, fireColor);
		for (int i = 0; i < terrain.getFire().size(); i++) {
			pss.add(new ParticleSystem(terrain.getFire().get(i).getPos(), new PVector(), 0.1f, 0.1f, psc));
		}
		pssMaxSize=pss.size();

		int sleepColor = p.color(47,180,206);
		psc2 = new PSControl(velParams2, lifetimeParams, radiusParams2, flow2, sleepColor);
	}


	@Override
	public void mousePressed(PApplet p) {
		if(!start) {
			p.background(0);
			start = true;
			if (debug) {
				p.textFont(text);
				p.text("Número total de Presas", 947, 16);
				p.text("Número total de Predadores", 942, 280);
				p.text("Número total de Animais", 947, 544);
			}
		}
		if (debug) {
			winGraph1[0] = timer;
			winGraph1[1] = timer + timeDuration;
			pltGraph1 = new SubPlot(winGraph1, viewGraph1, p.width, p.height);
			tg1 = new TimeGraph(p, pltGraph1, p.color(255, 0, 0), refPopulation);
			winGraph2[0] = timer;
			winGraph2[1] = timer + timeDuration;
			tg2 = new TimeGraph(p, pltGraph2, p.color(255, 0, 0), refPred);
			winGraph3[0] = timer;
			winGraph3[1] = timer + timeDuration;
			tg3 = new TimeGraph(p, pltGraph3, p.color(255, 0, 0), refPrey);
		}
	}

	@Override
	public void mouseReleased(PApplet p) {

	}

	@Override
	public void mouseDragged(PApplet p) {

	}

	@Override
	public void keyPressed(PApplet p) {
		if (p.key == 'a' || p.key == 'A'){
			PVector pos = new PVector(p.random((float) plt.getWindow()[0], (float) plt.getWindow()[1]),
					p.random((float) plt.getWindow()[2], (float) plt.getWindow()[3]));
			int color = p.color(WorldConstants.PREY_COLOR[0],
					WorldConstants.PREY_COLOR[1],
					WorldConstants.PREY_COLOR[2]);
			Animal prey = new Prey(pos, WorldConstants.PREY_VELOCITY, WorldConstants.PREY_FORCE, WorldConstants.PREY_MASS, WorldConstants.PREY_SIZE, color, p, plt, population.getImages().get(0));
			prey.addBehavior(new Wander(4));
			prey.addBehavior(new AvoidObstacle(0));
			prey.addBehavior(new Flee(0.1f));
			Eye eye = new Eye(prey, population.obstacles);
			prey.setEye(eye);
			population.allAnimals.add(prey);
		}
		if ((p.key == 'd' || p.key == 'D') && population.getNumPrey() > 0){
			PVector pos = new PVector(p.random((float) plt.getWindow()[0], (float) plt.getWindow()[1]),
					p.random((float) plt.getWindow()[2], (float) plt.getWindow()[3]));
			int color = p.color(WorldConstants.PREDATOR_COLOR[0],
					WorldConstants.PREDATOR_COLOR[1],
					WorldConstants.PREDATOR_COLOR[2]);
			Animal predator = new Predator(pos, WorldConstants.PREDATOR_VELOCITY, WorldConstants.PREDATOR_FORCE, WorldConstants.PREDATOR_MASS, WorldConstants.PREDATOR_SIZE, color, p, plt, population.getImages().get(1));
			predator.addBehavior(new Wander(1));
			predator.addBehavior(new Pursuit(6));

			List<Body> allTrackingBodies = new ArrayList<Body>();
			for (Animal a : population.allAnimals){
				if (a instanceof Prey){
					allTrackingBodies.add(a);
				}
			}
			Eye eye = new Eye(predator, allTrackingBodies);
			predator.setEye(eye);
			population.allAnimals.add(predator);
			population.target = (Animal) predator.getEye().nextTarget();
			predator.setTarget(population.target);
		}
	}

	@Override
	public void draw(PApplet p, float dt) {
		if (!start){
			p.background(0);
			p.textAlign(PApplet.CENTER);
			p.fill(112, 146, 190);
			p.textFont(title);
			p.text("Simulação de um ecossistema",p.width/2, 80);

			p.fill(255);
			p.textFont(textInit);
			p.text("\n \n Bem-vindo à simulação de um ecossistema \n \n"
					+ "Nesta simulação irá ser recriado um ecossistema simples \n constituido por 3 tipos de animais diferentes (presas, predadores e um monstro) \n \n"
					+ "Durante o decorrer da simulação podemos adicionar mais presas e predadores \n usando as teclas 'A' para adicionar presas e 'D' para adicionar predadores \n \n",p.width/2,130);
			p.text("Para iniciar a simulação, clique com o rato em cima do Canvas",p.width/2,570);
		}
		else {
			timer += dt;

			terrain.regenerate();
			population.update(dt, terrain);

			terrain.display(p);

			/*for(int i = 0; i < terrain.getGrass().size(); i++) {
				//float[] pp = plt.getPixelCoord(terrain.getGrass().get(i).getPos().x-0.25f, terrain.getGrass().get(i).getPos().y+0.25f);
				//a,b coordenadas da imagem x,y  ;   c,d largura e altura de cada imagem
				//p.image(imgGrass, pp[0]+(widthSize/3), pp[1]+(heightSize/3), widthSize, heightSize);
			}*/

			population.display(p, plt);

			if (pss.size() != 0) {
				for (ParticleSystem ps : pss) {
					ps.setLife(dt + ps.getLife());
					ps.move(dt, 1f);
					ps.display(p, plt);
				}
			}

			//population.updateImage(p);

			if (debug){
				toDebug(p, dt);
			}

			if (population.getMonster() != null) {
				if (population.getMonster().getCurrentBehaviour() instanceof Sleep && pssMaxSize + 1 > pss.size()) {
					pss.add(new ParticleSystem(population.getMonster().pos, new PVector(), 2f, 2f, psc2));
				} else {
					if(pssMaxSize<pss.size() && population.getMonster().getCurrentBehaviour() instanceof Pursuit) {
						pss.remove(pss.size() - 1);
					}
				}
			}
		}
	}

	public void toDebug(PApplet p, float dt){
		p.fill(0);
		p.noStroke();
		float[] bb = pltInBetween.getBoundingBox();
		p.rect(bb[0], bb[1], bb[2], bb[3]);

		if (timer > updateGraphTime) {
			tg1.plot(timer, population.getNumAnimals());
			tg2.plot(timer, population.getNumPred());
			tg3.plot(timer, population.getNumPrey());
			updateGraphTime = timer + intervalUpdate;
		}
	}
	private int[] getColors(PApplet p){
		int colors[] = new int[WorldConstants.NSTATES];
		for (int i = 0; i < WorldConstants.NSTATES; i++) {
			colors[i] = p.color(WorldConstants.TERRAIN_COLORS[i][0],
					WorldConstants.TERRAIN_COLORS[i][1],
					WorldConstants.TERRAIN_COLORS[i][2]);
		}
		return colors;
	}
}

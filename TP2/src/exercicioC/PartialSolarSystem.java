package exercicioC;

import mssn.IProcessingApp;
import physics.ParticleSystem;
import physics.RigidBody;
import mssn.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PartialSolarSystem implements IProcessingApp {
	private float speedUp = 60*60*24*30;
	private final float size = 1.5e11f;
	private SubPlot plt;

	private RigidBody sun;
	private float sunMass = 1.989e30f;
	private float sunRadius = 696.34e6f;
	private PVector sunPos = new PVector(0, 0);
	private int[] sunColour = {255, 87, 17};


	//mercurio
	private RigidBody mercury;
	private float mercuryMass = 0.33e24f;
	private float mercuryRadius = 2.4397e6f*30;
	private PVector mercuryPos = new PVector(0, 6.814e10f);
	private PVector mercuryVel = new PVector(4.74e4f, 0);
	private int[] mercuryColour = {161, 149, 87};


	//venus
	private RigidBody venus;
	private float venusMass = 4.87e24f;
	private float venusRadius = 6.0518e6f*30;
	private PVector venusPos = new PVector(0, 1.0843e11f);
	private PVector venusVel = new PVector(3.5e4f,0);
	private int[] venusColour = {225, 228, 195};

	//earth
	private RigidBody earth;
	private float earthMass = 5.972e24f;
	private float earthRadius = 6.371e6f*30;
	private PVector earthPos = new PVector(0, 1.4813e11f);
	private PVector earthVel = new PVector(3e4f,0);
	private int[] earthColour = {72, 118, 186};

	//mars
	private RigidBody mars;
	private float marsMass = 6.39e23f;
	private float marsRadius = 3.3895e6f*30;
	private PVector marsPos = new PVector(0,2.2554e11f);
	private PVector marsVel = new PVector(2.4077e4f,0);
	private int[] marsColour = {213,69,33};

	//moon
	private RigidBody moon;
	private float moonMass = 7.347e22f; //original 7.347e22f; 0.0123f
	private float moonRadius = 0.2727f*earthRadius;
	private PVector moonPos = new PVector(0,1.6e11f);
	private PVector moonVel = new PVector(3*3e4f,0); //3e4f
	private int[] moonColour = {226,211,160};

	private float[] viewport = {0.2f,0.2f,0.6f,0.6f};
	private double[]window = {-size, size,-size, size};

	private List<ParticleSystem> pss;
	private ParticleSystem test;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);

		//sol
		sun = new RigidBody(sunPos, new PVector(0,0), sunMass, sunRadius, sunColour);

		//planetas
		mercury = new RigidBody(mercuryPos, mercuryVel, mercuryMass, mercuryRadius, mercuryColour);
		venus = new RigidBody(venusPos, venusVel, venusMass, venusRadius, venusColour);
		earth = new RigidBody(earthPos, earthVel, earthMass, earthRadius, earthColour);
		mars = new RigidBody(marsPos, marsVel, marsMass,marsRadius,marsColour);

		//lua
		moon = new RigidBody(moonPos,moonVel,moonMass,moonRadius,moonColour);

		//cometas ao click no ecrã
		pss = new ArrayList<ParticleSystem>();
	}

	@Override
	public void mousePressed(PApplet p) {
		Random rn = new Random();
		double[] ww = plt.getWorldCoord(p.mouseX,p.mouseY);
		int[] cor = {rn.nextInt(255),rn.nextInt(255),rn.nextInt(255)};
		float vx = rn.nextFloat(4,10);
		float vy = rn.nextFloat(4,10);
		float vvx = rn.nextFloat(-10,10);
		float vvy = rn.nextFloat(-10,10);
		float lifespan = rn.nextFloat(1,5);
		//ParticleSystem ps = new ParticleSystem(new PVector((float)ww[0],(float)ww[1]),new PVector(3e9f*vvx,3e9f*vvy),5e14f,10*earthRadius,cor,lifespan,new PVector(2e9f*vx,2e9f*vy)); funciona
		ParticleSystem ps = new ParticleSystem(new PVector((float)ww[0],(float)ww[1]),new PVector(2e9f*vvx,8e9f*vvy),5e14f,10*earthRadius,cor,lifespan,new PVector(2e9f*vx,2e9f*vy));
		pss.add(ps);
	}

	@Override
	public void keyPressed(PApplet p) {

	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(p.color(0,0,0));
		sun.display(p,plt);

		PVector fMer = sun.getAttraction(mercury);
		mercury.applyForce(fMer);
		mercury.move(dt*speedUp);
		mercury.display(p, plt);

		PVector fVen = sun.getAttraction(venus);
		venus.applyForce(fVen);
		venus.move(dt*speedUp);
		venus.display(p,plt);

		PVector fEarth = sun.getAttraction(earth);
		earth.applyForce(fEarth);
		earth.move(dt*speedUp);
		earth.display(p,plt);

		PVector fMar = sun.getAttraction(mars);
		mars.applyForce(fMar);
		mars.move(dt*speedUp);
		mars.display(p,plt);

		PVector fMoo = earth.getAttraction(moon);
		moon.applyForce(fMoo.mult(1.5e5f)); //still earth 3e4f
		moon.move(dt*speedUp);
		moon.display(p,plt);

		for (int i = 0; i < pss.size(); i++) {
			PVector fCom = sun.getAttraction(pss.get(i));
			pss.get(i).applyForce(fCom.mult(1.5e12f));
			pss.get(i).move(dt * 3);
			pss.get(i).display(p, plt);
			//if (inCircle(pss.get(i))) pss.remove(pss.get(i));
		}

	}
}
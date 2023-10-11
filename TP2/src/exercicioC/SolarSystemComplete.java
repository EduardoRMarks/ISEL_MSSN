package exercicioC;

import physics.RigidBody;
import mssn.IProcessingApp;
import mssn.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class SolarSystemComplete implements IProcessingApp {

    private float speedUp = 60*60*24*30;
    private final float size = 3e12f;
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

    //jupiter
    private RigidBody jupiter;
    private float jupiterMass = 1.898e27f;
    private float jupiterRadius = 6.9911e7f*30;
    private PVector jupiterPos = new PVector(0, 7.4089e11f);
    private PVector jupiterVel = new PVector(1.3070e4f,0);
    private int[] jupiterColour = {185, 132, 71};

    //saturn
    private RigidBody saturn;
    private float saturnMass = 5.683e26f;
    private float saturnRadius = 5.8232e7f*30;
    private PVector saturnPos = new PVector(0,1.4713e12f);
    private PVector saturnVel = new PVector(9.69e3f,0);
    private int[] saturnColour = {219,146,1};

    //uranus
    private RigidBody uranus;
    private float uranusMass = 8.681e25f;
    private float uranusRadius = 2.5362e7f*30;
    private PVector uranusPos = new PVector(0, 2.9428e12f);
    private PVector uranusVel = new PVector(6.81e3f, 0);
    private int[] uranusColour = {133, 213, 247};

    //nuptune
    private RigidBody neptune;
    private float neptuneMass = 1.024e26f;
    private float neptuneRadius = 2.4622e7f*30;
    private PVector neptunePos = new PVector(0,4.4738e12f);
    private PVector neptuneVel = new PVector(5.43e3f,0);
    private int[] neptuneColour = {1,16,219};


    private float[] viewport = {0.2f,0.2f,0.6f,0.6f};
    private double[]window = {-size, size,-size, size};


    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        sun = new RigidBody(sunPos, new PVector(0,0), sunMass, sunRadius, sunColour);
        mercury = new RigidBody(mercuryPos, mercuryVel, mercuryMass, mercuryRadius, mercuryColour);
        venus = new RigidBody(venusPos, venusVel, venusMass, venusRadius, venusColour);
        earth = new RigidBody(earthPos, earthVel, earthMass, earthRadius, earthColour);
        mars = new RigidBody(marsPos, marsVel, marsMass,marsRadius,marsColour);
        jupiter = new RigidBody(jupiterPos, jupiterVel, jupiterMass, jupiterRadius, jupiterColour);
        saturn = new RigidBody(saturnPos, saturnVel, saturnMass,saturnRadius,saturnColour);
        uranus = new RigidBody(uranusPos, uranusVel, uranusMass, uranusRadius, uranusColour);
        neptune = new RigidBody(neptunePos, neptuneVel, neptuneMass,neptuneRadius,neptuneColour);
    }

    @Override
    public void mousePressed(PApplet p) {

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

        PVector fJup = sun.getAttraction(jupiter);
        jupiter.applyForce(fJup);
        jupiter.move(dt*speedUp);
        jupiter.display(p,plt);

        PVector fSat = sun.getAttraction(saturn);
        saturn.applyForce(fSat);
        saturn.move(dt*speedUp);
        saturn.display(p,plt);

        PVector fUr = sun.getAttraction(uranus);
        uranus.applyForce(fUr);
        uranus.move(dt*speedUp);
        uranus.display(p,plt);

        PVector fNep = sun.getAttraction(neptune);
        neptune.applyForce(fNep);
        neptune.move(dt*speedUp);
        neptune.display(p,plt);
    }
}

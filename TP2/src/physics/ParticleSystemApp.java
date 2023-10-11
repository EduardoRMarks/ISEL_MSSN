package physics;

import mssn.*;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleSystemApp implements IProcessingApp {
    private List<ParticleSystem> pss;
    private double[] window = {-10,10,-10,10};
    private float[] viewport = {0,0,1,1};
    private SubPlot plt;
    private int[] colour = {255,0,0};
    private PVector particleSpeed;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport,p.width,p.height);
        pss = new ArrayList<ParticleSystem>();
    }

    @Override
    public void mousePressed(PApplet p) {
        Random rn = new Random();
        double[] ww = plt.getWorldCoord(p.mouseX,p.mouseY);
        int[] cor = {rn.nextInt(255),rn.nextInt(255),rn.nextInt(255)};
        float vx = rn.nextFloat(4,10);
        float vy = rn.nextFloat(4,10);
        float lifespan = rn.nextFloat(1,3);
        ParticleSystem ps = new ParticleSystem(new PVector((float)ww[0],(float)ww[1]),new PVector(),1f,.2f,cor,lifespan,new PVector(vx,vy));
        pss.add(ps);
    }

    @Override
    public void keyPressed(PApplet p) {

    }

    @Override
    public void draw(PApplet p, float dt) {
        p.background(255);

        for(ParticleSystem ps: pss){
            ps.applyForce(new PVector(0,-1));
        }

        for(ParticleSystem ps: pss){
            ps.move(dt);
            ps.display(p,plt);
        }

    }
}

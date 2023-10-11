package physics;

import mssn.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class Particle extends RigidBody{
    private float lifespan;
    private float timer;
    public Particle(PVector pos, PVector vel, float radius, int[] colour, float lifespan) {
        super(pos, vel, 0f, radius, colour);
        this.lifespan = lifespan;
        timer = 0;
    }

    @Override
    public void move(float dt){
        super.move(dt);
        timer += dt;
    }

    public boolean isDead(){
        return timer > lifespan;
    }

    public void display(PApplet p, SubPlot plt){
        p.pushStyle();
        float alpha = PApplet.map(timer,0,lifespan,255,0);
        p.fill(this.getColour()[0],this.getColour()[1],this.getColour()[2],alpha);
        float[] pp = plt.getPixelCoord(this.getPos().x,this.getPos().y);
        float[] r = plt.getDimInPixel(this.getRadius(),this.getRadius());
        p.noStroke();
        p.circle(pp[0],pp[1],2*r[0]);
        p.popStyle();
    }
}

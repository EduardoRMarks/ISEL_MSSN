package physics;

import mssn.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class ParticleSystem  extends RigidBody{

    private List<Particle> particles;
    private float lifetime;
    private PVector particleSpeed;

    public ParticleSystem(PVector pos, PVector vel, float mass, float radius, int[] colour,float lifetime,PVector particleSpeed) {
        super(pos, vel, mass, radius, colour);
        this.lifetime = lifetime;
        this.particleSpeed = particleSpeed;
        this.particles = new ArrayList<Particle>();
    }

    @Override
    public void move(float dt){
        super.move(dt);
        addParticle();
        for (int i = particles.size()-1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.move(dt);
            if(p.isDead()) particles.remove(i);
        }
    }

    private void addParticle() {
        float vx = (float) (particleSpeed.x*(Math.random() -0.5));
        float vy = (float) (particleSpeed.y*(Math.random() -0.5));
        Particle particle = new Particle(getPos(),new PVector(vx,vy),getRadius(),getColour(),lifetime);
        particles.add(particle);
    }

    public void display(PApplet p, SubPlot plt){
        for (Particle particle : particles){
            particle.display(p,plt);
        }
    }
}

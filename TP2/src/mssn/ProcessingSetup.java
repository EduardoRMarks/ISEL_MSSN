package mssn;

import exercicioD.*;
import exercicioE.FlockApp;
import exercicioE.HunterApp;
import physics.ParticleSystemApp;
import processing.core.PApplet;

public class ProcessingSetup extends PApplet{
    private static IProcessingApp app;
    private float lastUpdateTime;
    @Override
    public void settings(){
        size(900,900);
    }

    public void setup(){
        app.setup(this);
        lastUpdateTime = millis();
    }
    @Override
    public void draw(){
        float now = millis();
        float dt = (now - lastUpdateTime) / 1000f;
        lastUpdateTime = now;
        app.draw(this, dt);
    }
    @Override
    public void mousePressed() {
        app.mousePressed(this);
    }
    @Override
    public void keyPressed() {
        app.keyPressed(this);
    }
    public static void main(String[] args) {
        //app = new PartialSolarSystem();
        app = new HunterApp();
        //app = new FlockApp();
        //app = new ParticleSystemApp();
        //app = new DeBugApp();
        //app = new SolarSystemComplete();
        PApplet.main(ProcessingSetup.class);
    }

}
/**
 * MSSN 22/23 TP3
 *
 * Trabalho realizado por:
 * Roman Ishchuk 43498
 * Eduardo Marques 45977
 *
 * Docente Paulo Vieira
 */
package tools;

import ecosystem.TestEcosystemAPP;
import ecosystem.TestTerrainApp;
import processing.core.PApplet;

public class ProcessingSetup extends PApplet{
    private static IProcessingApp app;
    private float lastUpdate;
    @Override
    public void settings(){
        size(1100,800);
    }

    public void setup(){
        app.setup(this);
        lastUpdate = millis();
    }
    @Override
    public void draw(){
        float now = millis();
        float dt = (now - lastUpdate) / 1000f;
        lastUpdate = now;
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
    @Override
    public void mouseDragged(){
        app.mouseDragged(this);
    }
    @Override
    public void mouseReleased(){
        app.mouseReleased(this);
    }
    public static void main(String[] args) {
        app = new TestEcosystemAPP();
        PApplet.main(ProcessingSetup.class);
    }

}
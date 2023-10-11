/**
 * MSSN 22/23 TP3
 *
 * Trabalho realizado por:
 * Roman Ishchuk 43498
 * Eduardo Marques 45977
 *
 * Docente Paulo Vieira
 */
package mssn;

import exercicioD.*;
import exercicioB.*;
import exercicioC.*;
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
    @Override
    public void mouseDragged(){
        app.mouseDragged(this);
    }
    @Override
    public void mouseReleased(){
        app.mouseReleased(this);
    }
    public static void main(String[] args) {
        //app = new exercicioB1();
        //pp = new exercicioB2();
        app = new exercicioC1();
        //app = new MandelbrotApp();
        //app = new JuliaApp();
        //app = new BlackHoleApp();
        //app = new VirusApp();
        //app = new VariosApp();

        PApplet.main(ProcessingSetup.class);
    }

}
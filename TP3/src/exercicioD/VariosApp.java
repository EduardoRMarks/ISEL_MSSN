/**
 * MSSN 22/23 TP3
 *
 * Trabalho realizado por:
 * Roman Ishchuk 43498
 * Eduardo Marques 45977
 *
 * Docente Paulo Vieira
 */
package exercicioD;

import mssn.IProcessingApp;
import mssn.SubPlot;
import processing.core.PApplet;

public class VariosApp implements IProcessingApp {

    private double[] window = {-2, 2, -2, 2};
    private float[] viewport = {0, 0, 0.5f, 0.5f};
    private float[] viewport2 = {0.5f,0.5f,0.5f,0.5f};
    private float[] viewport3 = {0,0.5f,0.5f,0.5f};
    private float[] viewport4 = {0.5f,0,0.5f,0.5f};
    private SubPlot plt;
    private SubPlot plt2;
    private SubPlot plt3;
    private SubPlot plt4;
    private  MandelbrotSet ms;
    private JuliaSet js;
    private BlackHoleSet bhs;
    private VirusSet vs;
    private int mx0, my0, mx1, my1;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        ms =  new MandelbrotSet(300, plt,p);
        plt2 = new SubPlot(window, viewport2, p.width,p.height);
        js = new JuliaSet(300,plt2,p);
        plt3 = new SubPlot(window,viewport3,p.width,p.height);
        bhs = new BlackHoleSet(300,plt3,p);
        plt4 = new SubPlot(window,viewport4,p.width,p.height);
        vs = new VirusSet(300,plt4,p);
    }

    @Override
    public void mousePressed(PApplet p) {
        mx0 = mx1 = p.mouseX;
        my0 = my1 = p.mouseY;
    }

    public void mouseReleased(PApplet p) {
        //System.out.println("MouseX: " + p.mouseX + "MouseY: " + p.mouseY);
        if(p.mouseX < p.width/2 && p.mouseY < p.height/2){
            double[] xy0 = plt3.getWorldCoord(mx0, my0);
            double[] xy1 = plt3.getWorldCoord(p.mouseX, p.mouseY);
            double xmin =  Math.min(xy0[0], xy1[0]);
            double xmax =  Math.max(xy0[0], xy1[0]);
            double ymin =  Math.min(xy0[1], xy1[1]);
            double ymax =  Math.max(xy0[1], xy1[1]);
            double[] window = {xmin, xmax, ymin, ymax};
            plt3 = new SubPlot(window, viewport3, p.width, p.height);
        }else if(p.mouseX < p.width/2 && p.mouseY > p.height/2){
            double[] xy0 = plt.getWorldCoord(mx0, my0);
            double[] xy1 = plt.getWorldCoord(p.mouseX, p.mouseY);
            double xmin =  Math.min(xy0[0], xy1[0]);
            double xmax =  Math.max(xy0[0], xy1[0]);
            double ymin =  Math.min(xy0[1], xy1[1]);
            double ymax =  Math.max(xy0[1], xy1[1]);
            double[] window = {xmin, xmax, ymin, ymax};
            plt = new SubPlot(window, viewport, p.width, p.height);
        }
        else if(p.mouseX > p.width/2 && p.mouseY <p.height/2){
            double[] xy0 = plt2.getWorldCoord(mx0, my0);
            double[] xy1 = plt2.getWorldCoord(p.mouseX, p.mouseY);
            double xmin =  Math.min(xy0[0], xy1[0]);
            double xmax =  Math.max(xy0[0], xy1[0]);
            double ymin =  Math.min(xy0[1], xy1[1]);
            double ymax =  Math.max(xy0[1], xy1[1]);
            double[] window = {xmin, xmax, ymin, ymax};
            plt2 = new SubPlot(window, viewport2, p.width, p.height);
        }else {
            double[] xy0 = plt4.getWorldCoord(mx0, my0);
            double[] xy1 = plt4.getWorldCoord(p.mouseX, p.mouseY);
            double xmin = Math.min(xy0[0], xy1[0]);
            double xmax = Math.max(xy0[0], xy1[0]);
            double ymin = Math.min(xy0[1], xy1[1]);
            double ymax = Math.max(xy0[1], xy1[1]);
            double[] window = {xmin, xmax, ymin, ymax};
            plt4 = new SubPlot(window, viewport4, p.width, p.height);
        }
        mx0 = mx1 = my0 = my1 = 0;
    }

    public void mouseDragged(PApplet p) {
        mx1 = p.mouseX;
        my1 = p.mouseY;
    }

    @Override
    public void keyPressed(PApplet p) {

    }

    @Override
    public void draw(PApplet p, float dt) {
        ms.display(p, plt);
        js.display(p,plt2);
        bhs.display(p,plt3);
        vs.display(p,plt4);
        displayNewWindow(p);
    }

    private void displayNewWindow(PApplet p) {
        p.pushStyle();
        p.noFill();
        p.strokeWeight(3);
        p.stroke(255,255,255);
        p.rect(mx0, my0, mx1-mx0, my1-my0);
        p.popStyle();
    }
}


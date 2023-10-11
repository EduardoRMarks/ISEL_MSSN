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

public class JuliaApp implements IProcessingApp {

    private double[] window = {-2, 2, -2, 2};
    private float[] viewport = {0, 0, 1, 1};
    private SubPlot plt;
    private JuliaSet js;
    private int mx0, my0, mx1, my1;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        js =  new JuliaSet(300, plt,p);
    }

    @Override
    public void mousePressed(PApplet p) {
        mx0 = mx1 = p.mouseX;
        my0 = my1 = p.mouseY;
    }

    public void mouseReleased(PApplet p) {
        double[] xy0 = plt.getWorldCoord(mx0, my0);
        double[] xy1 = plt.getWorldCoord(p.mouseX, p.mouseY);
        double xmin =  Math.min(xy0[0], xy1[0]);
        double xmax =  Math.max(xy0[0], xy1[0]);
        double ymin =  Math.min(xy0[1], xy1[1]);
        double ymax =  Math.max(xy0[1], xy1[1]);
        double[] window = {xmin, xmax, ymin, ymax};
        plt = new SubPlot(window, viewport, p.width, p.height);
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
        js.display(p, plt);
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


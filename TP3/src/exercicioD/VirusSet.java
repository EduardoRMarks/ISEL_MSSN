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

import mssn.SubPlot;
import processing.core.PApplet;

import static processing.core.PApplet.sqrt;
import static processing.core.PConstants.HSB;

public class VirusSet {

    private int nitter;
    private int x0, y0;
    private int dimx, dimy;


    public VirusSet(int nitter, SubPlot plt, PApplet p){
        this.nitter = nitter;
        float[] bb = plt.getBoundingBox();
        x0 = (int) bb[0];
        y0 = (int) bb[1];
        dimx = (int) bb[2];
        dimy = (int) bb[3];
        p.colorMode(HSB,1);
    }

    public void display(PApplet p, SubPlot plt){
        int tt = p.millis();
        p.loadPixels();
        for (int xx = x0; xx < x0 + dimx; xx++) {
            for (int yy = y0; yy < y0 + dimy; yy++) {
                double[] cc = plt.getWorldCoord(xx, yy);
                Complex c = new Complex(cc);
                Complex x = new Complex();
                int i;
                for (i = 0; i < nitter; i++) {
                    x.mult(x).mult(x).add(c).mult(x);
                    if (x.norm()>2){
                        break;
                    }
                }
                float h = sqrt((float) i / nitter);
                p.pixels[yy*p.width+xx] = (i == nitter) ? p.color(0) : p.color(h,255,255);
            }
        }
        p.updatePixels();
        System.out.println(p.millis()-tt);
    }
}

package physics;

import processing.core.PApplet;
import processing.core.PVector;

public class Fluid {

    private float density;

    public Fluid (float densidade){
        this.density = densidade;
    }

    public PVector drag (Mover m, PApplet p){
        float aux = (float) (0.5 * 0.47 * Math.PI * p.pow(m.getRadius(),2));
        //PVector v2 = PVector.
        //PVector f = PVector.mult(aux, Math.pow(m.getVel(),2);
        return null;
    }
}


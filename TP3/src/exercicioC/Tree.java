/**
 * MSSN 22/23 TP3
 *
 * Trabalho realizado por:
 * Roman Ishchuk 43498
 * Eduardo Marques 45977
 *
 * Docente Paulo Vieira
 */
package exercicioC;

import processing.core.PVector;
public class Tree {
    private  LSystem lsys;
    private Turtle turtle;
    private PVector position;
    private float length;
    private int numberOfSeasonsToGrow;
    private float scalingFactor;

    public Tree(String axiom, Rule[] rules, PVector position, float length, float angle, int niter, float scalingFactor){
        lsys = new LSystem(axiom, rules);
        turtle =  new Turtle(length, angle);
        this.position = position;
        this.scalingFactor = scalingFactor;
        numberOfSeasonsToGrow =  niter;
    }
}

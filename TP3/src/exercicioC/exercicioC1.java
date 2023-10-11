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

import mssn.IProcessingApp;
import mssn.SubPlot;
import processing.core.PApplet;

public class exercicioC1 implements IProcessingApp {
	private LSystem lsys;
	private double[] window = {-15, 15, 0, 15};
	private float[] viewport = {0.1f, 0.1f, 0.8f, 0.8f};
	public double[] startingPos = {0,0};
	private int iteraMax = 0;
	private int MAXGEN = 6;
	private SubPlot plt;
	private Turtle turtle;
	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		Rule[] rules2 = new Rule[2];
		Rule[] rules1 = new Rule[1];

		/*rules2[0] = new Rule('X', "F+[[X]-X]-F[-FX]+X");
		rules2[1] = new Rule('F', "FF");
		lsys = new LSystem("X", rules2);*/

		rules2[0] = new Rule('X', "F[+X]F[-X]+X");
		rules2[1] = new Rule('F', "FF");
		lsys = new LSystem("X", rules2);

		/*rules1[0] = new Rule('F', "F[+F]F[-F][F]");
		lsys = new LSystem("F", rules1);*/


		turtle = new Turtle(10, PApplet.radians(25));
	}

	@Override
	public void mousePressed(PApplet p) {
		iteraMax++;
		if (iteraMax <= MAXGEN) {
			//se MAXGEN-1: setGen5
			lsys.nextGeneration();
			turtle.scaling(0.5f);
		}
	}

	@Override
	public void mouseReleased(PApplet p) {

	}

	@Override
	public void mouseDragged(PApplet p) {

	}

	@Override
	public void keyPressed(PApplet p) {

	}

	@Override
	public void draw(PApplet p, float dt) {
		float[] bb = plt.getBoundingBox();
		p.rect(bb[0], bb[1], bb[2], bb[3]);

		turtle.setPose(startingPos, PApplet.radians(90), p, plt);
		turtle.render(lsys, p, plt);


	}
}

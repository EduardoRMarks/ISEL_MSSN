package mssn;

import dla.DLA;
import lifeGame.CellularAutomata;
import processing.core.PApplet;
public class ProcessingSetup extends PApplet {

	private static IProcessingApp app;

	public void settings(){
		app.settings(this);
	}

	public void setup(){
		app.setup(this);
	}
	public void draw(){
		app.draw(this);
	}
	public void mousePressed() {
		app.mousePressed(this);
	}
	public void keyPressed() {app.keyPressed(this);}

	public static void main(String[] args) {
		app = new CellularAutomata(140 ,140, 900, 900);
		//app = new DLA();
		PApplet.main(ProcessingSetup.class);
	}
}
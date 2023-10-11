package mssn;

import processing.core.PApplet;

public interface IProcessingApp {
	void settings(PApplet p);
	void setup(PApplet p);

	void mousePressed(PApplet p);

	void keyPressed(PApplet p);

	void draw(PApplet p);
}

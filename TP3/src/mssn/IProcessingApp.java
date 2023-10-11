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

import processing.core.PApplet;

public interface IProcessingApp {
    void setup(PApplet p);

    void mousePressed(PApplet p);

	/*void mouseReleased(PApplet p);

	void mouseDragged(PApplet p);*/

    void mouseReleased(PApplet p);

    void mouseDragged(PApplet p);

    void keyPressed(PApplet p);

    void draw(PApplet p, float dt);
}
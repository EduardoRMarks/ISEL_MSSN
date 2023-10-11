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

import mssn.SubPlot;
import processing.core.PApplet;

/*
* Classe que vai servir para desenhar no canvas com F, ++ etc
*/
public class Turtle {

	private float len;
	private float angle;

	public Turtle(float len, float angle){
		this.len = len;
		this.angle = angle;
	}

	//orientation = 0 (direira), 90 (cima)
	public void setPose(double[] position, float orientation, PApplet p, SubPlot plt){
		float[] pp = plt.getPixelCoord(position);
		p.translate(pp[0], pp[1]);
		p.rotate(-orientation);
	}

	public void scaling(float s){
		len *= s;
	}

	public void render(LSystem lsys, PApplet p, SubPlot plt){
		p.stroke(0);
		boolean t = false;

		//converter len to px
		float[] lenPX = plt.getVectorCoord(len, len);

		for (int i = 0; i < lsys.getSequence().length(); i++) {
			char c = lsys.getSequence().charAt(i);
			//anda e desenha
			//System.out.println(lsys.getSequence());
			if (c == 'F' || c == 'G'){
				p.pushMatrix();
				if(!t){
					p.stroke(163,108,77);
				}
				else {
					p.stroke(55,125,34);
				}
				p.line(0, 0, lenPX[0], 0);
				p.popMatrix();
				p.translate(lenPX[0],  0);
			}
			//anda, mas não desenha
			else if (c == 'f'){
				p.translate(lenPX[0], 0);
			}
			else if (c == '+'){
				t = true;
				p.rotate(angle);
			}
			else if (c == '-'){
				t = true;
				p.rotate(-angle);
			}
			else if (c == '['){
				t = true;
				p.pushMatrix();
			}
			else if (c == ']'){
				t = true;
				p.popMatrix();
			}
		}

	}
}

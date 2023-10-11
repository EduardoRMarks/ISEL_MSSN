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

public class LSystem {
	private String sequence;
	private String secToLast;
	private Rule[] ruleset;
	public int generation;

	public LSystem(String axiom, Rule[] ruleset){
		sequence = axiom;
		this.ruleset = ruleset;
		generation = 0;
	}

	public int getGeneration(){
		return generation;
	}

	public String getSequence(){
		return sequence;
	}


	public void nextGeneration(){
		generation++;
		String nextGen = "";
		for (int i = 0; i < sequence.length(); i++) {
			char c = sequence.charAt(i);
			//String replace = String.valueOf(c);
			String replace = "" + c;
			for (int j = 0; j < ruleset.length; j++) {
				if (c == ruleset[j].getSymbol()){
					replace = ruleset[j].getString();
					break;
				}
			}
			nextGen += replace;
		}
		this.sequence = nextGen;
	}
}

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
public class Rule {
	private char symbol;
	private String string;

	public Rule(char symbol, String string){
		this.symbol = symbol;
		this.string = string;
	}

	public char getSymbol(){
		return symbol;
	}

	public String getString(){
		return string;
	}
}

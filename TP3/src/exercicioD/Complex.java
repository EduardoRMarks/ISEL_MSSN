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

public class Complex {

    private double a, b;

    public Complex(double a, double b){
        this.a = a;
        this.b = b;
    }

    public Complex(double[] ab){
        this.a = ab[0];
        this.b = ab[1];
    }

    public Complex(){
        this.a = 0;
        this.b = 0;
    }

    public Complex mult (Complex x) {
        double real = this.a * x.a - this.b * x.b;
        double imag = this.a * x.b + this.b * x.a;
        this.a = real;
        this.b = imag;
        return this;
    }

    public Complex add(Complex x) {
        this.a += x.a;
        this.b += x.b;
        return this;
    }

    public double norm() {
        return Math.sqrt(a * a + b * b);
    }
}

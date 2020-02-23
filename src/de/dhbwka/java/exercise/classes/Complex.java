package de.dhbwka.java.exercise.classes;

public class Complex {
    double real;
    double imag;

    public Complex() {
        this(0, 0);
    }

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex add(Complex comp) {
        return new Complex(this.real + comp.getReal(), this.imag + comp.getImag());
    }

    public Complex sub(Complex comp) {
        return new Complex(this.real - comp.getReal(), this.imag - comp.getImag());
    }

    public Complex mult(Complex comp) {
        double c = this.real * comp.getReal() - this.imag * comp.getImag();
        double i = this.real * comp.getImag() + this.imag * comp.getReal();

        return new Complex(c, i);
    }

    public Complex div(Complex comp) {
        double denom = Math.pow(comp.getReal(), 2) + Math.pow(comp.getImag(), 2);
        double c = this.real * comp.getReal() + this.imag * comp.getImag();
        double i = this.real * comp.getImag() - this.imag * comp.getReal();

        return new Complex(c / denom, i / denom);
    }

    public double getMagnitude() {
        return Math.sqrt(this.real * this.real + this.imag + this.imag);
    }

    public boolean isLess(Complex comp) {
        return this.getMagnitude() < comp.getMagnitude();
    }

    public static void sortArr(Complex[] comps) {
        for(int i = 1; i < comps.length; ++i) {
            for(int j = 0; j < comps.length - i; ++j) {
                if(comps[j].getMagnitude() > comps[j+1].getMagnitude()) {
                    Complex temp = comps[j];
                    comps[j] = comps[j+1];
                    comps[j+1] = temp;
                }
            }
        }
    }

    public String toString() {
        return "Comp: " + this.real + " Imag: " + this.imag;
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }
}
package de.dhbwka.java.exercise.classes;

public class Horner {
    private double[] coefficients;

    public Horner() {
        this(new double[0]);
    }

    public Horner(double[] coeff) {
        this.coefficients = coeff;
    }

    public double getValue(double x) {
        if(this.coefficients.length > 0) {
            double sum = this.coefficients[this.coefficients.length - 1];

            for(int i = this.coefficients.length -2; i >= 0; --i) {
                sum = sum * x + this.coefficients[i];
            }

            return sum;
        } else {
            return 0;
        }
    }

    public String toString() {
        String result = "";

        for(int i = 0; i < this.coefficients.length -1; ++i) {
            result += this.coefficients[i] + "*x^" + i;

            if(i < this.coefficients.length -2) {
                result += " + ";
            }
        }

        return result;
    }
}

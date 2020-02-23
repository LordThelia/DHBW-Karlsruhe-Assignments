package de.dhbwka.java.exercise.classes;

public class Polynomial {
    private double a, b, c = 0;

    public Polynomial() {
        this(0, 0 ,0);
    }

    public Polynomial(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double[] calculateZeros() {
        double discriminant = Math.pow(this.b, 2) - 4 * this.a * this.c;

        if(discriminant == 0) {
            double[] result = new double[1];

            result[0] = -this.b / (2 * this.a);

            return result;
        } else if(discriminant > 0) {
            double[] result = new double[2];

            result[0] = (-this.b + Math.sqrt(discriminant)) / (2 * this.a);
            result[1] = (-this.b - Math.sqrt(discriminant)) / (2 * this.a);

            return result;
        } else {
            return new double[0];
        }
    }

    public Polynomial addPolynomials(Polynomial p2) {
        return new Polynomial(this.a + p2.getA(), this.b + p2.getB(), this.c + p2.getC());
    }

    public Polynomial subPolynomials(Polynomial p2) {
        return new Polynomial(this.a - p2.getA(), this.b - p2.getB(), this.c - p2.getC());
    }

    public Polynomial multiplyWithScalar(double factor) {
        return new Polynomial(this.a * factor, this.b * factor, this.c * factor);
    }

    public double calculateValue(double x) {
        double ax = this.a * Math.pow(x, 2);
        double bx = this.b * x;

        return ax + bx + this.c;
    }

    public String toString() {
        return a + "x² " + b + "x " + c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}


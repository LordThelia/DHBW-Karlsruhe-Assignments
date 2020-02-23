package de.dhbwka.java.exercise.classes;

public class Tests {
    public static void main(String[] args) {
        System.out.println("Radio: ");
        testRadio();
        System.out.println();

        System.out.println("Account: ");
        testAccounts();
        System.out.println();

        System.out.println("Point: ");
        testPoints();
        System.out.println();

        System.out.println("Polynomial: ");
        polynomial();
        System.out.println();

        System.out.println("Complex: ");
        complex();
        System.out.println();

        System.out.println("Horner:");
        horner();
        System.out.println();

        System.out.println("Nimmspiel:");
        //takeGame();
        System.out.println();

        System.out.println("Lotto:");
        //lotto();
        System.out.println();

        System.out.println("MasterMind:");
        //masterMind();
        System.out.println();
    }

    private static void masterMind() {
        MasterMind mm = new MasterMind();
        mm.play();
    }

    private static void lotto() {
        Lotto lt = new Lotto(6, 49);
        lt.play();
    }

    private static void takeGame() {
        Nimmspiel game = new Nimmspiel("P1", "P2");
        System.out.println(game);
        game.play();
    }

    private static void testAccounts() {
        Account account= new Account(4711, "Donald Duck", 500, 1000);
        System.out.println(account);
        account.processDeposit(200);
        System.out.println(account);
        account.processPayment(400);
        System.out.println(account);
        account.processPayment(2000);
        System.out.println(account);
    }

    private static void testPoints() {
        Point pointA= new Point(4.0, 2.0);
        System.out.println("A: "+ pointA);
        Point pointB= new Point(-1.0, -1.0);
        System.out.println("B: "+ pointB);
        System.out.println("AbstandA-B: "+ pointA.distanceBetween(pointB));
        pointA= pointA.mirroredOnOrigin();
        System.out.println("A': "+ pointA);
        System.out.println("AbstandA’-B: "+ pointA.distanceBetween(pointB));
    }

    private static void testRadio() {
        Radio radio= new Radio(false, 7, 93.5);
        System.out.println(radio);
        radio.turnOn();
        System.out.println(radio);
        radio.incVolume();
        radio.incVolume();
        System.out.println(radio);
        radio.incVolume();
        radio.incVolume();
        System.out.println(radio);
        radio.decVolume();
        System.out.println(radio);
        radio.setFrequency(97.8);
        System.out.println(radio);
        radio.setFrequency(112.7);
        System.out.println(radio);
        radio.turnOff();
        System.out.println(radio);
    }

    private static void horner() {
        double[] coeff = {1, 2, 3, 4.5, 8, -10, 3, 4, 2, -3, 0.5};
        Horner poly = new Horner(coeff);
        System.out.println("Poly: " + poly.toString());
        System.out.println("f(1.5): " + poly.getValue(1.5));
    }

    private static void complex() {
        Complex c1 = new Complex(1.0,2.0);
        Complex c2 = new Complex(2.0,1.0);
        System.out.println("C1: " + c1);
        System.out.println("C2: " + c2);
        System.out.println("C1+C2: " + c1.add(c2));
        System.out.println("C1-C2: " + c1.sub(c2));
        System.out.println("C1*C2: " + c1.mult(c2));
        System.out.println("C1/C2: " + c1.div(c2));
        System.out.println("C1<C2?: " + c1.isLess(c2));

        Complex[] comps = new Complex[10];
        for(int i = 0; i < comps.length; ++i) {
            comps[i] = new Complex(Math.random() * 10, Math.random() * 10);
        }

        System.out.println("Unsortiert:");
        for(Complex c : comps) {
            System.out.printf("%5.3f + %5.3fi Betrag: %5.3f\n", c.getReal(), c.getImag(), c.getMagnitude());
        }

        Complex.sortArr(comps);
        System.out.println("Sortiert:");
        for(Complex c : comps) {
            System.out.printf("%5.3f + %5.3fi Betrag: %5.3f\n", c.getReal(), c.getImag(), c.getMagnitude());
        }
    }

    private static void polynomial() {
        Polynomial p1 = new Polynomial(2, 0, 0);
        Polynomial p2 = new Polynomial(0, -4, 1);
        Polynomial p3 = p1.addPolynomials(p2);
        Polynomial p4 = p3.multiplyWithScalar(2.0);

        double[] zeros = p2.calculateZeros();

        System.out.println("P1: " + p1.toString());
        System.out.println("P2: " + p2.toString());
        System.out.println("P3: " + p3.toString());
        System.out.println("P4: " + p4.toString());
        System.out.println("Nullstellen P3: ");
        for(double zero: zeros) {
            System.out.print(zero + " ");
        }
    }
}

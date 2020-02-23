package de.dhbwka.java.exercise.classes.periodic;

public class PeriodicTable {
    private Element[] elements = new Element[118];

    public PeriodicTable() {}

    public void addElement(Element e) {
        if (!hasElement(e.getOrdinal())) {
            elements[e.getOrdinal()] = e;
        }
    }

    public boolean hasElement(int o) {
        return elements[o] != null;
    }

    public Element getElement(int o) {
        return elements[o];
    }

    public Element[] getMetals() {
        int metalCount = 0;

        for (Element e : elements) {
            if (e != null && e instanceof Metal) {
                ++metalCount;
            }
        }

        Element[] result = new Element[metalCount];
        int pos = 0;
        for (Element elm : elements) {
            if (elm != null && elm instanceof Metal) {
                result[pos] = elm;
                ++pos;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        PeriodicTable pt = new PeriodicTable();

        pt.addElement(new Element("Wasserstoff", "H", 1, 'K', Element.GAS, Element.MAIN));
        pt.addElement(new Element("Helium", "He", 2, 'K', Element.GAS, Element.MAIN));
        pt.addElement(new Metal("Natrium", "Na", 11, 'M', Element.SOLID, Element.MAIN,false,21E6));
        pt.addElement(new Metal("Eisen", "Fe", 26, 'N', Element.SOLID, Element.SIDE,false,10.02E6));
        pt.addElement(new Metal("Germanium", "Ge", 32, 'N', Element.SOLID, Element.SIDE,true,1.45));
        pt.addElement(new Element("Brom", "Br", 35, 'N', Element.LIQUID, Element.MAIN));
        pt.addElement(new Metal("Tellur", "Te", 52, 'O', Element.SOLID, Element.MAIN,true,0.005));
        pt.addElement(new Metal("Gold", "Au", 79, 'P', Element.SOLID, Element.SIDE,false,44E6));

        System.out.println("Elemente:");
        for(Element elm : pt.elements) {
            if (elm != null) {
                System.out.println(elm);
            }
        }

        System.out.println("\nMetalle:");
        for(Element elm : pt.getMetals()) {
            System.out.println(elm);
        }

        System.out.println("\nGold:");
        System.out.println(pt.getElement(79));
    }
}

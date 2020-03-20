package de.dhbwka.java.exams.darts;

public class Board {
    private Field[] fields = new Field[63];

    public Board() {
        // 0 Field
        this.fields[0] = new Field("X", 0, false);

        for(int i = 1; i <= 20; ++i) {
            // 1 - 20
            this.fields[i] = new Field(Integer.toString(i), i, false);
            // 1 - 20 double
            this.fields[i + 20] = new Field("D" + i, i * 2, true);
            // 1 - 20 triple
            this.fields[i + 40] = new Field("T" + i, i * 3, false);
        }

        // Single Bull, Bullseye
        this.fields[61] = new Field("25", 25, false);
        this.fields[62] = new Field("50", 50, true);
    }

    public Field parseField(String label) {
        Field field = null;

        for(Field f: this.fields) {
            if(f.getLabel().equalsIgnoreCase(label)) {
                field = f;
            }
        }

        return field;
    }
}

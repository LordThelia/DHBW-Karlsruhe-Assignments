package de.dhbwka.java.exams.darts;

public class Visit {
    private Field[] fields;

    public Visit(Field[] fields) throws IllegalArgumentException {
        if(fields.length > 3) {
            throw new IllegalArgumentException("Too many Darts thrown!");
        }

        this.fields = fields;
    }

    public Field[] getFields() {
        return this.fields;
    }

    public int getValue() {
        int value = 0;

        for(int i = 0; i < this.fields.length; ++i) {
            if(this.fields[i] != null) {
                value += this.fields[i].getValue();
            }
        }

        return value;
    }

    public Field getLastField() {
        Field result = null;

        for(int i = 0; i < this.fields.length; ++i) {
            if(this.fields[i] != null) {
                result = this.fields[i];
            }
        }

        return result;
    }
}

package de.dhbwka.java.exercise.docu;

/**
 * @author DHBW-KA TINF.
 * @version 1.0.
 */
public class Radio {
    /**
     * boolean - Radio status - on = true, off = false.
     */
    private boolean on;

    /**
     * int - Radio volume - between 0 and 10.
     */
    private int volume;

    /**
     * double - frequency used by the radio - between 85.0 and 110.0.
     */
    private double frequency;

    /**
     * Default constructor - on = false, volume = 5, frequency = 100.0.
     */
    public Radio() {
        this.on = false;
        this.volume = 5;
        this.frequency = 100.0;
    }

    /**
     *
     * @param on boolean - if the Radio is turned on.
     * @param volume int -  volume of the radio - between 0 and 10.
     * @param frequency double - frequency used by the radio - between 85.0 and 110.0.
     */
    public Radio(boolean on, int volume, double frequency)  {
        this.on = on;
        this.volume = volume;
        this.frequency = frequency;
    }

    /**
     * Increment the Radio volume by 1 if radio is turned on and the volume is lower than 10.
     */
    public void incVolume() {
        if(this.on && this.volume < 10) {
            ++this.volume;
        }
    }

    /**
     * Decrement the Radio volume by 1 if the Radio is turned on and the volume is greater than 0.
     */
    public void decVolume() {
        if(this.on && this.volume > 0) {
            --this.volume;
        }
    }

    /**
     * Turn the radio on - set on to true.
     */
    public void turnOn() {
        this.on = true;
    }

    /**
     * Turn the radio off - set on to false.
     */
    public void turnOff() {
        this.on = false;
    }

    /**
     * Set the frequency used by the radio. Only if the frequency is between 85.0 and 110.0. If not 99.0 is going to be used.
     * @param frequency double - the frequency which the radio is going to be using
     */
    public void setFrequency(double frequency) {
        if(frequency >= 85.0 && frequency <= 110.0) {
            this.frequency = frequency;
        } else {
            this.frequency = 99.0;
        }
    }

    /**
     * Get all information about the radio as a String
     * @return String - Complete information about the radio
     */
    @Override
    public String toString() {
        return "Radio " + (this.on ? "an" : "aus") + " Freq=" + this.frequency + ", Laut=" + this.volume;
    }
}
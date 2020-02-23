package de.dhbwka.java.exercise.classes;

public class Point {
    private double x_coord;
    private double y_coord;

    public Point(double x, double y) {
        this.x_coord = x;
        this.y_coord = y;
    }

    public String toString() {
        return "X: " + this.x_coord + " Y: " + this.y_coord;
    }

    public String distanceOrigin() {
        double dist = Math.sqrt((Math.pow(this.x_coord, 2.0) + Math.pow(this.y_coord, 2.0)));
        return "Distance: " + dist;
    }

    public Point mirroredOnX() {
        return new Point(this.x_coord * -1, this.y_coord);
    }

    public Point mirroredOnY() {
        return new Point(this.x_coord, this.y_coord *-1);
    }

    public Point mirroredOnOrigin() {
        return new Point(this.x_coord * -1, this.y_coord * -1);
    }

    public String distanceBetween(Point p2) {
        double dist = Math.sqrt((Math.pow((this.x_coord - p2.getX_coord()), 2.0) + Math.pow((this.y_coord - p2.getY_coord()), 2.0)));
        return "Distance: " + Double.toString(dist);
    }

    public double getX_coord() {
        return x_coord;
    }

    public void setX_coord(double x_coord) {
        this.x_coord = x_coord;
    }

    public double getY_coord() {
        return y_coord;
    }

    public void setY_coord(double y_coord) {
        this.y_coord = y_coord;
    }
}
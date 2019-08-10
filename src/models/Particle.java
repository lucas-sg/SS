package models;

import java.awt.geom.Point2D;
import java.util.HashSet;

public class Particle {
    private Point2D c; // Center
    private double r; // Radius
    private Property p;
    public final static double maxRadius = 5.00; // TODO: Redefine
    public final static double minRadius = 0.01; // TODO: Redefine
    private HashSet<Particle> neighbours;

    public Particle(final Point2D c, final double r, Property p) {
        this.setCenter(c);
        this.setRadius(r);
        this.setProperty(p);
        neighbours = new HashSet<>();
    }

    public Point2D getCenter() {
        return c;
    }

    private void setCenter(final Point2D c) {
        this.c = c;
    }

    public double getRadius() {
        return r;
    }

    private void setRadius(final double r) {
        this.r = r;
    }

    public Property getProperty() {
        return p;
    }

    private void setProperty(final Property p) {
        this.p = p;
    }

    public HashSet<Particle> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(final Particle neighbour) {
        neighbours.add(neighbour);
    }
}
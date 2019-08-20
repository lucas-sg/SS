package models;

import java.awt.geom.Point2D;
import java.util.HashSet;

public class Particle {
    private Point2D c; // Center
    private double r; // Radius
    public final static double maxRadius = 5.00; // TODO: Redefine
    public final static double minRadius = 0.01; // TODO: Redefine
    private HashSet<Particle> neighbours;

    public Particle(final Point2D c, final double r) {
        this.setCenter(c);
        this.setRadius(r);
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

    public HashSet<Particle> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(final Particle neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Particle)) {
            return false;
        }

        Particle other = (Particle) obj;

        return other.getCenter().equals(this.c);
    }
}
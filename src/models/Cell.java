package models;

import java.util.ArrayList;
import java.util.Collection;

public class Cell {
    private final Collection<Particle> particles;
    private double size;

    public Cell(final double size) {
        this.particles = new ArrayList<>();
        this.setSize(size);
    }

    public Collection<Particle> getParticles() {
        return particles;
    }

    public void addParticle(final Particle newParticle) {
        particles.add(newParticle);
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
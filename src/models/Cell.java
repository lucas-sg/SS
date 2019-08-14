package models;

import java.util.Collection;
import java.util.HashSet;

public class Cell {
    private final Collection<Particle> particles;
    private double size;

    public Cell(final double size) {
        this.particles = new HashSet<>();
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

    private void setSize(double size) {
        this.size = size;
    }
}
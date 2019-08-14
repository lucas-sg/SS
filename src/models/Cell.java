package models;

import java.util.Collection;
import java.util.ArrayList;;

public class Cell {
    private final Collection<Particle> particles;

    public Cell() {
        this.particles = new ArrayList<>();
    }

    public Collection<Particle> getParticles() {
        return particles;
    }

    public void addParticle(final Particle newParticle) {
        particles.add(newParticle);
    }
}
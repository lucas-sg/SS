package engine;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import models.Particle;

public class OutputRow {
    private Particle p;
    private Collection<Particle> neighbours; 
    private Duration d;

    public OutputRow(final Particle p, final Collection<Particle> neighbours, 
        final Duration d) {
        this.setParticle(p);
        this.setNeighbours(neighbours);
        this.setExecutionDuration(d);
    }

    private void setParticle(final Particle p) {
        this.p = p;
    }
    
    public Particle getParticle() {
        return this.p;
    }

    public Collection<Particle> getNeighbours() {
        return this.neighbours;
    }

    private void setNeighbours(final Collection<Particle> neighbours) {
        this.neighbours = new ArrayList<>(neighbours);
    }

    public Duration getExecutionDuration() {
        return this.d;
    }

    private void setExecutionDuration(final Duration duration) {
        this.d = duration;
    }
}
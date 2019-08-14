package engine;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import models.Particle;

public class OutputRow {
    private Collection<Particle> neighbours; 
    private Duration d;

    public OutputRow(final Collection<Particle> neighbours, final Duration d) {
        this.setNeighbours(neighbours);
        this.setExecutionDuration(d);
    }

    public Collection<Particle> getNeighbours() {
        return neighbours;
    }

    private void setNeighbours(final Collection<Particle> neighbours) {
        this.neighbours = new ArrayList<>(neighbours);
    }

    public Duration getExecutionDuration() {
        return d;
    }

    private void setExecutionDuration(final Duration duration) {
        this.d = duration;
    }
}
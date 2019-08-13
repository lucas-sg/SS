package engine;

import java.time.Duration;
import java.util.ArrayList;

import models.Particle;

public class OutputRow {
    private ArrayList<Particle> neighbours; 
    private Duration d;

    public OutputRow(final ArrayList<Particle> neighbours, final Duration d) {
        this.setNeighbours(neighbours);
        this.setExecutionDuration(d);
    }

    public ArrayList<Particle> getNeighbours() {
        return neighbours;
    }

    private void setNeighbours(final ArrayList<Particle> neighbours) {
        this.neighbours = new ArrayList<>(neighbours);
    }

    public Duration getExecutionDuration() {
        return d;
    }

    private void setExecutionDuration(final Duration duration) {
        this.d = duration;
    }
}
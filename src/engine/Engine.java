package engine;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import models.CIMInput;
import models.Particle;
import models.ParticleFactory;
import models.Board;

public class Engine {
    private Board board;
    private ParticleFactory particleFactory;

    public Engine() {
        this.particleFactory = new ParticleFactory();
    }

    public ArrayList<OutputRow> applyCIM(final CIMInput input) {
        setEnvironment(input);
    
        return cellIndexMethod(input);
    }

    private ArrayList<OutputRow> cellIndexMethod(final CIMInput input) {
        ArrayList<Particle> particles;
        ArrayList<OutputRow> output;
        OutputRow outputRow;

        particles = particleFactory.getAllParticles();
        output = new ArrayList<>();

        for (Particle p : particles) {
            outputRow = getOutputRowForParticle(p);
            output.add(outputRow);
        }
        
        return output;
    }

    private void setEnvironment(final CIMInput input) {
        double r1, r2;        
        particleFactory.generateRandomParticles(input.getN());
        r1 = particleFactory.getBiggestRadius();
        r2 = particleFactory.getSecondBiggestRadius();
        this.board = new Board(input, r1, r2);
    }

    private OutputRow getOutputRowForParticle(final Particle p) {
        ArrayList<Particle> neighbours;
        Instant start, end;
        Duration timeElapsed;

        start       = Instant.now();
        neighbours  = getNeighboursForParticle(p);
        end         = Instant.now();
        timeElapsed = Duration.between(start, end);

        return new OutputRow(neighbours, timeElapsed);
    }

    private ArrayList<Particle> getNeighboursForParticle(final Particle p) {
        board.getMatrix();

        return new ArrayList<>();
    }
}
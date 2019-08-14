package engine;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import models.Particle;
import models.ParticleFactory;
import models.Board;

public class Engine {
    private CIMInput input;
    private Board board;
    private ParticleFactory particleFactory;

    public Engine(final CIMInput input) {
        this.input = input;
        this.particleFactory = new ParticleFactory(this.input.getRC());
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
        this.particleFactory.generateRandomParticles(input.getN());
        r1 = particleFactory.getBiggestRadius();
        r2 = particleFactory.getSecondBiggestRadius();
        this.board = new Board(input, r1, r2);
        this.board.populate(this.particleFactory);
    }

    private OutputRow getOutputRowForParticle(final Particle p) {
        Collection<Particle> neighbours;
        Instant start, end;
        Duration timeElapsed;

        start       = Instant.now();
        neighbours  = getNeighboursForParticle(p);
        end         = Instant.now();
        timeElapsed = Duration.between(start, end);

        return new OutputRow(neighbours, timeElapsed);
    }

    private Collection<Particle> getNeighboursForParticle(final Particle p) {
        Collection<Particle> neighbours = new ArrayList<>();
        
        addSameCellNeighbours(p, neighbours);        

        return new ArrayList<>(); // TODO: Finish the algorithm
    }

    private void addSameCellNeighbours(final Particle p, Collection<Particle> neighbours) {
        Collection<Particle> sameCellNeighbours = board.getParticlesFromCell(p.getCenter());

        sameCellNeighbours.remove(p);
        
        for (Particle supposedNeighbour : sameCellNeighbours) {
            if (!particleFactory.particlesInteract(p, supposedNeighbour)) {
                sameCellNeighbours.remove(supposedNeighbour);
            }
        }

        neighbours.addAll(sameCellNeighbours);
    }
}
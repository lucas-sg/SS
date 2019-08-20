package engine;

import java.awt.Point;
import java.awt.geom.Point2D;
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

    public ArrayList<OutputRow> applyCIM() {
        setEnvironment();
    
        return cellIndexMethod();
    }

    private ArrayList<OutputRow> cellIndexMethod() {
        ArrayList<Particle> particles = particleFactory.getAllParticles();
        ArrayList<OutputRow> output   = new ArrayList<>();
        OutputRow outputRow;

        for (Particle p : particles) {
            outputRow = getOutputRowForParticle(p);
            output.add(outputRow);
        }
        
        return output;
    }

    private void setEnvironment() {
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

        return new OutputRow(p, neighbours, timeElapsed);
    }

    private Collection<Particle> getNeighboursForParticle(final Particle p) {
        Collection<Particle> neighbours = new ArrayList<>();
        
        addSameCellNeighbours(p, neighbours);        
        addLShapeNeighbours(p, neighbours);

        return neighbours;
    }

    private void addSameCellNeighbours(final Particle p, Collection<Particle> neighbours) {
        Collection<Particle> sameCellNeighbours = board.getParticlesFromCell(p.getCenter());
        
        for (Particle supposedNeighbour : sameCellNeighbours) {
            if (!particleFactory.particlesInteract(p, supposedNeighbour)) {
                sameCellNeighbours.remove(supposedNeighbour);
            }
        }

        neighbours.addAll(sameCellNeighbours);
    }

    private void addLShapeNeighbours(final Particle p, Collection<Particle> neighbours) {
        ArrayList<Point> pointsInLShape     = getLShapePoints(p.getCenter());
        Collection<Particle> cellNeighbours = new ArrayList<>();
        Point2D currCell;
        double i, j;

        
        for (Point coord : pointsInLShape) {
            i        = coord.getX();
            j        = coord.getY();
            currCell = new Point2D.Double(i, j);
            
            cellNeighbours.addAll(board.getParticlesFromCell(currCell));
        }

        for (Particle supposedNeighbour : cellNeighbours) {
            if (particleFactory.particlesInteract(p, supposedNeighbour)) {
                neighbours.add(supposedNeighbour);
            }
        }
    }

    private ArrayList<Point> getLShapePoints(final Point2D coord) {
        ArrayList<Point> pointsInLShape = new ArrayList<>();
        Point upCenter, upRight, centerRight, downRight;
        int x = (int) coord.getX();
        int y = (int) coord.getY();
        int m = board.getM();

        if (y + 1 < m) {
            upCenter = new Point(x, y + 1);
            pointsInLShape.add(upCenter);
        }

        if (x + 1 < m) {
            if (y + 1 < m) {
                upRight = new Point(x + 1, y + 1);
                pointsInLShape.add(upRight);
            }

            centerRight = new Point(x + 1, y);
            pointsInLShape.add(centerRight);

            if (y - 1 >= 0) {
                downRight = new Point(x + 1, y - 1);
                pointsInLShape.add(downRight);
            }
        }

        return pointsInLShape;
    }
}
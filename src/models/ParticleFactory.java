package models;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Random;

public class ParticleFactory {
    private Random rand;
    private HashMap<Integer, Particle> particles;
    private static int particleID;
    
    public ParticleFactory() {
        particleID = 0;
    }

    public Particle createParticle(final Point2D c, final double r, final Property p) {
        Particle particle = new Particle(c, r, p);
        
        particles.put(particleID + 1, particle);

        return particle;
    }

    public Particle createParticle(final double r, final Property p) {
        Point2D randomCenter = generateRandomPoint();

        return this.createParticle(randomCenter, r, p);
    }

    public Particle createParticle(final Property p) {
        double randomRadius = generateRandomDouble(Particle.minRadius, Particle.maxRadius);

        return this.createParticle(randomRadius, p);
    }

    public Particle createParticle() {
        return this.createParticle(null);
    }

    private double generateRandomDouble(final double min, final double max) {
        rand = new Random();
        return min + (max - min) * rand.nextDouble();   
    }

    private Point2D generateRandomPoint() {
        double x = generateRandomDouble(0.00, Board.maxBoardSize);
        double y = generateRandomDouble(0.00, Board.maxBoardSize);

        return new Point2D.Double(x, y);
    }
}
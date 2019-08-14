package models;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class ParticleFactory {
    private Random rand;
    private ArrayList<Particle> particles;
    private double biggestRadius              = 0.0;
    private double secondBiggestRadius        = 0.0;
    // Quantity of attempts at creating a random particle
    private static final int ALLOWED_ATTEMPTS = 30; // TODO: Redefine

    public ParticleFactory() {
    }

    public void generateRandomParticles(int quantity, final double radius) {
        Particle newRandomParticle;

        while (quantity > 0) {
            newRandomParticle = createParticle(radius, null);
            particles.add(newRandomParticle);
            quantity--;
        }
    }

    public void generateRandomParticles(int quantity) {
        generateRandomParticles(quantity, 0.0);
    }

    public Particle createParticle(final Point2D center, final double radius, final Property prop)
            throws IllegalArgumentException {
        if (checkCorrectParticleDistribution(center, radius) != particles.size()) {
            throw new IllegalArgumentException("Particles cannot overlap");
        }

        Particle particle = new Particle(center, radius, prop);

        particles.add(particle);

        if (particle.getRadius() > biggestRadius) {
            setSecondBiggestRadius(biggestRadius);
            setBiggestRadius(particle.getRadius());
        }

        return particle;
    }

    public Particle createParticle(final double radius, final Property prop) {
        Point2D randomCenter               = null;
        int checkedParticles               = 0;
        int attempts                       = 0;
        boolean newParticleOverlapsAnother = true;
        
        while (newParticleOverlapsAnother && attempts < ALLOWED_ATTEMPTS) {
            randomCenter     = generateRandomPoint();
            checkedParticles = checkCorrectParticleDistribution(randomCenter, radius);

            if (checkedParticles == particles.size()) {
                newParticleOverlapsAnother = false;
            }

            attempts++;
        }

        return this.createParticle(randomCenter, radius, prop);
    }
    
    public Particle createParticleWithRandomRadius(final Property prop) {
        double randomRadius = generateRandomDouble(Particle.minRadius, Particle.maxRadius);

        return this.createParticle(randomRadius, prop);
    }

    public Particle createParticle(final Property prop) {
        return this.createParticle(0.0, prop);
    }

    public Particle createParticle() {
        return this.createParticle(null);
    }

    private double generateRandomDouble(final double min, final double max) {
        rand = new Random();
        return min + (max - min) * rand.nextDouble();   
    }

    private Point2D generateRandomPoint() {
        double x = generateRandomDouble(0.0, Board.maxBoardSize);
        double y = generateRandomDouble(0.0, Board.maxBoardSize);

        return new Point2D.Double(x, y);
    }

    // Source: https://www.geeksforgeeks.org/check-two-given-circles-touch-intersect/
    private int getBorderDistanceBetweenCircles(final Point2D c1, final double r1, 
        final Point2D c2, final double r2) {
        double deltaX, deltaY, distance;

        if (r1 == 0.0 || r2 == 0.0) {
            return c1.equals(c2) ? 1 : -1;
        }

        deltaX = c1.getX() - c2.getX();
        deltaY = c1.getY() - c1.getY();
        distance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY)) - (r1 + r2);

        return distance > 0.0001 ? 1 : distance < -0.0001 ? -1 : 0;
    }

    private int checkCorrectParticleDistribution(final Point2D center, final double radius) {
        Particle curr;
        int checkedParticles = 0;

        while (checkedParticles < particles.size()) {
            curr = particles.get(checkedParticles);
            
            if (getBorderDistanceBetweenCircles(curr.getCenter(), curr.getRadius(), 
                center, radius) <= 0.0001) {
                break;
            }

            checkedParticles++;
        }

        return checkedParticles;
    }

    public ArrayList<Particle> getAllParticles() {
        return particles;
    }

    public double getBiggestRadius() {
        return secondBiggestRadius;
    }

    public void setBiggestRadius(double biggestRadius) {
        this.biggestRadius = biggestRadius;
    }

    public double getSecondBiggestRadius() {
        return secondBiggestRadius;
    }

    public void setSecondBiggestRadius(double secondBiggestRadius) {
        this.secondBiggestRadius = secondBiggestRadius;
    }
}
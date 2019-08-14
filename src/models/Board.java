package models;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Collection;

import engine.CIMInput;

public class Board {
    private int l;
    private int m; 
    private double cellSize;
    private Cell[][] matrix;
    public static final double maxBoardSize = 30.0; // TODO: Redefine

    public Board(final CIMInput input, final double biggestRadius,
        final double secondBiggestRadius) {
        int l = input.getL(), m = input.getM();

        if (l <= 0 || m <= 0) {
            throw new IllegalArgumentException("The field and board size should be positive");
        }
        
        this.setL(l);
        this.setM(m);
        this.setCellSize(biggestRadius, secondBiggestRadius, input.getR());
        this.setMatrix();
    }
    
    public int getL() {
        return l;
    }

    private void setL(final int l) {
        this.l = l;
    }

    public int getM() {
        return m;
    }

    private void setM(final int m) {
        this.m = m;
    }

    public double getCellSize() {
        return cellSize;
    }

    private void setCellSize(final double biggestRadius, final double secondBiggesRadius,
        final double rC) {
        double influenceRadius = biggestRadius + secondBiggesRadius + rC;

        cellSize = (l / m) > influenceRadius ? (l / m) : influenceRadius;
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    private void setMatrix() {
        this.matrix = new Cell[m][m];
    }


    public void populate(final ParticleFactory pf) {
        Point cellIndex;
        int i, j;

        for (Particle p : pf.getAllParticles()) {
            cellIndex = getCellIndexFromCoordinate(p.getCenter());
            i         = (int) cellIndex.getX();
            j         = (int) cellIndex.getY();
            matrix[i][j].addParticle(p);
        }
    }

    private int roundDouble(final double d) {
        int intD = (int) d;

        return (double) intD + 0.5 < d ? intD : intD + 1;
    }

    private int translateCoordToIndex(double d) {
        d = d / cellSize;
        return roundDouble(d) - 1;
    }

    public Point getCellIndexFromCoordinate(final Point2D coord) {
        int x = translateCoordToIndex(coord.getX());
        int y = translateCoordToIndex(coord.getY());
        
        return new Point(x, y);
    }

    public Collection<Particle> getParticlesFromCell(final Point2D coord) {
        int i = (int) coord.getX();
        int j = (int) coord.getY();

        return matrix[i][j].getParticles();
    }
}
package models;

import java.util.LinkedList;

public class Board {
    private int l; // Board size: L x L
    private int m; // Field size: M x M
    private double cellSize;
    private LinkedList<Particle>[][] matrix;
    public static final double maxBoardSize = 30.0; // TODO: Redefine

    public Board(final int l, final int m) {
        if (l <= 0 || m <= 0) {
            throw new IllegalArgumentException("The field and board size should be positive");
        }
        
        this.setL(l);
        this.setM(m);
        this.setCellSize();
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

    private void setCellSize() {
        this.cellSize = l/m; // TODO: Take into account the interaction radius
    }

    public LinkedList<Particle>[][] getMatrix() {
        return matrix;
    }

    private void setMatrix() {
        // TODO: Check the warning about the cast type
        this.matrix = new LinkedList[m][m];
    }
}
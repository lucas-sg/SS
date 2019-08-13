package models;

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
        double cellSize = l / m;
        double influenceRadius = biggestRadius + secondBiggesRadius + rC;

        if (cellSize < influenceRadius) {
            cellSize = influenceRadius;
        }
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    private void setMatrix() {
        this.matrix = new Cell[m][m];
    }
}
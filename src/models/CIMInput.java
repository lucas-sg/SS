package models;

public class CIMInput {
    private int n;
    private int l;
    private int m;
    private double rC; // Interaction radius for each particle

    public CIMInput(final int n, final int l, final int m, final double rC) {
        this.setN(n);
        this.setL(l);
        this.setM(m);
        this.setRC(rC);
    }

    public int getN() {
        return n;
    }

    private void setN(final int n) {
        this.n = n;
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

    public double getRC() {
        return rC;
    }

    private void setRC(final double rC) {
        this.rC = rC;
    }
}
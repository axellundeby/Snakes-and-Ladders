package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;

public final class Circle {
    private final CellPosition center;
    private final int radius;

    public Circle(CellPosition center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean contains(CellPosition pos) {
        int dx = pos.col() - center.col();
        int dy = pos.row() - center.row();
        return dx*dx + dy*dy <= radius*radius;
    }

    public CellPosition getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }
}
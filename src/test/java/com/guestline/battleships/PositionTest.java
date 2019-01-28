package com.guestline.battleships;

import static org.junit.Assert.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import org.junit.Test;

public class PositionTest {
    
    @Test
    public void testGetNeighbours() {
        Position topLeftCorner = new Position(0, 0);
        Position bottomRightCorner = new Position(9, 9);
        Position leftSide = new Position(1, 0);
        Position rightSide = new Position(9, 1);
        Position topSide = new Position(0, 1);
        Position bottomSide = new Position(9, 1);
        Position middle = new Position(1, 1);
        
        assertThat(topLeftCorner.getNeighbours(), hasSize(3));
        assertThat(bottomRightCorner.getNeighbours(), hasSize(3));
        assertThat(leftSide.getNeighbours(), hasSize(5));
        assertThat(rightSide.getNeighbours(), hasSize(5));
        assertThat(topSide.getNeighbours(), hasSize(5));
        assertThat(bottomSide.getNeighbours(), hasSize(5));
        assertThat(middle.getNeighbours(), hasSize(8));
    }

    @Test
    public void testGetImmediateNeighbours() {
        Position topLeftCorner = new Position(0, 0);
        Position bottomRightCorner = new Position(9, 9);
        Position leftSide = new Position(1, 0);
        Position rightSide = new Position(9, 1);
        Position topSide = new Position(0, 1);
        Position bottomSide = new Position(9, 1);
        Position middle = new Position(1, 1);
        
        assertThat(topLeftCorner.getImmediateNeighbours(), hasSize(2));
        assertThat(bottomRightCorner.getImmediateNeighbours(), hasSize(2));
        assertThat(leftSide.getImmediateNeighbours(), hasSize(3));
        assertThat(rightSide.getImmediateNeighbours(), hasSize(3));
        assertThat(topSide.getImmediateNeighbours(), hasSize(3));
        assertThat(bottomSide.getImmediateNeighbours(), hasSize(3));
        assertThat(middle.getImmediateNeighbours(), hasSize(4));
    }
    
}

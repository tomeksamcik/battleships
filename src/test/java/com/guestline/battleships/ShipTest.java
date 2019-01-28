package com.guestline.battleships;

import static org.junit.Assert.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.Test;

public class ShipTest {
    
    private Board board = new Board();
    
    @Test
    public void testInitialize() {
        Ship ship = new Ship(1, 5, "Test Ship"); 
        
        ship.initialize(board);
        
        assertThat(ship.getOccupied(), hasSize(5));
    }
    
    @Test
    public void testIsHit() {
        Ship ship = new Ship(1, 5, "Test Ship"); 
        
        ship.initialize(board);
        board.hitOrMiss(ship.getOccupied().get(0));
        
        assertThat(ship.isHit(board), equalTo(true));
        assertThat(ship.isAfloat(board), equalTo(true));
    }
    
    @Test
    public void testIsAfloat() {
        Ship ship = new Ship(1, 5, "Test Ship"); 
        
        ship.initialize(board);
        ship.getOccupied().forEach(o -> {
            board.hitOrMiss(o);            
        });
        
        assertThat(ship.isHit(board), equalTo(true));
        assertThat(ship.isAfloat(board), equalTo(false));
    }

}

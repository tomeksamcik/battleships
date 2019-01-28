package com.guestline.battleships;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Field {
    
    protected static final String OCCUPIED = "O";
    
    protected static final String MISSED = "x";
    
    protected static final String EMPTY = ".";
    
    protected static final String HIT = "X";
    
    @Getter
    private final boolean occupied;
    
    @Getter
    @Setter
    private boolean hit;

    @Getter
    @Setter
    private boolean missed;
    
    @Getter
    private int id;

    public Field(boolean occupied, boolean hit, boolean missed, int id) {
        this.occupied = occupied;
        this.missed = missed;
        this.hit = hit;
        this.id = id;
    }
    
    public String toString() {
        return toString(false);
    }
    
    public String toString(boolean debug) {
        if (occupied) {
            if (hit) {
                return HIT;
            } else {
                return OCCUPIED;
            }
        } else {
            if (missed) {
                return MISSED;
            } else {
                if (debug) {
                    if (id != 0) {
                        return String.valueOf(id);
                    }                
                } else {
                    return EMPTY;
                }                
            }
        }
        return EMPTY;
    }

}

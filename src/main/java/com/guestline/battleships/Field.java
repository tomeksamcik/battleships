package com.guestline.battleships;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Field {

    protected static final String OCCUPIED = "O";

    protected static final String MISSED = "+";

    protected static final String EMPTY = ".";
    
    protected static final String SUNK = "#";

    protected static final String HIT = "#";

    @Getter
    @Setter
    private boolean occupied;

    @Getter
    @Setter
    private boolean missed;

    @Getter
    @Setter
    private boolean sunk;
    
    @Getter
    @Setter
    private boolean hit;

    @Getter
    private int id;

    public Field(final boolean occupied, final boolean hit,
            final boolean missed, final boolean sunk, final int id) {
        this.occupied = occupied;
        this.missed = missed;
        this.sunk = sunk;
        this.hit = hit;
        this.id = id;
    }

    public final String toString() {
        return toString(false);
    }

    public final String toString(final boolean debug) {
        if (occupied) {
            if (hit) {
                return HIT;
            } else {
                if (debug) {
                    return OCCUPIED;
                } else {
                    return EMPTY;
                }
            }
        } else {
            if (missed) {
                return MISSED;
            } else if (sunk) {
                return SUNK;
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

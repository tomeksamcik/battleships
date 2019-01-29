package com.guestline.battleships;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public final class Field {

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

    private Field(final boolean occupied, final boolean missed,
            final boolean sunk, final boolean hit, final int id) {
        this.occupied = occupied;
        this.missed = missed;
        this.sunk = sunk;
        this.hit = hit;
        this.id = id;
    }

    public String toString() {
        return toString(false);
    }

    public String toString(final boolean debug) {
        return occupied ? (hit ? HIT : (debug ? OCCUPIED : EMPTY))
                : (missed ? MISSED
                        : (sunk ? SUNK
                                : (debug ? (id != 0 ? String.valueOf(id)
                                        : EMPTY) : EMPTY)));
    }

}

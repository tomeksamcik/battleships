package com.guestline.battleships;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Position {

    @Getter
    private int row;

    @Getter
    private int column;

    Position(final String input) {
        column = input.charAt(0) - Board.ASCII_A;
        row = Integer.parseInt(input.substring(1)) - 1;
    }

    public final String toOrdinalString() throws UnsupportedEncodingException {
        return new String(new byte[] { (byte) (column + Board.ASCII_A) },
                StandardCharsets.UTF_8.name()) + (row + 1);
    }

    public final List<Position> getNeighbours() {
        List<Position> neighbours = new LinkedList<>();
        for (int i = (row - 1 >= 0 ? row - 1 : row); i <= (row + 1 < Board.ROWS
                ? row + 1
                : row); i++) {
            for (int j = (column - 1 >= 0 ? column - 1
                    : column); j <= (column + 1 < Board.COLUMNS ? column + 1
                            : column); j++) {
                if (!(i == row && j == column)) {
                    neighbours.add(new Position(i, j));
                }
            }
        }
        return neighbours;
    }

    public final List<Position> getImmediateNeighbours() {
        List<Position> neighbours = new LinkedList<>();
        for (int i = (row - 1 >= 0 ? row - 1 : row); i <= (row + 1 < Board.ROWS
                ? row + 1
                : row); i++) {
            for (int j = (column - 1 >= 0 ? column - 1
                    : column); j <= (column + 1 < Board.COLUMNS ? column + 1
                            : column); j++) {
                if (!(i == row && j == column) && !(i != row && j != column)) {
                    neighbours.add(new Position(i, j));
                }
            }
        }
        return neighbours;
    }
}

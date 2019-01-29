package com.guestline.battleships;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AccessLevel;
import lombok.Getter;

public class Board {

    private final Random rand = new Random();

    public static final int ROWS = 10;

    public static final int COLUMNS = 10;

    public static final int ASCII_A = 97;

    private static final int GET_POSITION_ATTEMPTS_THRESHOLD = 10;

    @Getter(AccessLevel.PROTECTED)
    private Field[][] fields = new Field[ROWS][COLUMNS];

    Board() {
        initialize();
    }

    private void initialize() {
        IntStream.range(0, ROWS).forEach(i -> {
            IntStream.range(0, COLUMNS).forEach(j -> {
                fields[i][j] = Field.builder().build();
            });
        });
    }

    public final void print() {
        print(false);
    }

    public final void print(final boolean debug) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(-1, fields.length).forEach(i -> {
            if (i == -1) {
                sb.append("  ");
                IntStream.range(0, fields.length)
                        .forEach(j -> sb.append((char) (j + ASCII_A) + " "));
            } else {
                sb.append((i + 1) + (i != fields.length - 1 ? " " : ""));
                IntStream.range(0, fields[i].length).forEach(j -> {
                    sb.append(fields[i][j].toString(debug) + " ");
                });
            }
            sb.append(System.lineSeparator());
        });
        System.out.println(sb);
    }

    public final List<Position> getNext(final int id, final Position position) {
        return position.getImmediateNeighbours().stream()
                .filter(p -> isValid(id, p))
                .filter(p -> p.getRow() >= position.getRow())
                .filter(p -> p.getColumn() >= position.getColumn())
                .collect(Collectors.toList());
    }

    public final Position markOccupied(final int id, final Position position) {
        fields[position.getRow()][position.getColumn()] = Field.builder()
                .occupied(true).id(id).build();
        return position;
    }

    public final List<Position> markZone(final int id,
            final Position position) {
        List<Position> zone = position.getNeighbours().stream()
                .filter(p -> !fields[p.getRow()][p.getColumn()].isOccupied())
                .filter(p -> fields[p.getRow()][p.getColumn()].getId() == 0)
                .collect(Collectors.toList());
        zone.forEach(p -> fields[p.getRow()][p.getColumn()] = Field.builder()
                .id(id).build());
        return zone;
    }

    public final Optional<Position> getRandomPosition(final int id,
            final int size) {
        int row, column, attempts = 0;
        do {
            row = rand.nextInt(ROWS);
            column = rand.nextInt(COLUMNS);
            if (++attempts >= GET_POSITION_ATTEMPTS_THRESHOLD) {
                return Optional.empty();
            }
        } while (!isEnoughSpace(row, column, size)
                || !isValid(id, new Position(row, column)));
        return Optional.of(new Position(row, column));
    }

    public final boolean isEnoughSpace(final int row, final int column,
            final int size) {
        return ROWS - 1 - row + COLUMNS - 1 - column >= size - 1;
    }

    public final Field hitOrMiss(final Position position) {
        Field target = fields[position.getRow()][position.getColumn()];
        if (target.isOccupied()) {
            target.setHit(true);
        } else {
            target.setMissed(true);
        }
        return target;
    }

    private boolean isValid(final int id, final Position p) {
        return !fields[p.getRow()][p.getColumn()].isOccupied()
                && (fields[p.getRow()][p.getColumn()].getId() == 0
                        || fields[p.getRow()][p.getColumn()].getId() == id);
    }

}

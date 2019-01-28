package com.guestline.battleships;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Getter;

public class Board {

    private final Random rand = new Random();

    public static final int ROWS = 10;

    public static final int COLUMNS = 10;

    @Getter
    private Field[][] fields = new Field[ROWS][COLUMNS];

    public Board() {
        initialize();
    }

    private void initialize() {
        IntStream.range(0, ROWS).forEach(i -> {
            IntStream.range(0, COLUMNS).forEach(j -> {
                fields[i][j] = Field.builder().build();
            });
        });
    }

    public void print() {
        print(false);
    }

    public void print(boolean debug) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(-1, fields.length).forEach(i -> {
            if (i == -1) {
                sb.append("  ");
                IntStream.range(0, fields.length).forEach(j -> sb.append((char) (j + 97) + " "));
            } else {
                sb.append((i + 1) + (i != 9 ? " " : ""));
                IntStream.range(0, fields[i].length).forEach(j -> {
                    sb.append(fields[i][j].toString(debug) + " ");
                });
            }
            sb.append(System.lineSeparator());
        });
        System.out.println(sb);
    }

    public List<Position> getNext(int id, Position position) {
        return position.getImmediateNeighbours().stream().filter(p -> isValid(id, p))
                .filter(p -> p.getRow() >= position.getRow()).filter(p -> p.getColumn() >= position.getColumn())
                .collect(Collectors.toList());
    }

    public Position markOccupied(int id, Position position) {
        fields[position.getRow()][position.getColumn()] = Field.builder().occupied(true).id(id).build();
        return position;
    }

    public List<Position> markZone(int id, Position position) {
        List<Position> zoned = position.getNeighbours().stream()
                .filter(p -> !fields[p.getRow()][p.getColumn()].isOccupied())
                .filter(p -> fields[p.getRow()][p.getColumn()].getId() == 0).collect(Collectors.toList());
        zoned.forEach(p -> fields[p.getRow()][p.getColumn()] = Field.builder().id(id).build());
        return zoned;
    }

    public Position getRandomPosition(int id, int size) {
        int row, column = -1;
        int attempts = 0;
        do {
            row = rand.nextInt(ROWS);
            column = rand.nextInt(COLUMNS);
            if (++attempts >= 10) {
                return new Position(-1, -1);
            }
        } while (!isEnoughSpace(row, column, size) || !isValid(id, new Position(row, column)));
        return new Position(row, column);
    }

    public boolean isEnoughSpace(int row, int column, int size) {
        return ROWS - 1 - row + COLUMNS - 1 - column >= size - 1;
    }

    public Field hitOrMiss(Position position) {
        Field target = fields[position.getRow()][position.getColumn()];
        if (target.isOccupied()) {
            target.setHit(true);
        } else {
            target.setMissed(true);
        }
        return target;
    }

    private boolean isValid(int id, Position p) {
        return !fields[p.getRow()][p.getColumn()].isOccupied()
                && (fields[p.getRow()][p.getColumn()].getId() == 0 || fields[p.getRow()][p.getColumn()].getId() == id);
    }

}

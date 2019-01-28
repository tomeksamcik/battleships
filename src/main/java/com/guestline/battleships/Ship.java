package com.guestline.battleships;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ship {

    @Getter
    @EqualsAndHashCode.Include
    private final int id;

    private final int size;

    @Getter
    private final String name;

    private final Random rand = new Random();

    @Getter
    private List<Position> occupied = new ArrayList<Position>();

    public Ship(final int id, final int size, final String name) {
        this.id = id;
        this.size = size;
        this.name = name;
    }

    public final void initialize(final Board board) {
        List<Position> occupied = new ArrayList<Position>();
        List<Position> zoned = new ArrayList<Position>();
        while (occupied.size() < size) {
            Optional<Position> position = Optional
                    .of(board.getRandomPosition(id, size));
            while (occupied.size() < size && position.isPresent()) {
                occupied.add(board.markOccupied(id, position.get()));
                zoned.addAll(board.markZone(id, position.get()));
                List<Position> next = board.getNext(id, position.get());
                position = !next.isEmpty()
                        ? Optional.of(next.get(rand.nextInt(next.size())))
                        : Optional.empty();
            }
            if (occupied.size() < size) {
                rollback(board, occupied, zoned);
                occupied.clear();
                zoned.clear();
            }
        }
        this.occupied = occupied;
    }

    public final boolean isAfloat(final Board board) {
        return !occupied.stream().allMatch(
                p -> board.getFields()[p.getRow()][p.getColumn()].isHit());
    }

    public final boolean isHit(final Board board) {
        return occupied.stream().anyMatch(
                p -> board.getFields()[p.getRow()][p.getColumn()].isHit());
    }

    private void rollback(final Board board,
            final List<Position> occupied, final List<Position> zoned) {
        occupied.forEach(
                p -> board.getFields()[p.getRow()][p.getColumn()] = Field
                        .builder().occupied(false).build());
        zoned.forEach(p -> board.getFields()[p.getRow()][p.getColumn()] = Field
                .builder().occupied(false).build());
    }

}

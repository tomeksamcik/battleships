package com.guestline.battleships;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;

public class BoardTest {

    @Test
    public void testIsEnoughSpace() {
        Board board = new Board();

        assertFalse(board.isEnoughSpace(9, 6, 5));
        assertFalse(board.isEnoughSpace(8, 7, 5));
        assertFalse(board.isEnoughSpace(7, 8, 5));
        assertTrue(board.isEnoughSpace(9, 5, 5));
        assertTrue(board.isEnoughSpace(7, 7, 5));
        assertTrue(board.isEnoughSpace(8, 6, 5));
        assertTrue(board.isEnoughSpace(6, 8, 5));
    }

    @Test
    public void testHitOrMiss() {
        Board board = new Board();
        board.getFields()[0][0] = Field.builder().occupied(true).zoneId(1).build();

        Field hit = board.hitOrMiss(new Position(0, 0));
        Field miss = board.hitOrMiss(new Position(1, 1));

        assertThat(hit.isHit(), equalTo(true));
        assertThat(hit.isOccupied(), equalTo(true));
        assertThat(hit.isMissed(), equalTo(false));
        assertThat(miss.isHit(), equalTo(false));
        assertThat(miss.isOccupied(), equalTo(false));
        assertThat(miss.isMissed(), equalTo(true));
    }

    @Test
    public void testGetRandomPositionAllOccupied() {
        Board board = new Board();
        IntStream.range(0, 10).forEach(i -> {
            IntStream.range(0, 10).forEach(j -> {
                board.getFields()[i][j] = Field.builder().occupied(true)
                        .build();
            });
        });

        Optional<Position> position = board.getRandomPosition(1, 5);

        assertThat(position.isPresent(), equalTo(false));
    }

    @Test
    public void testGetRandomPositionAllZonedEnemy() {
        Board board = new Board();
        IntStream.range(0, 10).forEach(i -> {
            IntStream.range(0, 10).forEach(j -> {
                board.getFields()[i][j] = Field.builder().zoneId(2).build();
            });
        });

        Optional<Position> position = board.getRandomPosition(1, 5);

        assertThat(position.isPresent(), equalTo(false));
    }

    @Test
    public void testGetRandomPositionAllZonedAlly() {
        Board board = new Board();
        IntStream.range(0, 10).forEach(i -> {
            IntStream.range(0, 10).forEach(j -> {
                board.getFields()[i][j] = Field.builder().zoneId(1).build();
            });
        });

        Optional<Position> position = board.getRandomPosition(1, 5);

        assertThat(position.isPresent(), equalTo(true));
    }

    @Test
    public void testMarkZoned() {
        Board board = new Board();
        IntStream.range(0, 5).forEach(i -> {
            IntStream.range(0, 10).forEach(j -> {
                board.getFields()[i][j] = Field.builder().zoneId(1).build();
            });
        });

        List<Position> zone1 = getZone(board, 2, new Position(5, 0));
        List<Position> zone2 = getZone(board, 3, new Position(5, 4));
        List<Position> zone3 = getZone(board, 4, new Position(5, 2));
        List<Position> zone4 = getZone(board, 5, new Position(8, 8));
        board.print(true);

        assertThat(zone1, hasSize(3));
        assertThat(zone2, hasSize(5));
        assertThat(zone3, hasSize(1));
        assertThat(zone4, hasSize(8));
    }

    private List<Position> getZone(Board board, int id, Position position) {
        board.getFields()[position.getRow()][position.getColumn()] = Field
                .builder().occupied(true).zoneId(id).build();
        return board.markZone(id, position);
    }

    @Test
    public void testMarkOccupied() {
        Board board = new Board();
        board.markOccupied(1, new Position(0, 0));

        assertThat(board.getFields()[0][0].isOccupied(), equalTo(true));
    }

    @Test
    public void testGetNext() {
        Board board = new Board();
        IntStream.range(0, 5).forEach(i -> {
            IntStream.range(0, 10).forEach(j -> {
                board.getFields()[i][j] = Field.builder().zoneId(1).build();
            });
        });

        getZone(board, 2, new Position(5, 0));
        getZone(board, 3, new Position(5, 4));
        getZone(board, 4, new Position(5, 2));
        getZone(board, 5, new Position(8, 8));
        board.print(true);

        List<Position> next = board.getNext(4, new Position(5, 2));

        assertThat(next, hasSize(1));
        assertThat(next.get(0), equalTo(new Position(6, 2)));
    }

}

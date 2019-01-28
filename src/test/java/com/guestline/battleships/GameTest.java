package com.guestline.battleships;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.stream.IntStream;

import org.junit.Test;

public class GameTest {
    
    @Test
    public void test() {
        String input = generateInput();
        Game game = new Game(new Board(), new UserInput(
                new Scanner(new ByteArrayInputStream(input.getBytes()))));
        game.addShip(new Ship(1, 5, "Battleship"));
        game.addShip(new Ship(2, 4, "Destroyer"));
        game.addShip(new Ship(3, 4, "Destroyer"));
        game.play();
        
        assertThat(game.isGameOver(), equalTo(true));
    }
    
    private String generateInput() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, Board.ROWS).forEach(i -> {
            IntStream.range(0, Board.COLUMNS).forEach(j -> {
                sb.append(new Position(i, j).toOrdinalString());
                sb.append(" ");
            });
        });
        return sb.toString();
    }

}

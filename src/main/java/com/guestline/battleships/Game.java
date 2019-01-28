package com.guestline.battleships;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import lombok.Getter;

public class Game {

    private final List<Ship> ships = new ArrayList<Ship>();

    private final List<Ship> sunken = new ArrayList<Ship>();

    @Getter
    private final Board board;

    private final UserInput userInput;

    public Game(final Board board, final UserInput userInput) {
        this.board = board;
        this.userInput = userInput;
    }

    public final void addShip(final Ship ship) {
        ships.add(ship);
        ship.initialize(board);
    }

    public final boolean isGameOver() {
        return ships.stream().allMatch(s -> !s.isAfloat(board));
    }

    public final void play() {
        while (!isGameOver()) {
            board.print();
            Optional<Position> position = userInput.nextMove();
            if (position.isPresent()) {
                Field target = board.hitOrMiss(position.get());
                if (target.isHit()) {
                    System.out.println("You hit an enemy ship !");
                    List<Ship> notAfloat = ships.stream()
                            .filter(s -> !s.isAfloat(board))
                            .collect(Collectors.toList());
                    notAfloat.forEach(s -> {
                        if (!sunken.contains(s)) {
                            System.out.println(String.format(
                                    "Enemy %s has sunk !", s.getName()));
                        }
                        sunken.add(s);
                    });
                }
                if (target.isMissed()) {
                    System.out.println("You missed :(");
                }
            } else {
                break;
            }
            System.out.println();
        }
        System.out.println("All enemy ships have sunk. Game Over.");
    }

    public static void main(final String... args) {
        Game game = new Game(new Board(),
                new UserInput(new Scanner(System.in, StandardCharsets.UTF_8.name())));
        game.addShip(new Ship(1, 5, "Battleship"));
        game.addShip(new Ship(2, 4, "Destroyer"));
        game.addShip(new Ship(3, 4, "Destroyer"));
        game.play();
    }

}

package com.guestline.battleships;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInput {

    private final Scanner in;

    private final static Pattern PATTERN = Pattern.compile("[a-jA-J](?:[0-9]{1}|10)");

    private final static String INPUT_ERROR = "Incorrect Input !";

    private final static String INPUT_PROMPT = "Please enter your move (ex. a7): ";

    public UserInput(Scanner in) {
        this.in = in;
    }

    public Optional<Position> nextMove() {
        System.out.print(INPUT_PROMPT);

        while (!in.hasNext(PATTERN)) {
            if (in.hasNext()) {
                in.next();
                System.out.println(INPUT_ERROR + System.lineSeparator() + INPUT_PROMPT);
            } else {
                return Optional.empty();
            }
        }

        return Optional.of(new Position(in.next(PATTERN).toLowerCase()));
    }

}

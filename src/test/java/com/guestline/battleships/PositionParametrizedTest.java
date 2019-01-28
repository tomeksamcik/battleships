package com.guestline.battleships;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PositionParametrizedTest {

    private String fInput;

    private Position fExpected;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { 
                { "a1", new Position(0, 0) },
                { "j10", new Position(9, 9) },
                { "j11", new Position(10, 9) }, });
    }

    public PositionParametrizedTest(String input, Position expected) {
        this.fInput = input;
        this.fExpected = expected;
    }

    @Test
    public void test() {
        assertThat(fInput, equalTo(fExpected.toOrdinalString()));
    }

}

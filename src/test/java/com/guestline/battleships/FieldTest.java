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
public class FieldTest {

    private String fInput;

    private Field fExpected;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { Field.OCCUPIED, Field.builder().occupied(true).build() },
                { Field.OCCUPIED,
                        Field.builder().occupied(true).id(1).build() },
                { Field.HIT,
                        Field.builder().occupied(true).hit(true).id(1)
                                .build() },
                { Field.HIT, Field.builder().sunk(true).id(1).build() },
                { Field.MISSED,
                        Field.builder().sunk(true).missed(true).id(1).build() },
                { "1", Field.builder().id(1).build() },
                { Field.MISSED, Field.builder().missed(true).id(1).build() },
                { Field.EMPTY, Field.builder().build() }, });
    }

    public FieldTest(String input, Field expected) {
        this.fInput = input;
        this.fExpected = expected;
    }

    @Test
    public void test() {
        System.out.println(fInput + " == " + fExpected.toString(true) + " hit: " + fExpected.isHit() + ", missed: " + fExpected.isMissed() + ", occupied: " + fExpected.isOccupied() + ", sunk: " + fExpected.isSunk());
        assertThat(fInput, equalTo(fExpected.toString(true)));
    }

}

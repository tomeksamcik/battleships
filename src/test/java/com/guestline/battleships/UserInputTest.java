package com.guestline.battleships;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class UserInputTest {
    
    private String fInput;

    private Optional<Position> fExpected;    
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { "a1", Optional.of(new Position(0, 0)) }, 
                 { "A1", Optional.of(new Position(0, 0)) }, 
                 { "j10", Optional.of(new Position(9, 9)) }, 
                 { "j11", Optional.empty() },
                 { "sss", Optional.empty() },
                 { "ssss xxx a1", Optional.of(new Position(0, 0)) }  
           });
    }
    
    public UserInputTest(String input, Optional<Position> expected){
        this.fInput = input;
        this.fExpected = expected;
    }    
    
    @Test
    public void test() {
        assertThat(getUserInput(fInput).nextMove(), equalTo(fExpected));
    }
    
    private UserInput getUserInput(String input) {;
        return new UserInput(new Scanner(new ByteArrayInputStream(input.getBytes())));
    }
  
}

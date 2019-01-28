package com.guestline.battleships;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BoardTest.class, FieldTest.class,
        PositionParametrizedTest.class, PositionTest.class, ShipTest.class,
        UserInputTest.class, GameTest.class })
public class AllTests {

}

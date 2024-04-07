package org.local.diceroll.dice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class DiceTest {
    static Logger log = Logger.getLogger(DiceTest.class.getName());

//    usefully annotation: @BeforeAll, @BeforeEach, @AfterEach, @AfterAll

    @DisplayName("test One six sided die")
    @Test
    void test1d6() {
        DicePool pool = new DicePool(6, 0);
        int value = pool.roll().value();
        log.info(pool.toString());
        assertEquals(0, (value - 1) / 6);
        assumeTrue(value >= 1);
        assumeTrue(value <= 6);
        log.info("Success");
    }

    @Test
        //@Disabled("Not implemented yet")
    void testPool() {
        DicePool pool = new DicePool("2d  20+3d 6-4");
        int value = pool.roll().value();
        log.info(pool.toString());
        assertTrue(value > 1 && value < 54);
    }

    @Test
    void testEmptyPool() {
        DicePool pool = new DicePool("");
        int value = pool.roll().value();
        assertEquals(0, value);
    }

    @Test
    void testNullPool() {
        DicePool pool = new DicePool(null);
        int value = pool.roll().value();
        assertEquals(0, value);
    }

}

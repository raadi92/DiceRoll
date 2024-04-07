package org.local.diceroll.dice;

import java.util.Random;

public class Die {
    private final int face;
    private final int sign;
    private static final Random seed = new Random(System.currentTimeMillis());
    private int result;

    private Die(int face, int sign) {
        this.face = face;
        this.sign = sign;
    }

    public static Die newDie(int face) {
        return new Die(face, 1);
    }

    public static Die newDie(int face, int sign) {
        return new Die(face, sign);
    }

    public Die roll() {
        this.result = seed.nextInt(face) + 1;
        return this;
    }

    public int value() {
        return this.sign * this.result;
    }

    public int faces() {
        return this.face;
    }
}

package org.local.diceroll.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DieDefinition {
    protected int sign = 1;
    protected int numberOfDice;
    protected boolean isDice;
    protected int numberOfFaces;

    private static final String DICE_POOL_REGEX = "([+-])?(\\d+)([dD])?(\\d+)?";
    private static final String DICE_ID = "D";

    private DieDefinition() {
    }

    /**
     * @param pool identify the dice pool needed
     *             <p>
     *             explain the regex:
     *             ([+-])?   OPTIONAL - sign of the Die / Modifier
     *             (\d+)                sequence of digit modifier or dice number
     *             [dD]?     OPTIONAL - d or D character, identify a Die
     *             (\d+)?    OPTIONAL - sequence of digit (die faces)
     */
    public static List<DieDefinition> parseDicePool(String pool) {

        List<DieDefinition> diceDef = new ArrayList<>();

        if (pool != null) {
            Pattern pattern = Pattern.compile(DICE_POOL_REGEX);
            Matcher matcher = pattern.matcher(pool.replaceAll("\\s+", ""));

            while (matcher.find()) {
                DieDefinition die = new DieDefinition();
                die.sign = getSign(matcher);
                die.numberOfDice = Integer.parseInt(matcher.group(2));
                String d = matcher.group(3);
                die.isDice = (d != null && d.equalsIgnoreCase(DICE_ID));
                if (die.isDice) {
                    die.numberOfFaces = Integer.parseInt(matcher.group(4));
                }

                diceDef.add(die);
            }
        }

        return diceDef;
    }

    private static int getSign(Matcher matcher) {
        return (matcher.group(1) == null || matcher.group(1).equals("+")) ? 1 : -1;
    }

}

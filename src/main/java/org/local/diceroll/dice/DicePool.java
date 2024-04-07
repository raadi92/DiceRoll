package org.local.diceroll.dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DicePool {
    private List<Die> dice;
    private int modifier;
    private int result;

    public DicePool(int die, int modifier) {
        this.dice = Collections.singletonList(Die.newDie(die));
        this.modifier = modifier;
    }

    public DicePool(String pool) {
        this.dice = new ArrayList<>();
        this.modifier = 0;

        List<DieDefinition> diceDef = DieDefinition.parseDicePool(pool);
        diceDef.forEach(def -> {
            if (def.isDice) {
                addDiceDef(def);
            } else {
                this.modifier += def.sign * def.numberOfDice;
            }
        });

    }

    private void addDiceDef(DieDefinition definition) {
        for (int i=0; i < definition.numberOfDice; i++){
            this.dice.add(Die.newDie(definition.numberOfFaces, definition.sign));
        }
    }

    public DicePool roll() {
        int sum = 0;
        for (Die die : dice) {
            sum += die.roll().value();
        }
        this.result = sum + this.modifier;
        return this;
    }

    public int value() {
        return this.result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d { ", this.result));
        this.dice.forEach(die -> sb.append(String.format("%d(d%d) ", die.value(), die.faces())));
        sb.append("}");
        return sb.toString();
    }
}



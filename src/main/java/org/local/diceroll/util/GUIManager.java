package org.local.diceroll.util;

import java.util.UUID;

public class GUIManager {

    private GUIManager() {
    }

    public static String genereteUUID() {
        return UUID.randomUUID().toString();
    }
    
}

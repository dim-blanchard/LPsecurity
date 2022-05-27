package fr.loirelique.lpsecurity.List;

import java.util.HashMap;

/**
 * List pour l'autentification.
 */

public class ListWrongPasswordTentative {
    private static HashMap<String, Integer> wrongLoginPasswordTentative = new HashMap<String, Integer>();

    public static void setNewPlayer(String uuid) {
        wrongLoginPasswordTentative.put(uuid, 0);
    }

    public static void setRemovePlayer(String uuid) {
        wrongLoginPasswordTentative.remove(uuid);
    }

    public static int getNumberTentativeOfPlayer(String uuid) {
        int numberTentative = wrongLoginPasswordTentative.get(uuid);
        return numberTentative;
    }

    public static void incrementNumberTentativeOfPlayer(String uuid){
        int numberTentative = wrongLoginPasswordTentative.get(uuid);
        numberTentative++;
        wrongLoginPasswordTentative.put(uuid,numberTentative);
    }
}

package fr.loirelique.lpsecurity.Useful;

import java.util.HashMap;

public class ListUseful {
    private static HashMap<String, Integer> wrongLoginPasswordTentative = new HashMap<String, Integer>();


    public static void name(String uuid, int tentative) {
        if (wrongLoginPasswordTentative.get(uuid) != null) {

            wrongLoginPasswordTentative.put(uuid, ++);
            System.out.println(wrongLoginPasswordTentative.get(uuid));
            if (wrongLoginPasswordTentative.get(uuid) > tentative) {
                Player player = Main.plugin.getListPlayer(uuid);
                player.kickPlayer("Trois tentative");

            }
        } else {
            wrongLoginPasswordTentative.put(uuid, i);
        }
    }


}

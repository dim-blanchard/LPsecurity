package fr.loirelique.lpsecurity.Useful.List;

import java.util.HashMap;

public class ListLoginPlayer {
    private static HashMap<String,String> listLoginPlayer = new HashMap<String,String>();

    public static void setLoginPlayer(String uuid) {
        if (listLoginPlayer.get(uuid)==null) {
            listLoginPlayer.put(uuid,"1");
        }
    }

    public static void removeLoginPlayer(String uuid) {
        if (listLoginPlayer.get(uuid)!= null) {
            listLoginPlayer.remove(uuid);
        }
    }

    public static String getLoginPlayer(String uuid) {
        String test = null;
        if (listLoginPlayer.get(uuid)!=null) {
            test=listLoginPlayer.get(uuid);
            return test;
        }
        return test;
    }
}

package fr.loirelique.lpsecurity.Useful.List;

import java.util.HashMap;

import fr.loirelique.lpsecurity.Main;

public class ListMutePlayer {
    private static HashMap<String, Integer> mutePlayer = new HashMap<String,Integer>();
    private static HashMap<String, String> mutePlayerMotif = new HashMap<String,String>();

    public static void setMutePlayer(String uuid) {
        if (Main.plugin.getListOnlinePlayer(uuid)!= null) {
               if (mutePlayer.get(uuid) == null) {
            mutePlayer.put(uuid, 1);
        }  
        }
         
    }

    public static int getMutePlayer(String uuid) {
        int mute = 0;
        if (mutePlayer.get(uuid)!=null) {
            mute = mutePlayer.get(uuid);
        }
        return mute;
    }

    public static void removeMutePlayer(String uuid) {
        if (mutePlayer.get(uuid)!= null) {
            mutePlayer.remove(uuid);
        }
    }

    public static void setMutePlayerMotif(String uuid , String motif){   
        if (Main.plugin.getListOnlinePlayer(uuid)!= null) {
            if (mutePlayerMotif.get(uuid) == null) {
         mutePlayerMotif.put(uuid, motif);
     }  
     }

    }

    public static String getMutePlayerMotif(String uuid) {
        String retourne ="null";
        if (mutePlayerMotif.get(uuid)!=null) {
            retourne = mutePlayerMotif.get(uuid);
        }
        return retourne;
    }

    public static void removeMutePlayerMotif(String uuid) {
        if (mutePlayerMotif.get(uuid)!= null) {
            mutePlayerMotif.remove(uuid);
        }
    }
}

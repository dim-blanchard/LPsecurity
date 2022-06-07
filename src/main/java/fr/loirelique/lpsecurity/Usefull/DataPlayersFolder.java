package fr.loirelique.lpsecurity.Usefull;

import java.io.File;

import fr.loirelique.lpsecurity.Main;

public class DataPlayersFolder {
    
    public static void create() {
        final String chemainFolder = Main.plugin.getDataFolder().toString();
        final String nameFolder = "dataPlayerUsefull";
        final File folder = new File(chemainFolder, nameFolder);
        if (folder.exists() == true) {
            System.out.println("[LPsecurity] Folder Data Players Exists.");
        } else {
            System.out.println("[LPsecurity] Folder Data Players Not Exists.");
            folder.mkdir();
            System.out.println("[LPsecurity] Folder Data Players Was Create.");
        }
    }
}

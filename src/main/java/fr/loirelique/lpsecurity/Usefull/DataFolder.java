package fr.loirelique.lpsecurity.Usefull;

import java.io.File;

import fr.loirelique.lpsecurity.Main;

public class DataFolder {
    
    public static void create(String nameFolder) {
        final String chemainFolder = Main.plugin.getDataFolder().toString();
        final File folder = new File(chemainFolder, nameFolder);
        if (folder.exists() == true) {
            System.out.println("[LPsecurity] Folder"+nameFolder+"Exists.");
        } else {
            System.out.println("[LPsecurity] Folder"+nameFolder+"Not Exists.");
            folder.mkdir();
            System.out.println("[LPsecurity] Folder"+nameFolder+" Was Create.");
        }
    }
}

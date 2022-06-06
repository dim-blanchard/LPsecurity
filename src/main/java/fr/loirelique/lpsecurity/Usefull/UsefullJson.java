package fr.loirelique.lpsecurity.Usefull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UsefullJson {
    private static String chemainFolder = "C:\\Users\\Master\\Desktop\\Erizia-spigot-1.8.8\\Spigot-1.8.8-serveur\\plugins\\LPsecurity";
    private static String chemainFiles = "C:\\Users\\Master\\Desktop\\Erizia-spigot-1.8.8\\Spigot-1.8.8-serveur\\plugins\\LPsecurity\\dataPlayerUsefull\\";
    private static String nameFile = "players.json";
    private static String nameFolder = "dataPlayerUsefull";
    String ban;
    String mute;
    String warn;
    String temp_ban;
    String motif_ban;
    String temp_mute;
    String motif_kick;
    String motif_mute;
    String motif_warn;
    String motif_unban;

    String motif_unmute;
    String motif_tempban;
    String motif_tempmute;
    String isOnline;
    String isLogin;
    // private static String chemainFiles =
    // "/home/padymaster/Documents/VisualStudioCode/Spigot-serveur-1.8.8/plugins/LPsecurity/dataPlayerUsefull/";
    // private static String chemainFolder =
    // "/home/padymaster/Documents/VisualStudioCode/Spigot-serveur-1.8.8/plugins/LPsecurity";

    public static void creatFolderAndVerifExists(String nameFolder, String chemain) {
        // Creation du dossier
        File folder = new File(
                chemain, nameFolder);
        if (folder.exists() == true) {
            System.out.println("Dossier Exsist...");
        } else {
            System.out.println("Dossier abs création..");
            folder.mkdir();
        }
        ////////////////////
    }

    public static void createFilesAndIfExisteUpdate(String ban, String mute, String warn, String temp_ban,
            String motif_ban,
            String temp_mute, String motif_kick, String motif_mute, String motif_warn, String motif_unban,
            String motif_unmute, String motif_tempban, String motif_tempmute, String isOnline, String isLogin,
            String chemain, String nameFile) {
        final File file = new File(chemain, nameFile);
        if (file.exists() == true) {
            System.out.println("Le fichier existe\nLe fichier est mit à jour");
            JSONObject dataPlayerUsefull = new JSONObject();
            dataPlayerUsefull.put("ban", ban);
            dataPlayerUsefull.put("mute", mute);
            dataPlayerUsefull.put("warn", warn);
            dataPlayerUsefull.put("temp_ban", temp_ban);
            dataPlayerUsefull.put("motif_ban", motif_ban);
            dataPlayerUsefull.put("temp_mute", temp_mute);
            dataPlayerUsefull.put("motif_kick", motif_kick);
            dataPlayerUsefull.put("motif_mute", motif_mute);
            dataPlayerUsefull.put("motif_warn", motif_warn);
            dataPlayerUsefull.put("motif_unban", motif_unban);
            dataPlayerUsefull.put("motif_unmute", motif_unmute);
            dataPlayerUsefull.put("motif_tempban", motif_tempban);
            dataPlayerUsefull.put("motif_tempmute", motif_tempmute);
            dataPlayerUsefull.put("isOnline", isOnline);
            dataPlayerUsefull.put("isLogin", isLogin);

            try (FileWriter fileW = new FileWriter(file)) {
                fileW.write(dataPlayerUsefull.toJSONString());
                fileW.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("création fichier");
            JSONObject dataPlayerUsefull = new JSONObject();
            dataPlayerUsefull.put("ban", ban);
            dataPlayerUsefull.put("mute", mute);
            dataPlayerUsefull.put("warn", warn);
            dataPlayerUsefull.put("temp_ban", temp_ban);
            dataPlayerUsefull.put("motif_ban", motif_ban);
            dataPlayerUsefull.put("temp_mute", temp_mute);
            dataPlayerUsefull.put("motif_kick", motif_kick);
            dataPlayerUsefull.put("motif_mute", motif_mute);
            dataPlayerUsefull.put("motif_warn", motif_warn);
            dataPlayerUsefull.put("motif_unban", motif_unban);
            dataPlayerUsefull.put("motif_unmute", motif_unmute);
            dataPlayerUsefull.put("motif_tempban", motif_tempban);
            dataPlayerUsefull.put("motif_tempmute", motif_tempmute);
            dataPlayerUsefull.put("isOnline", isOnline);
            dataPlayerUsefull.put("isLogin", isLogin);

            try (FileWriter fileW = new FileWriter(file)) {
                fileW.write(dataPlayerUsefull.toJSONString());
                fileW.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String readFiles(String key, String namefile, String chemain) {
        JSONParser parser = new JSONParser();

        Object obj;
        try {
            obj = parser.parse(new FileReader(chemain + namefile));
            JSONObject jsonObject = (JSONObject) obj;
            String data = (String) jsonObject.get(key);
            return data;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        } catch (org.json.simple.parser.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
    }

    public static void updateFileAndIsExiste(String ban, String mute, String warn, String temp_ban, String motif_ban,
            String temp_mute, String motif_kick, String motif_mute, String motif_warn, String motif_unban,
            String motif_unmute, String motif_tempban, String motif_tempmute, String isOnline, String isLogin,
            String chemain, String nameFile) {
        final File file = new File(chemain, nameFile);
        if (file.exists() == true) {
            System.out.println("Le fichier existe\nLe fichier est mit à jour");
            JSONObject dataPlayerUsefull = new JSONObject();
            dataPlayerUsefull.put("ban", ban);
            dataPlayerUsefull.put("mute", mute);
            dataPlayerUsefull.put("warn", warn);
            dataPlayerUsefull.put("temp_ban", temp_ban);
            dataPlayerUsefull.put("motif_ban", motif_ban);
            dataPlayerUsefull.put("temp_mute", temp_mute);
            dataPlayerUsefull.put("motif_kick", motif_kick);
            dataPlayerUsefull.put("motif_mute", motif_mute);
            dataPlayerUsefull.put("motif_warn", motif_warn);
            dataPlayerUsefull.put("motif_unban", motif_unban);
            dataPlayerUsefull.put("motif_unmute", motif_unmute);
            dataPlayerUsefull.put("motif_tempban", motif_tempban);
            dataPlayerUsefull.put("motif_tempmute", motif_tempmute);
            dataPlayerUsefull.put("isOnline", isOnline);
            dataPlayerUsefull.put("isLogin", isLogin);

            try (FileWriter fileW = new FileWriter(file)) {
                fileW.write(dataPlayerUsefull.toJSONString());
                fileW.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Le fichier n'existe pas.");
        }
    }

/*     public static void main(String args[]) {

        // if le fichier du joueur existe on le cree sinon non
        creatFolderAndVerifExists(nameFolder, chemainFolder);
        createFilesAndIfExisteUpdate("ban", "mute", "warn", "temp_ban", "motif_ban", "temp_mute", "motif_kick",
                "motif_mute",
                "motif_warn", "motif_unban", "motif_unmute", "motif_tempban", "motif_tempmute", "1", "1", chemainFiles,
                nameFile);
        System.out.println("\n[--Lecture du fichier--]");
        String ban = readFiles("ban", nameFile, chemainFiles);
        String temp_mute = readFiles("temp_mute", nameFile, chemainFiles);

        System.out.println(ban);
        System.out.println(temp_mute);

        updateFileAndIsExiste("test", "mute", "warn", "temp_ban", "motif_ban", "temp_mute", "motif_kick", "motif_mute",
                "motif_warn", "motif_unban", "motif_unmute", "motif_tempban", "motif_tempmute", "1", "1", chemainFiles,
                nameFile);
        String ban2 = readFiles("ban", "players.json", chemainFiles);
        String temp_mute2 = readFiles("temp_mute", "players.json", chemainFiles);

        System.out.println(ban2);
        System.out.println(temp_mute2);

    } */

}

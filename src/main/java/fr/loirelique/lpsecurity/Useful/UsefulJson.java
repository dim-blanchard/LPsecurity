package fr.loirelique.lpsecurity.Useful;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UsefulJson {

    private static String chemainFolder = "C:\\Users\\Master\\Desktop\\Erizia-spigot-1.8.8\\Spigot-1.8.8-serveur\\plugins\\LPsecurity";
    private static String chemainFiles = "C:\\Users\\Master\\Desktop\\Erizia-spigot-1.8.8\\Spigot-1.8.8-serveur\\plugins\\LPsecurity\\historique_sanctions\\";

    public static void createFiles(String ban, String mute, String warn, String temp_ban, String motif_ban,
            String temp_mute, String motif_kick, String motif_mute, String motif_warn, String motif_unban,
            String motif_unmute, String motif_tempban, String motif_tempmute, File file) {

        JSONObject historique_sanctions = new JSONObject();
        historique_sanctions.put("ban", ban);
        historique_sanctions.put("mute", mute);
        historique_sanctions.put("warn", warn);
        historique_sanctions.put("temp_ban", temp_ban);
        historique_sanctions.put("motif_ban", motif_ban);
        historique_sanctions.put("temp_mute", temp_mute);
        historique_sanctions.put("motif_kick", motif_kick);
        historique_sanctions.put("motif_mute", motif_mute);
        historique_sanctions.put("motif_warn", motif_warn);
        historique_sanctions.put("motif_unban", motif_unban);
        historique_sanctions.put("motif_unmute", motif_unmute);
        historique_sanctions.put("motif_tempban", motif_tempban);
        historique_sanctions.put("motif_tempmute", motif_tempmute);
        /*
         * JSONObject histo = new JSONObject();
         * histo.put("historique_sanctions", historique_sanctions);
         */

        try (FileWriter fileW = new FileWriter(file)) {
            fileW.write(historique_sanctions.toJSONString());
            fileW.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateFiles(String ban, String mute, String warn, String temp_ban, String motif_ban,
            String temp_mute, String motif_kick, String motif_mute, String motif_warn, String motif_unban,
            String motif_unmute, String motif_tempban, String motif_tempmute, File file) {

        JSONObject historique_sanctions = new JSONObject();
        historique_sanctions.put("ban", ban);
        historique_sanctions.put("mute", mute);
        historique_sanctions.put("warn", warn);
        historique_sanctions.put("temp_ban", temp_ban);
        historique_sanctions.put("motif_ban", motif_ban);
        historique_sanctions.put("temp_mute", temp_mute);
        historique_sanctions.put("motif_kick", motif_kick);
        historique_sanctions.put("motif_mute", motif_mute);
        historique_sanctions.put("motif_warn", motif_warn);
        historique_sanctions.put("motif_unban", motif_unban);
        historique_sanctions.put("motif_unmute", motif_unmute);
        historique_sanctions.put("motif_tempban", motif_tempban);
        historique_sanctions.put("motif_tempmute", motif_tempmute);

        try (FileWriter fileW = new FileWriter(file)) {
            fileW.write(historique_sanctions.toJSONString());
            fileW.flush();

        } catch (IOException e) {
            e.printStackTrace();
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

    public static void creatFolderAndVerifExists(String nameFolder, String chemain) {

        // Creation du dossier
        File test = new File(
                chemain, nameFolder);
        if (test.exists() == true) {
            System.out.println("Dossier Exsist...");
        } else {
            System.out.println("Dossier abs création..");
            test.mkdir();
        }
        ////////////////////
    }

    private static void creatFilesAndVerifExists(String chemain, String nameFile) {
        File test1 = new File(chemain, nameFile);
        if (test1.exists() == true) {
            System.out.println("Fichier exsite...");
        } else {
            System.out.println("Fichier n'exsite pas...Fichier en création...");
            createFiles("ban", "mute", "warn", "temp_ban", "motif_ban", "temp_mute", "motif_kick", "motif_mute",
                    "motif_warn", "motif_unban", "motif_unmute", "motif_tempban", "motif_tempmute", test1);

        }
    }

    public static void main(String args[]) {

        // if le fichier du joueur existe on le cree sinon non
        String nameFile = "players.json";
        String nameFolder = "historique_sanctions";
        creatFolderAndVerifExists(nameFolder, chemainFolder);
        creatFilesAndVerifExists(chemainFiles, nameFile);

        System.out.println("\n[--Lecture du fichier--]");
        String ban = readFiles("ban", "players.json", chemainFiles);
        String temp_mute = readFiles("temp_mute", "players.json", chemainFiles);

        System.out.println(ban);
        System.out.println(temp_mute);
    }

}

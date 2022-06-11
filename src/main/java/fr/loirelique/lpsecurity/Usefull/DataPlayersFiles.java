package fr.loirelique.lpsecurity.Usefull;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.loirelique.lpsecurity.Main;

public class DataPlayersFiles {

    public static void create(String uuidPlayers, String chemainFiles) {
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("[LPsecurity] File Data Players Exists.");
        } else {
            System.out.println("[LPsecurity] File Data Players Not Exists.");
            fileConfiguration.set("ban", 2);
            fileConfiguration.set("motif_ban", "null");
            fileConfiguration.set("motif_unban", "null");

            fileConfiguration.set("temp_ban", "null");
            fileConfiguration.set("motif_tempban", "null");

            fileConfiguration.set("mute", 2);
            fileConfiguration.set("motif_mute", "null");
            fileConfiguration.set("motif_unmute", "null");

            fileConfiguration.set("temp_mute", "null");
            fileConfiguration.set("motif_tempmute", "null");

            fileConfiguration.set("warn", 0);
            fileConfiguration.set("motif_warn", "null");

            fileConfiguration.set("motif_kick", "null");

            fileConfiguration.set("isOnline", false);
            fileConfiguration.set("isLogin", false);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("[LPsecurity] File Data Players Was Create.");
        }
    }

    public static void updateHistoriqueSanctions(String uuidPlayers,
            int ban, String motif_ban, String motif_unban,
            String temp_ban, String motif_tempban,
            int mute, String motif_mute, String motif_unmute,
            String temp_mute, String motif_tempmute,
            String motif_kick,
            int warn, String motif_warn, String chemainFiles) {

        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("Update Data Player");
            fileConfiguration.set("ban", ban);
            fileConfiguration.set("motif_ban", motif_ban);
            fileConfiguration.set("motif_unban", motif_unban);

            fileConfiguration.set("temp_ban", temp_ban);
            fileConfiguration.set("motif_tempban", motif_tempban);

            fileConfiguration.set("mute", mute);
            fileConfiguration.set("motif_mute", motif_mute);
            fileConfiguration.set("motif_unmute", motif_unmute);

            fileConfiguration.set("temp_mute", temp_mute);
            fileConfiguration.set("motif_tempmute", motif_tempmute);

            fileConfiguration.set("warn", warn);
            fileConfiguration.set("motif_warn", motif_warn);

            fileConfiguration.set("motif_kick", motif_kick);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    //////////////////////////////////////////////////////////////////
    public static void setIsOnline(String uuidPlayers, Boolean isOnline, String chemainFiles) {

        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("Update Data Player");
            fileConfiguration.set("isOnline", isOnline);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public static void setIsLogin(String uuidPlayers, Boolean isLogin, String chemainFiles) {

        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("Update Data Player");
            fileConfiguration.set("isLogin", isLogin);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    ////////////////////////////////////////////////////////////////

    public static boolean getIsLogin(String uuidPlayers, String chemainFiles) {
        Boolean isLogin = false;
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("Get IsLogin or not.");
            isLogin = (Boolean) fileConfiguration.get("islogin");
        }
        return isLogin;
    }

    public static boolean getIsOnline(String uuidPlayers, String chemainFiles) {
        Boolean isOnline = false;
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("Get IsOnline or not.");
            isOnline = (Boolean) fileConfiguration.get("isOnline");
        }
        return isOnline;
    }

    /*
     * HISTORIQUE SANCTION SECTION MUTE
     * 
     */

    public static void updateMuteAndMotif(String uuidPlayers,
            int mute, String motif_mute, String chemainFiles) {

        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            fileConfiguration.set("mute", mute);
            fileConfiguration.set("motif_mute", motif_mute);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    public static void setMuteTempMuteAndMotif(String uuidPlayers,
            int mute, String temp_mute, String motif_tempmute, String chemainFiles) {

        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            fileConfiguration.set("mute", mute);
            fileConfiguration.set("temp_mute", temp_mute);
            fileConfiguration.set("motif_tempmute", motif_tempmute);

            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    public static void setMuteAndMotif(String uuidPlayers,
            int mute, String motif_mute, String chemainFiles) {

        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            fileConfiguration.set("mute", mute);
            fileConfiguration.set("motif_mute", motif_mute);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    public static void setUnmuteAndMotif(String uuidPlayers,
            int mute, String motif_unmute, String chemainFiles) {

        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            fileConfiguration.set("mute", mute);
            fileConfiguration.set("motif_unmute", motif_unmute);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    public static int getMute(String uuidPlayers, String chemainFiles) {
        int value = 2;
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            value = fileConfiguration.getInt("mute");
        }
        return value;
    }

    public static String getMotifMute(String uuidPlayers, String chemainFiles) {
        String value = new String();
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            value = fileConfiguration.getString("motif_mute");
        }
        return value;
    }

    public static String getTempMute(String uuidPlayers, String chemainFiles) {
        String value = new String();
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            value = fileConfiguration.getString("temp_mute");
        }
        return value;
    }

    public static String getMotifTempMute(String uuidPlayers, String chemainFiles) {
        String value = new String();
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            value = fileConfiguration.getString("motif_tempmute");
        }
        return value;
    }

    public static String getUnMute(String uuidPlayers, String chemainFiles) {
        String value = new String();
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = uuidPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            value = fileConfiguration.getString("motif_unmute");
        }
        return value;
    }

}

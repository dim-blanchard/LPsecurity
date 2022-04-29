package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class ConfigBdd {

    /**
     * GETTER DE CONFIG BDD
     */
    public static String getDriver() {
        String driver = Main.plugin.getConfig().getString("bdd.driver");
        return driver;
    }

    public static String getHost() {
        String host = Main.plugin.getConfig().getString("bdd.host");
        return host;
    }

    public static String getPort() {
        String port = Main.plugin.getConfig().getString("bdd.port");
        return port;
    }

    public static String getDatabase1() {
        String database = Main.plugin.getConfig().getString("bdd.database");
        return database;
    }

    public static String getUser1() {
        String user = Main.plugin.getConfig().getString("bdd.user");
        return user;
    }

    public static String getPass1() {
        String pass = Main.plugin.getConfig().getString("bdd.pass");
        return pass;
    }

    public static String getTable1() {
        String table = Main.plugin.getConfig().getString("bdd.table");
        return table;
    }


    public static String getUserSelect() {
        String user = Main.plugin.getConfig().getString("bdd.user_select");
        return user;
    }

    public static String getPassSelect() {
        String pass = Main.plugin.getConfig().getString("bdd.pass_select");
        return pass;
    }

    public static String getUserSelectUpdate() {
        String user = Main.plugin.getConfig().getString("bdd.user_select_update");
        return user;
    }

    public static String getPassSelectUpdate() {
        String pass = Main.plugin.getConfig().getString("bdd.pass_select_update");
        return pass;
    }

    public static String getUserSelectInsert() {
        String user = Main.plugin.getConfig().getString("bdd.uuser_select_insert");
        return user;
    }

    public static String getPassSelectInsert() {
        String pass = Main.plugin.getConfig().getString("bdd.pass_select_insert");
        return pass;
    }

    public static String getSel() {
        String sel = Main.plugin.getConfig().getString("security.sel");

        return sel;
    }

   

}

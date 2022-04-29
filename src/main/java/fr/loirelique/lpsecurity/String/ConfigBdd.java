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


    public static String getSel() {
        String sel = Main.plugin.getConfig().getString("security.sel");

        return sel;
    }

   

}

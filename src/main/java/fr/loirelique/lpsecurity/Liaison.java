package fr.loirelique.lpsecurity;

import org.bukkit.plugin.java.JavaPlugin;


public class Liaison {
    
    private JavaPlugin config;

    private String url;
    private String host ;
    private String user ;
    private String pass;

    private String database;

    public Liaison() {
        
        this.url = config.getConfig().getString("bdd.url");
        this.host = config.getConfig().getString("bdd.host");
        this.user = config.getConfig().getString("bdd.user");
        this.pass = config.getConfig().getString("bdd.pass");

        this.database = config.getConfig().getString("bdd.database");
        config.saveDefaultConfig();
    }


    public String getUrl() {
         return url;
     }

    public String getHost() {
        return host;
    }
     
    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getDatabase() {
        return database;
    }



    

}


package fr.loirelique.lpsecurity.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.loirelique.lpsecurity.String.ConfigBdd;

public class RequestDatabase {
    
    private int id=0;
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    private String uuidPlayers = "null";
    public String getUuidPlayers() {return uuidPlayers;}
    public void setUuidPlayers(String uuidPlayers) {this.uuidPlayers = uuidPlayers;}

    private String pseudo="null";
    public String getPseudo() {return pseudo;}
    public void setPseudo(String pseudo) {this.pseudo = pseudo;}

    private String password = "null";
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public void getAllDatabase(String uuidPlayers) {
            try (Connection connection = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Select_sql3 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
            try (PreparedStatement statement2_select = connection
                    .prepareStatement(requet_Select_sql3)) {
                statement2_select.setString(1, uuidPlayers);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                        setId(resultat_requete_select.getInt("id"));
                        setUuidPlayers(resultat_requete_select.getString("uuid"));
                        setPseudo(resultat_requete_select.getString("pseudo"));
                        setPassword(resultat_requete_select.getString("password"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();                   
        }
    }

    public boolean isOnline () {
        try (Connection connection = DriverManager.getConnection(ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"+ ConfigBdd.getDatabase1()+ "?characterEncoding=latin1&useConfigs=maxPerformance",ConfigBdd.getUser1(), ConfigBdd.getPass1()))
        {return true;}catch(Exception e) {e.printStackTrace(); return false;}
    }

    public void insertNewPlayers(String uuidPlayers, String pseudo, String password) {
        try (Connection connection = DriverManager.getConnection(
             ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
             + "/"
             + ConfigBdd.getDatabase1()
             + "?characterEncoding=latin1&useConfigs=maxPerformance",
             ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_insert_sql1 = "INSERT INTO " + ConfigBdd.getTable1()
            + " (uuid,pseudo,password,historique_sanctions) VALUES(?,?,?,?)";
                try (PreparedStatement statement1_insert = connection
                     .prepareStatement(requet_insert_sql1)) {                              
                    String historiqueSanctionDefault = "{\"mute\": 0,\"warn\": 0,\"ban\": 0,\"temp_ban\": \"null\", \"motif_ban\": \"null\", \"temp_mute\": \"null\", \"motif_kick\": \"null\", \"motif_mute\": \"null\", \"motif_warn\": \"null\", \"motif_unban\": \"null\", \"motif_unmute\": \"null\", \"motif_tempban\": \"null\", \"motif_tempmute\": \"null\"}";
                    statement1_insert.setString(1, uuidPlayers);
                    statement1_insert.setString(2, pseudo);
                    statement1_insert.setString(3, password);
                    statement1_insert.setString(4, historiqueSanctionDefault);
                    statement1_insert.executeUpdate();}
                } catch (Exception e) {e.printStackTrace();}
    }

}

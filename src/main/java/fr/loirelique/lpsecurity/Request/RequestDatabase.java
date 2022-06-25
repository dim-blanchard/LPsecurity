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

    private String motif_ban ="null" ;
    public String getMotif_ban() {return motif_ban;}
    public void setMotif_ban(String motif_ban) {this.motif_ban = motif_ban;}

    private String motif_tempban ="null" ;
    public String getMotif_tempban() {return motif_tempban;}
    public void setMotif_tempban(String motif_tempban) {this.motif_tempban = motif_tempban;}

    private String motif_unban ="null" ;
    public String getMotif_unban() {return motif_unban;}

    public void setMotif_unban(String motif_unban) {this.motif_unban = motif_unban;}

    private String motif_kick="null" ;
    public String getMotif_kick() {return motif_kick;}
    public void setMotif_kick(String motif_kick) {this.motif_kick = motif_kick;}

    private String motif_mute="null" ;
    public String getMotif_mute() {return motif_mute;}
    public void setMotif_mute(String motif_mute) {this.motif_mute = motif_mute;}

    private String motif_warn ="null";
    public String getMotif_warn() {return motif_warn;}
    public void setMotif_warn(String motif_warn) {this.motif_warn = motif_warn;}

    private String motif_unmute ="null";
    public String getMotif_unmute() {return motif_unmute;}
    public void setMotif_unmute(String motif_unmute) {this.motif_unmute = motif_unmute;}

    private String motif_tempmute ="null";
    public String getMotif_tempmute() {return motif_tempmute;}
    public void setMotif_tempmute(String motif_tempmute) {this.motif_tempmute = motif_tempmute;}

    private int ban = 0 ;
    public int getBan() {return ban;}
    public void setBan(int ban) {this.ban = ban;}

    private int warn = 0;
    public int getWarn() {return warn;}
    public void setWarn(int warn) {this.warn = warn;}

    private int mute = 0;
    public int getMute() {return mute;}
    public void setMute(int mute) {this.mute = mute;}

    private String tempmute ="null";
    public String getTempmute() {return tempmute;}
    public void setTempmute(String tempmute) {this.tempmute = tempmute;}

    private String tempban="null";
    public String getTempban() {return tempban;}
    public void setTempban(String tempban) {this.tempban = tempban;}

    public void getColomun(String uuidPlayers) {
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

    public void getHistoriqueSanction(String uuidPlayers) {
        try (Connection connection = DriverManager.getConnection(
        ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                + ConfigBdd.getDatabase1()
                + "?characterEncoding=latin1&useConfigs=maxPerformance",
        ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
    String requet_Select_sql2 = "SELECT historique_sanctions->>'$.temp_ban',historique_sanctions->>'$.temp_mute', historique_sanctions->>'$.mute', historique_sanctions->>'$.ban',historique_sanctions->>'$.warn',historique_sanctions->>'$.motif_ban',historique_sanctions->>'$.motif_tempban',historique_sanctions->>'$.motif_unban',historique_sanctions->>'$.motif_kick',historique_sanctions->>'$.motif_warn',historique_sanctions->>'$.motif_mute',historique_sanctions->>'$.motif_unmute',historique_sanctions->>'$.motif_tempmute' FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
    try (PreparedStatement statement2_select = connection
            .prepareStatement(requet_Select_sql2)) {
        statement2_select.setString(1, uuidPlayers);

        try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
            while (resultat_requete_select.next()) {
            setMotif_ban(resultat_requete_select.getString("historique_sanctions->>'$.motif_ban'"));
            setMotif_tempban(resultat_requete_select.getString("historique_sanctions->>'$.motif_tempban'"));
            setMotif_unban(resultat_requete_select.getString("historique_sanctions->>'$.motif_unban'"));
            setTempban(resultat_requete_select.getString("historique_sanctions->>'$.temp_ban'"));

            setMotif_mute( resultat_requete_select.getString("historique_sanctions->>'$.motif_mute'"));
            setMotif_tempmute(resultat_requete_select.getString("historique_sanctions->>'$.motif_tempmute'"));;
            setMotif_unmute(resultat_requete_select.getString("historique_sanctions->>'$.motif_unmute'"));
            setTempmute(resultat_requete_select.getString("historique_sanctions->>'$.temp_mute'"));

            setMotif_kick( resultat_requete_select.getString("historique_sanctions->>'$.motif_kick'"));;
            setMotif_warn(resultat_requete_select.getString("historique_sanctions->>'$.motif_warn'"));

            setBan(resultat_requete_select.getInt("historique_sanctions->>'$.ban'"));
            setWarn(resultat_requete_select.getInt("historique_sanctions->>'$.warn'"));
            setMute(resultat_requete_select.getInt("historique_sanctions->>'$.mute'"));
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

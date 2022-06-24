package fr.loirelique.lpsecurity.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.loirelique.lpsecurity.String.ConfigBdd;

public class RequestHistorique {
    
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

    private int ban =0 ;
    public int getBan() {return ban;}
    public void setBan(int ban) {this.ban = ban;}

    private int warn =0;
    public int getWarn() {return warn;}
    public void setWarn(int warn) {this.warn = warn;}

    private int mute =0;
    public int getMute() {return mute;}
    public void setMute(int mute) {this.mute = mute;}

    public RequestHistorique(String uuidPlayers) {
                try (Connection connection = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Select_sql2 = "SELECT  historique_sanctions->>'$.mute', historique_sanctions->>'$.ban',historique_sanctions->>'$.warn',historique_sanctions->>'$.motif_ban',historique_sanctions->>'$.motif_tempban',historique_sanctions->>'$.motif_unban',historique_sanctions->>'$.motif_kick',historique_sanctions->>'$.motif_warn',historique_sanctions->>'$.motif_mute',historique_sanctions->>'$.motif_unmute',historique_sanctions->>'$.motif_tempmute' FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
            try (PreparedStatement statement2_select = connection
                    .prepareStatement(requet_Select_sql2)) {
                statement2_select.setString(1, uuidPlayers);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                    setMotif_ban(resultat_requete_select.getString("historique_sanctions->>'$.motif_ban'"));
                    setMotif_tempban(resultat_requete_select.getString("historique_sanctions->>'$.motif_tempban'"));
                    setMotif_unban(resultat_requete_select.getString("historique_sanctions->>'$.motif_unban'"));

                    setMotif_mute( resultat_requete_select.getString("historique_sanctions->>'$.motif_mute'"));
                    setMotif_tempmute(resultat_requete_select.getString("historique_sanctions->>'$.motif_tempmute'"));;
                    setMotif_unmute(resultat_requete_select.getString("historique_sanctions->>'$.motif_unmute'"));

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





/*     public void name(String uuidPlayers) {

            try (Connection connection = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Select_sql2 = "SELECT   historique_sanctions->>'$.ban',historique_sanctions->>'$.warn',historique_sanctions->>'$.motif_ban',historique_sanctions->>'$.motif_tempban',historique_sanctions->>'$.motif_unban',historique_sanctions->>'$.motif_kick',historique_sanctions->>'$.motif_warn',historique_sanctions->>'$.motif_mute',historique_sanctions->>'$.motif_unmute',historique_sanctions->>'$.motif_tempmute' FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
            try (PreparedStatement statement2_select = connection
                    .prepareStatement(requet_Select_sql2)) {
                statement2_select.setString(1, uuidPlayers);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                    motif_ban = resultat_requete_select.getString("historique_sanctions->>'$.motif_ban'");
                    motif_tempban = resultat_requete_select.getString("historique_sanctions->>'$.motif_tempban'");
                    motif_unban = resultat_requete_select.getString("historique_sanctions->>'$.motif_unban'");

                    motif_mute = resultat_requete_select.getString("historique_sanctions->>'$.motif_mute'");
                    motif_tempmute = resultat_requete_select.getString("historique_sanctions->>'$.motif_tempmute'");
                    motif_unmute = resultat_requete_select.getString("historique_sanctions->>'$.motif_unmute'");

                    motif_kick = resultat_requete_select.getString("historique_sanctions->>'$.motif_kick'");
                    motif_warn = resultat_requete_select.getString("historique_sanctions->>'$.motif_warn'");

                    ban = resultat_requete_select.getInt("historique_sanctions->>'$.ban'");
                    warn = resultat_requete_select.getInt("historique_sanctions->>'$.warn'"); 
                    }   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    } */


}

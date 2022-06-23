package fr.loirelique.lpsecurity.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.loirelique.lpsecurity.String.ConfigBdd;

public class RequestHistorique {

    private static String motif_ban ;
    private static String motif_tempban ;
    private static String motif_unban ;
    private static String motif_kick ;
    private static String motif_mute ;
    private static String motif_warn ;
    private static String motif_unmute ;
    private static String motif_tempmute ;
    private static int ban ;
    private static int warn ;


    public static void name(String uuidPlayers) {

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
    }


}

package fr.loirelique.lpsecurity.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.loirelique.lpsecurity.String.ConfigBdd;

public class RequestMute{

    public static int getMute(String uuidPlayers) {
        int mute = 2;
        try (Connection connection = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Select_sql2 = "SELECT historique_sanctions->>'$.mute' FROM "
                    + ConfigBdd.getTable1() + " WHERE uuid=?";
            try (PreparedStatement statement2_select = connection
                    .prepareStatement(requet_Select_sql2)) {
                statement2_select.setString(1, uuidPlayers);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                        mute = resultat_requete_select.getInt("historique_sanctions->>'$.mute'");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mute;
    }

    public static void setMuteAndMotif(String uuidPlayers, String motif_mute) {
        
        try (Connection connection_update = DriverManager.getConnection(
            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
                    ConfigBdd.getPort()
                    + "/"
                    + ConfigBdd.getDatabase1()
                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
        String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() +
                " SET historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')), historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) WHERE uuid=?";
        try (PreparedStatement statement2_select = connection_update
                .prepareStatement(requet_Update_sql2)) {
            statement2_select.setString(1, "mute");
            statement2_select.setInt(2, 1);
            statement2_select.setString(3, "motif_mute");
            statement2_select.setString(4, motif_mute);
            statement2_select.setString(5, uuidPlayers);
            statement2_select.executeUpdate();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}

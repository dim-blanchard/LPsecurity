package fr.loirelique.lpsecurity.Request;

import fr.loirelique.lpsecurity.String.ConfigBdd;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class RequestBan {

    public static int getBan(String uuidPlayers) {
        int ban = 2;
        try (Connection connection = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Select_sql2 = "SELECT historique_sanctions->>'$.ban' FROM " + ConfigBdd.getTable1()
                    + " WHERE uuid=?";
            try (PreparedStatement statement2_select = connection
                    .prepareStatement(requet_Select_sql2)) {
                statement2_select.setString(1, uuidPlayers);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                        ban = resultat_requete_select.getInt("historique_sanctions->>'$.ban'");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ban;
        }

        return ban;
    }

    public static void setBanAndMotif(String uuidPlayers,String motif_ban) {
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
            statement2_select.setString(1, "ban");
            statement2_select.setInt(2, 1);
            statement2_select.setString(3, "motif_ban");
            statement2_select.setString(4, motif_ban);
            statement2_select.setString(5, uuidPlayers);
            statement2_select.executeUpdate();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}

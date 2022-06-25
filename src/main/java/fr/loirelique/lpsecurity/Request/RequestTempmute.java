package fr.loirelique.lpsecurity.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import fr.loirelique.lpsecurity.String.ConfigBdd;

public class RequestTempmute {
    public static void setMuteAndTempMuteMotif(String uuidPlayers , String temp_mute , String motif_tempmute) {
        try (Connection connection_update = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
                        ConfigBdd.getPort()
                        + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() +
                    " SET historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) , historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')), historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) , historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) WHERE uuid=?";
            try (PreparedStatement statement2_select = connection_update
                    .prepareStatement(requet_Update_sql2)) {
                statement2_select.setString(1, "mute");
                statement2_select.setInt(2, 1);
                statement2_select.setString(3, "temp_mute");
                statement2_select.setString(4, temp_mute);
                statement2_select.setString(5, "motif_tempmute");
                statement2_select.setString(6, motif_tempmute);
                statement2_select.setString(7, uuidPlayers);
                statement2_select.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setUnMuteAndTempMuteMotif(String uuidPlayers) {     
        try (Connection connection_update = DriverManager.getConnection(
            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
                    ConfigBdd.getPort()
                    + "/"
                    + ConfigBdd.getDatabase1()
                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
        String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() +
                " SET historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')),, historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')),historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) WHERE uuid=?";
        try (PreparedStatement statement2_select = connection_update
                .prepareStatement(requet_Update_sql2)) {
            statement2_select.setString(1, "mute");
            statement2_select.setInt(2, 0);
            statement2_select.setString(3, "motif_tempmute");
            statement2_select.setString(4, "null");
            statement2_select.setString(5, "temp_mute");
            statement2_select.setString(6, "null");
            statement2_select.setString(7, uuidPlayers);
            statement2_select.executeUpdate();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}

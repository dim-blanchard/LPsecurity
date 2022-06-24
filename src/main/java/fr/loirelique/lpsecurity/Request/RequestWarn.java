package fr.loirelique.lpsecurity.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import fr.loirelique.lpsecurity.List.ListWarningDegresAndMotifs;
import fr.loirelique.lpsecurity.String.ConfigBdd;

public class RequestWarn {

    public static void setWarnAndMotif(String uuidPlayers , int warn , String motif_warn) {
        try (Connection connection_update2 = DriverManager.getConnection(
            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
                    ConfigBdd.getPort()
                    + "/"
                    + ConfigBdd.getDatabase1()
                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
        String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() +
                " SET historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')), historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) WHERE uuid=?";
        try (PreparedStatement statement2_select = connection_update2
                .prepareStatement(requet_Update_sql2)) {
            statement2_select.setString(1, "warn");
            statement2_select.setString(2, Integer.toString(warn));
            statement2_select.setString(3, "motif_warn");
            statement2_select.setString(4, ListWarningDegresAndMotifs.getMotifs(motif_warn));
            statement2_select.setString(5,  uuidPlayers);
            statement2_select.executeUpdate();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public static void setWarnBanAndMotifs(String uuidPlayers , int warn , String motif_warn) {
        try (Connection connection = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
                        ConfigBdd.getPort()
                        + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() +
                    " SET historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')), historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')), historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')), historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,''))  WHERE uuid=?";
            try (PreparedStatement statement2_select = connection
                    .prepareStatement(requet_Update_sql2)) {
                statement2_select.setString(1, "ban");
                statement2_select.setInt(2, 1);
                statement2_select.setString(3, "motif_ban");
                statement2_select.setString(4, ListWarningDegresAndMotifs.getMotifs(motif_warn));
                statement2_select.setString(5, "warn");
                statement2_select.setString(6, Integer.toString(warn));
                statement2_select.setString(7, "motif_warn");
                statement2_select.setString(8, ListWarningDegresAndMotifs.getMotifs(motif_warn));

                statement2_select.setString(9, uuidPlayers);
                statement2_select.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

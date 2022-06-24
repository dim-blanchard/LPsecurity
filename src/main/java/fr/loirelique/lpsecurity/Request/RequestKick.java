package fr.loirelique.lpsecurity.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import fr.loirelique.lpsecurity.String.ConfigBdd;

public class RequestKick {
    public static void setKickAndMotif(String uuidPlayers, String motif_kick) {
        try (Connection connection = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                        + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_insert_sql1 = "UPDATE " + ConfigBdd.getTable1()
                    + " SET historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,''))  WHERE uuid=?";
            try (PreparedStatement statement1_insert = connection
                    .prepareStatement(requet_insert_sql1)) {

                statement1_insert.setString(1, "motif_kick");
                statement1_insert.setString(2, motif_kick);

                statement1_insert.setString(3, uuidPlayers);
                statement1_insert.executeUpdate();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

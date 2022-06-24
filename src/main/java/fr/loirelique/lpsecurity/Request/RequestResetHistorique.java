package fr.loirelique.lpsecurity.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import fr.loirelique.lpsecurity.String.ConfigBdd;

public class RequestResetHistorique {
    
    
    public static void setResetHistorique(String uuidPlayers) {
        try (Connection connection_reset = DriverManager.getConnection(
            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                    + "/"
                    + ConfigBdd.getDatabase1()
                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {

        String requet_insert_sql1 = "UPDATE " + ConfigBdd.getTable1()
                + " SET historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) ,historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) ,historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) ,historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) ,historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) ,historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) ,historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) ,historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,''))  WHERE uuid=?";
        try (PreparedStatement statement1_insert = connection_reset
                .prepareStatement(requet_insert_sql1)) {
            
            statement1_insert.setString(1, "motif_ban");
            statement1_insert.setString(2, "null");

            statement1_insert.setString(3, "motif_tempban");
            statement1_insert.setString(4, "null");

            statement1_insert.setString(5, "motif_unban");
            statement1_insert.setString(6, "null");

            statement1_insert.setString(7, "motif_mute");
            statement1_insert.setString(8, "null");

            statement1_insert.setString(9, "motif_unmute");
            statement1_insert.setString(10, "null");

            statement1_insert.setString(11, "motif_tempmute");
            statement1_insert.setString(12, "null");

            statement1_insert.setString(13, "motif_kick");
            statement1_insert.setString(14, "null");

            statement1_insert.setString(15, "motif_warn");
            statement1_insert.setString(16, "null");
            
            statement1_insert.setString(17, uuidPlayers);
            statement1_insert.executeUpdate();
        }
        

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}

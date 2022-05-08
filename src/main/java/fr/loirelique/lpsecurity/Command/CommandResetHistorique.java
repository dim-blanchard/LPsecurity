package fr.loirelique.lpsecurity.Command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.String.ConfigBdd;

/**
 * CommandeResetHistorique
 */
public class CommandResetHistorique implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (cmd.getName().equalsIgnoreCase("resethistorique")) { // Si c'est la commande "register" qui a été tapée:
                if (args.length == 1) {                 
                    String uuid = Main.plugin.getUuidHash(args[0]);
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
                            
                            statement1_insert.setString(17, uuid);
                            statement1_insert.executeUpdate();
                        }
                        p.sendMessage("Historique de sanctions "+ args[0]+" reset.");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    p.sendMessage("Commande non executer");
                }
            }
        }

        return errorCommande;
    }

}
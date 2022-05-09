package fr.loirelique.lpsecurity.Command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.String.ConfigBdd;
import fr.loirelique.lpsecurity.String.MessageMute;

public class CommandMute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.mute")) {
                if (cmd.getName().equalsIgnoreCase("mute")) { // Si c'est la commande "banish" qui a été tapée:

                    if (args.length >= 2) {
                        String pseudo = args[0];
                        String uuid = Main.plugin.getUuidHash(pseudo);
                        int mute = 2;

                        try (Connection connection_register = DriverManager.getConnection(
                                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                                        + ConfigBdd.getDatabase1()
                                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                            String requet_Select_sql2 = "SELECT historique_sanctions->>'$.mute' FROM "
                                    + ConfigBdd.getTable1() + " WHERE uuid=?";
                            try (PreparedStatement statement2_select = connection_register
                                    .prepareStatement(requet_Select_sql2)) {
                                statement2_select.setString(1, uuid);

                                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                                    while (resultat_requete_select.next()) {
                                        mute = resultat_requete_select.getInt("historique_sanctions->>'$.mute'");
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (mute == 0) {
                            StringBuilder builder = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {

                                String ar = Main.plugin.sansAccent(args[i].replace(" ' ", " \' "));
                                builder.append(ar).append(" ");
                            }
                            String msg = builder.toString();

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
                                    statement2_select.setString(2, "1");
                                    statement2_select.setString(3, "motif_mute");
                                    statement2_select.setString(4, msg);
                                    statement2_select.setString(5, uuid);
                                    statement2_select.executeUpdate();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            p.sendMessage(MessageMute.setColorMute() + "[" + pseudo + "] " + MessageMute.getMute());
                            if (Main.plugin.getListOnlinePlayer(uuid) == "1") {
                                Player player = Main.plugin.getListPlayer(uuid);
                                player.kickPlayer("Bannie:" + msg);
                            }
                            errorCommande = true;
                        }
                        if (mute == 1) {
                            p.sendMessage(
                                    MessageMute.setColorAlreadyMute() + "[" + pseudo + "] " + MessageMute.getAlreadyMute());
                            errorCommande = true;
                        }

                    }

                    if (errorCommande == true) {
                        errorCommande = true;
                    } else if (errorCommande == false) {
                        errorCommande = false;
                        p.sendMessage(MessageMute.setColorErrorMute() + MessageMute.getErrorMute());
                    }

                }
            } else {
                p.sendMessage("Pas la permission.");
            }
        }
        return errorCommande;
    }

}

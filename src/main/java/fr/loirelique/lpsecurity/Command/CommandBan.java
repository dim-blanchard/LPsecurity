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
import fr.loirelique.lpsecurity.String.MessageBan;
import fr.loirelique.lpsecurity.Usefull.DataListPlayers;
import fr.loirelique.lpsecurity.Usefull.DataPlayersFiles;

public class CommandBan implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.ban")) {
                if (cmd.getName().equalsIgnoreCase("ban")) { // Si c'est la commande "banish" qui a été tapée:

                    if (args.length >= 2) {
                        String pseudo = args[0];
                        String uuidPlayers = Main.plugin.getUuidHash(pseudo);
                        int ban = 2;

                        try (Connection connection_register = DriverManager.getConnection(
                                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                                        + ConfigBdd.getDatabase1()
                                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                            String requet_Select_sql2 = "SELECT historique_sanctions->>'$.ban' FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
                            try (PreparedStatement statement2_select = connection_register
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
                        }
                        if (ban == 0) {
                            StringBuilder builder = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {

                                String ar = Main.plugin.sansAccent(args[i].replace(" ' ", " \' "));
                                builder.append(ar).append(" ");
                            }
                            String motif_ban = builder.toString();

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

                        
                            p.sendMessage(MessageBan.setColorBan() + "[" + pseudo + "] " + MessageBan.getBan());
                            if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer) == true ) {
                                Player player = DataListPlayers.getObjectPlayers(uuidPlayers);                              
                                player.kickPlayer("Bannie:" + motif_ban);
                            }
                            errorCommande = true;
                        }
                        if (ban == 1) {
                            p.sendMessage(
                                    MessageBan.setColorAlreadyBan() + "[" + pseudo + "] " + MessageBan.getAlreadyBan());
                            errorCommande = true;
                        }

                    }

                    if (errorCommande == true) {
                        errorCommande = true;
                    } else if (errorCommande == false) {
                        errorCommande = false;
                        p.sendMessage(MessageBan.setColorErrorBan() + MessageBan.getErrorBan());
                    }

                }
            }else{p.sendMessage("Pas la permission.");}
        }
        return errorCommande;
    }
}

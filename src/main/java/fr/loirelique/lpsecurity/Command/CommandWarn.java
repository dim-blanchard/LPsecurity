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

public class CommandWarn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.

            if (cmd.getName().equalsIgnoreCase("unban")) { // Si c'est la commande "banish" qui a été tapée:

                if (args.length >= 2) {
                    String pseudo = args[0];
                    String uuid = Main.plugin.getUuidHash(pseudo);
                    int ban = 2;
                    int warn = 0;
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {

                        String ar = Main.plugin.sansAccent(args[i].replace(" ' ", " \' "));
                        builder.append(ar).append(" ");
                    }
                    String msg = builder.toString();

                    int warnDegresMax = 10;
                    String warningLvlOne="Avertissement niveau 1.";
                    int warningDegresLvlOne = 1;
                    String warningLvlTwo="Avertissement niveau 2.";
                    int warningDegresLvlTwo = 2;
                    String warningLvlThree="Avertissement niveau 3";
                    int warningDegresLvlThree = 3;

                    try (Connection connection_register = DriverManager.getConnection(
                            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                                    + ConfigBdd.getDatabase1()
                                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
                            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                        String requet_Select_sql2 = "SELECT historique_sanctions->>'$.ban',historique_sanctions->>'$.warn' FROM "
                                + ConfigBdd.getTable1() + " WHERE uuid=?";
                        try (PreparedStatement statement2_select = connection_register
                                .prepareStatement(requet_Select_sql2)) {
                            statement2_select.setString(1, uuid);

                            try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                                while (resultat_requete_select.next()) {
                                    ban = resultat_requete_select.getInt("historique_sanctions->>'$.ban'");
                                    warn = resultat_requete_select.getInt("historique_sanctions->>'$.warn'");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    

                    if (ban == 1) {
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
                                statement2_select.setString(2, "0");
                                statement2_select.setString(3, "motif_unban");
                                statement2_select.setString(4, msg);
                                statement2_select.setString(5, uuid);
                                statement2_select.executeUpdate();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        p.sendMessage(MessageUnban.setColorUnban() + "[" + pseudo + "] " + MessageUnban.getUnban());
                        errorCommande = true;
                    } else if (ban == 0) {
                        p.sendMessage(MessageUnban.setColorAlreadyUnban() + "[" + pseudo + "] "
                                + MessageUnban.getAlreadyUnban());
                        errorCommande = true;
                    }

                }
                if (errorCommande == true) {
                    errorCommande = true;
                } else if (errorCommande == false) {
                    errorCommande = false;
                    p.sendMessage(MessageUnban.setColorErrorUnban() + MessageUnban.getErrorUnban());
                }

            }
        }
        return errorCommande;
    }

}

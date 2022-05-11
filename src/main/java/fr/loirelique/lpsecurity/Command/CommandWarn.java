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
import fr.loirelique.lpsecurity.String.MessageWarn;
import fr.loirelique.lpsecurity.Useful.List.ListWarningDegresAndMotifs;

public class CommandWarn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.

            if (cmd.getName().equalsIgnoreCase("warn")) { // Si c'est la commande "banish" qui a été tapée:

                if (args.length >= 2) {
                    String pseudo = args[0];
                    String uuid = Main.plugin.getUuidHash(pseudo);
                    boolean condition = false;
                    int ban = 2;
                    int warn = 0;
                    String motifInsert = args[1];              

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

                    if (motifInsert.equals("motif1")) {
                        warn += ListWarningDegresAndMotifs.getDegres(motifInsert);
                    }
                    if (motifInsert.equals("motif2")) {
                        warn += ListWarningDegresAndMotifs.getDegres(motifInsert);
                    }
                    if (motifInsert.equals("motif3")) {
                        warn += ListWarningDegresAndMotifs.getDegres(motifInsert);
                    }
                    if (warn >= ListWarningDegresAndMotifs.getWarnDegresMax()) {
                        condition = true;
                    }else{
                        condition = false;
                    }
                    if (condition == true && ban ==1) {
                        p.sendMessage("joueur bannie regarder l'historique de sanctions.");
                    }
                    if (condition == false && ban ==1) {
                        p.sendMessage("joueur bannie regarder l'historique de sanctions");
                    }
                    
                    if (condition == true && ban == 0) {
                        //Bannie
                        try (Connection connection_update1 = DriverManager.getConnection(
                            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
                                    ConfigBdd.getPort()
                                    + "/"
                                    + ConfigBdd.getDatabase1()
                                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
                            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                        String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() +
                                " SET historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')), historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')), historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')), historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')), historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,''))  WHERE uuid=?";
                        try (PreparedStatement statement2_select = connection_update1
                                .prepareStatement(requet_Update_sql2)) {
                            statement2_select.setString(1, "ban");
                            statement2_select.setString(2, "1");
                            statement2_select.setString(3, "motif_ban");
                            statement2_select.setString(4, ListWarningDegresAndMotifs.getMotifs(motifInsert));
                            statement2_select.setString(5, "warn");
                            statement2_select.setString(6, Integer.toString(warn));
                            statement2_select.setString(7, "motif_warn");
                            statement2_select.setString(8, ListWarningDegresAndMotifs.getMotifs(motifInsert));
       
                            statement2_select.setString(9, uuid);
                            statement2_select.executeUpdate();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    p.sendMessage(MessageWarn.setColorWarnAndBan() + "[" + pseudo + "] " + MessageWarn.getWarnAndBan());
                    errorCommande = true;

                    }else if (condition == false && ban == 0) {
                        //warn 
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
                            statement2_select.setString(4, ListWarningDegresAndMotifs.getMotifs(motifInsert));
                            statement2_select.setString(5, uuid);
                            statement2_select.executeUpdate();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    p.sendMessage(MessageWarn.setColorWarn() + "[" + pseudo + "] " + MessageWarn.getWarn());
                    errorCommande = true;

                    }else{
                        p.sendMessage(MessageWarn.setColorAlreadyWarnAndBan() + "[" + pseudo + "] "
                        + MessageWarn.getAlreadyWarnAndBan());
                          errorCommande = true;
                    }

                }
                if (errorCommande == true) {
                    errorCommande = true;
                } else if (errorCommande == false) {
                    errorCommande = false;
                    p.sendMessage(MessageWarn.setColorErrorWarn() + MessageWarn.getErrorWarn());
                }

            }
        }
        return errorCommande;
    }

}

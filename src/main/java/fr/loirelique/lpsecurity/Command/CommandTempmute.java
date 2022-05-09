package fr.loirelique.lpsecurity.Command;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.String.ConfigBdd;
import fr.loirelique.lpsecurity.String.MessageTempmute;
import fr.loirelique.lpsecurity.Useful.DateAndTime;

public class CommandTempmute implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (cmd.getName().equalsIgnoreCase("tempmute")) { // Si c'est la commande "banish" qui a été tapée:

                if (args.length >= 2) {
                    int mute = 2;
                    String pseudo = args[0];
                    String uuid = Main.plugin.getUuidHash(pseudo);
                    System.out.println(args[0]);
                    DateAndTime dateAndTime = new DateAndTime();
                    String years = args[1];
                    System.out.println(args[1]);
                    String months = args[2];
                    System.out.println(args[2]);
                    String dayOfMonths = args[3];
                    System.out.println(args[3]);
                    String hours = args[4];
                    System.out.println(args[4]);
                    String minutes = args[5];
                    System.out.println(args[5]);

                    StringBuilder builder = new StringBuilder();
                    for (int i = 6; i < args.length; i++) {

                        String ar = Main.plugin.sansAccent(args[i].replace(" ' ", " \' "));
                        builder.append(ar).append(" ");
                    }
                    String msg = builder.toString();

                    try (Connection connection_register = DriverManager.getConnection(
                            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                                    + ConfigBdd.getDatabase1()
                                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
                            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                        String requet_Select_sql2 = "SELECT historique_sanctions->>'$.mute' FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
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

                        if (dateAndTime.testDateEtTime(years, months, dayOfMonths, hours, minutes) == true) {

                            int year = Integer.parseInt(years.replaceAll("\\s", ""));
                            int month = Integer.parseInt(months.replaceAll("\\s", ""));
                            int dayOfMonth = Integer.parseInt(dayOfMonths.replaceAll("\\s", ""));
                            int hour = Integer.parseInt(hours.replaceAll("\\s", ""));
                            int minute = Integer.parseInt(minutes.replaceAll("\\s", ""));

                            LocalDateTime heurDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
                            System.out.println(heurDateTime);
                            String heureDateTime = heurDateTime.toString();

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
                                    statement2_select.setString(2, "1");
                                    statement2_select.setString(3, "temp_mute");
                                    statement2_select.setString(4, heureDateTime);
                                    statement2_select.setString(5, "motif_tempmute");
                                    statement2_select.setString(6, msg);
                                    statement2_select.setString(7, uuid);
                                    statement2_select.executeUpdate();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            p.sendMessage(MessageTempmute.setColorTempmute() + "[" + pseudo + "] "
                                    + MessageTempmute.getTempmute());
                            Player player = Main.plugin.getListPlayer(uuid);
                            player.sendMessage(msg);
                            errorCommande = true;

                        } else if (dateAndTime.testDateEtTime(years, months, dayOfMonths, hours, minutes) == false) {
                            errorCommande = false;
                        }

                    } else if (mute == 1) {
                        p.sendMessage(MessageTempmute.setColoralreadyTempmute() + "[" + pseudo + "] "
                                + MessageTempmute.getAlreadyTempmute());
                        errorCommande = true;
                    }
                }

                if (errorCommande == true) {
                    errorCommande = true;
                } else if (errorCommande == false) {
                    errorCommande = false;
                    p.sendMessage(MessageTempmute.setColorErrorTempmute() + MessageTempmute.getErrorTempmute());
                }
            }
        }
        return errorCommande;
    }
    
}

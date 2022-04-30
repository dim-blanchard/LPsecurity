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
import fr.loirelique.lpsecurity.String.MessageTempban;
import fr.loirelique.lpsecurity.Useful.DateAndTime;

public class CommandTempban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (cmd.getName().equalsIgnoreCase("tempban")) { // Si c'est la commande "banish" qui a été tapée:

                if (args.length >= 2) {
                    int ban = 0;
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
                        String requet_Select_sql2 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
                        try (PreparedStatement statement2_select = connection_register
                                .prepareStatement(requet_Select_sql2)) {
                            statement2_select.setString(1, uuid);

                            try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                                while (resultat_requete_select.next()) {
                                    ban = resultat_requete_select.getInt("ban");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (ban == 0) {

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
                                        " SET ban=?, historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) , historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) WHERE uuid=?";
                                try (PreparedStatement statement2_select = connection_update
                                        .prepareStatement(requet_Update_sql2)) {
                                    statement2_select.setInt(1, 1);
                                    statement2_select.setString(2, "temp_ban");
                                    statement2_select.setString(3, heureDateTime);
                                    statement2_select.setString(4, "motif_tempban");
                                    statement2_select.setString(5, msg);
                                    statement2_select.setString(6, uuid);
                                    statement2_select.executeUpdate();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            p.sendMessage(MessageTempban.setColorTempban()+"["+pseudo + "] "+MessageTempban.getTempban());
                            Player player = Main.plugin.getListPlayer(uuid);
                            player.kickPlayer(msg);

                        } else if (dateAndTime.testDateEtTime(years, months, dayOfMonths, hours, minutes) == false) {
                                p.sendMessage(MessageTempban.setColorErrorTempban()+MessageTempban.getErrorTempban());
                        }

                    } else if (ban == 1) {
                        p.sendMessage(MessageTempban.setColoralreadyTempban()+"[" + pseudo + "] " + MessageTempban.getAlreadyTempban());
                    }
                }

            }
        }
        return false;
    }
}

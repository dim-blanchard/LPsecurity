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
import fr.loirelique.lpsecurity.Useful.DateAndTime;

public class CommandTempban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (cmd.getName().equalsIgnoreCase("tempban")) { // Si c'est la commande "banish" qui a été tapée:

                if (args.length >= 2) {
                    String pseudo = args[0];
                    String uuid = Main.plugin.getUuidHash(pseudo);

                    StringBuilder builder = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {

                        String ar = Main.plugin.sansAccent(args[i].replace(" ' ", " \' "));
                        builder.append(ar).append(" ");
                    }
                    String msg = builder.toString();
                    DateAndTime dateAndTime = new DateAndTime();
                    String years = args[3];
                    String months = args[4];
                    String dayOfMonths = args[5];
                    String hours = args[6];
                    String minutes = args[7];

                    if (dateAndTime.testDateEtTime(years, months, dayOfMonths, hours, minutes) == true) {
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
                            statement2_select.setInt(1,1);
                            statement2_select.setString(2,"temp_ban");
                            statement2_select.setString(3,"TEMP DE BANNISEMENT");
                            statement2_select.setString(4,"motif_tempban");
                            statement2_select.setString(5, msg);
                            statement2_select.setString(6, uuid);
                            statement2_select.executeUpdate();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    p.sendMessage(pseudo + " a étais bannie.");
                    Player player = Main.plugin.getListPlayer(uuid);
                    player.kickPlayer("Tu viens d'être bannie");
                    
                    }else if (dateAndTime.testDateEtTime(years, months, dayOfMonths, hours, minutes) == false) {
                        System.out.println("La commande n'a pas été executer.");
                    }     
                }

            }
        }
        return false;
    }
}

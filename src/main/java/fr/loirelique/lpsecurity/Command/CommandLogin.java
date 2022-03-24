package fr.loirelique.lpsecurity.Command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.String.ConfigBdd;
import fr.loirelique.lpsecurity.String.ConfigMessage;

public class CommandLogin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        {
            // C'est un joueur qui a effectué la commande
            if (sender instanceof Player) {
                Player p = (Player) sender;// On récupère le joueur.
                if (cmd.getName().equalsIgnoreCase("login")) { // Si c'est la commande "login" qui a été tapée:

                    if (args.length == 0) {

                    }

                    if (args.length == 1) {
                        String uuid = p.getUniqueId().toString();
                        String uuidfrombdd = "";

                        try (Connection connection_register = DriverManager.getConnection(
                                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                                        + ConfigBdd.getDatabase1()
                                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                            // -2 Fait une ou plusieure requete connection au jeux

                            String requet_Select_sql2 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
                            try (PreparedStatement statement2_select = connection_register
                                    .prepareStatement(requet_Select_sql2)) {
                                statement2_select.setObject(1, uuid);

                                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                                    while (resultat_requete_select.next()) {
                                        uuidfrombdd = resultat_requete_select.getString(2);

                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (uuid.equals(uuidfrombdd)) {
                            String args0 = args[0];
                            String password="";
                            try (Connection connection_register = DriverManager.getConnection(
                                    ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                                            + "/"
                                            + ConfigBdd.getDatabase1()
                                            + "?characterEncoding=latin1&useConfigs=maxPerformance",
                                    ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                                // -2 Fait une ou plusieure requete connection au jeux

                                String requet_Select_sql2 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
                                try (PreparedStatement statement2_select = connection_register
                                        .prepareStatement(requet_Select_sql2)) {
                                    statement2_select.setObject(1, uuid);

                                    try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                                        while (resultat_requete_select.next()) {
                                            password = resultat_requete_select.getString("password");

                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (args0.equals(password)) {

                                if (Main.plugin.getTaskRegisterTime(p) != null) { 
                                    Bukkit.getScheduler().cancelTask(Main.plugin.getTaskRegisterTime(p));
                                    Main.plugin.getTaskRegisterTimeRemove(p); 
                                   
                                }
                                if (Main.plugin.getTaskLoginTime(p) != null) {
                                    Bukkit.getScheduler().cancelTask(Main.plugin.getTaskLoginTime(p));
                                    Main.plugin.getTaskLoginTimeRemove(p); 
                                }
                                if (Main.plugin.getTaskBlockSpawn(p) != null) {
                                    Bukkit.getScheduler().cancelTask(Main.plugin.getTaskBlockSpawn(p));
                                    Main.plugin.getTaskBlockSpawnRemove(p); 
                                }
                            
                                ConfigMessage.sendAfterLogin(p);
                                

                            } else {
                                p.sendMessage(ConfigMessage.getMdpNotEqual());

                            }


                        } else {
                            p.sendMessage(ConfigMessage.getLoginError());
                        }

                    }

                }
            }

            return false;
        }

    }
}

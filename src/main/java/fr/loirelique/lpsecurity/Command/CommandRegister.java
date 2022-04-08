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

public class CommandRegister implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (cmd.getName().equalsIgnoreCase("register")) { // Si c'est la commande "register" qui a été tapée:

                if (args.length == 0) {

                }

                if (args.length == 2) {
                    String uuid = Main.plugin.getUuidHash(p);
                    String uuidRequet = "";

                    try (Connection connection_register = DriverManager.getConnection(
                            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                                    + ConfigBdd.getDatabase1()
                                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
                            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                        // -2 Fait une ou plusieure requete connection au jeux

                        String requet_Select_sql2 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
                        try (PreparedStatement statement3_select = connection_register
                                .prepareStatement(requet_Select_sql2)) {
                            statement3_select.setObject(1, uuid);

                            try (ResultSet resultat_requete_select = statement3_select.executeQuery()) {
                                while (resultat_requete_select.next()) {
                                    uuidRequet = resultat_requete_select.getString("uuid");

                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (uuid.equals(uuidRequet)) {
                        p.sendMessage(ConfigMessage.getErrorRegister());
                    } else {
                        String args0 = args[0];
                        String args1 = args[1];
                        if (args0.equals(args1) && args[0].length() >= 8 && args[1].length() >= 8) {

                            try (Connection connection_addPlayer = DriverManager.getConnection(
                                    ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                                            + "/"
                                            + ConfigBdd.getDatabase1()
                                            + "?characterEncoding=latin1&useConfigs=maxPerformance",
                                    ConfigBdd.getUser1(), ConfigBdd.getPass1())) {

                                String requet_insert_sql1 = "INSERT INTO " + ConfigBdd.getTable1()
                                        + " (uuid,pseudo,password) VALUES(?,?,?)";
                                try (PreparedStatement statement1_insert = connection_addPlayer
                                        .prepareStatement(requet_insert_sql1)) {
                                    String pseudo = p.getName();
                                    pseudo = pseudo.toLowerCase();
                                    pseudo = pseudo.replaceAll("\\s", "");
                                    String pass = Main.plugin.getHash(args0);
                                    statement1_insert.setString(1, uuid);
                                    statement1_insert.setString(2, pseudo);
                                    statement1_insert.setString(3,pass);
                                    statement1_insert.executeUpdate();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Bukkit.getScheduler().cancelTask(Main.plugin.getTaskRegisterTime(p));
                            Main.plugin.getTaskRegisterTimeRemove(p);
                            Main.plugin.setTaskLoginTime(p);
                            ConfigMessage.sendLogin(p);

                        } else {
                            p.sendMessage(ConfigMessage.getErrorRegisterPass());

                        }
                    }

                }

            }
        }

        return false;
    }

}

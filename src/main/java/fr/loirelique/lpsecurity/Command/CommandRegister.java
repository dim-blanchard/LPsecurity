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
import fr.loirelique.lpsecurity.String.MessageLogin;
import fr.loirelique.lpsecurity.String.MessageRegister;
import fr.loirelique.lpsecurity.Usefull.DataListIp;
import fr.loirelique.lpsecurity.Usefull.DataListPlayers;
import fr.loirelique.lpsecurity.Usefull.DataPlayersFiles;

public class CommandRegister implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (cmd.getName().equalsIgnoreCase("register")) { // Si c'est la commande "register" qui a été tapée:

                if (args.length == 2) {
                    String uuidPlayers = Main.plugin.getUuidHash(p);
                    String ipPlayers= p.getAddress().getHostString();
                    String bddUuid = "";

                    try (Connection connection_register = DriverManager.getConnection(
                            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                                    + ConfigBdd.getDatabase1()
                                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
                            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                        // -2 Fait une ou plusieure requete connection au jeux

                        String requet_Select_sql2 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
                        try (PreparedStatement statement3_select = connection_register
                                .prepareStatement(requet_Select_sql2)) {
                            statement3_select.setObject(1, uuidPlayers);

                            try (ResultSet resultat_requete_select = statement3_select.executeQuery()) {
                                while (resultat_requete_select.next()) {
                                    bddUuid = resultat_requete_select.getString("uuid");

                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        p.kickPlayer("La base de donné n'est pas en ligne merci de reitérer.");  
                    }

                    if (uuidPlayers.equals(bddUuid)) {
                        p.sendMessage(MessageRegister.getwrongRegister());
                    } else {
                        if (args[0].equals(args[1]) && args[0].length() >= 8 && args[1].length() >= 8) {

                            int ban = 0;
                            String motif_ban = "null";
                            String motif_unban = "null";

                            String temp_ban = "null";
                            String motif_tempban = "null";

                            int mute = 0;
                            String motif_mute = "null";
                            String motif_unmute = "null";

                            String temp_mute = "null";
                            String motif_tempmute = "null";

                            String motif_kick = "null";

                            int warn = 0;
                            String motif_warn = "null";
                            DataPlayersFiles.updateHistoriqueSanctions(uuidPlayers, ban, motif_ban, motif_unban,
                                    temp_ban, motif_tempban, mute, motif_mute, motif_unmute, temp_mute, motif_tempmute,
                                    motif_kick, warn, motif_warn, Main.plugin.dataPlayer);
                            DataListIp.setFile(uuidPlayers, ipPlayers,p);
                            try (Connection connection_addPlayer = DriverManager.getConnection(
                                    ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                                            + "/"
                                            + ConfigBdd.getDatabase1()
                                            + "?characterEncoding=latin1&useConfigs=maxPerformance",
                                    ConfigBdd.getUser1(), ConfigBdd.getPass1())) {

                                String requet_insert_sql1 = "INSERT INTO " + ConfigBdd.getTable1()
                                        + " (uuid,pseudo,password,historique_sanctions) VALUES(?,?,?,?)";
                                try (PreparedStatement statement1_insert = connection_addPlayer
                                        .prepareStatement(requet_insert_sql1)) {
                                    String pseudo = p.getName();
                                    pseudo = pseudo.toLowerCase();
                                    pseudo = pseudo.replaceAll("\\s", "");
                                    String pass = Main.plugin.getHash(args[0]);
                                    String historiqueSanctionDefault = "{\"mute\": 0,\"warn\": 0,\"ban\": 0,\"temp_ban\": \"null\", \"motif_ban\": \"null\", \"temp_mute\": \"null\", \"motif_kick\": \"null\", \"motif_mute\": \"null\", \"motif_warn\": \"null\", \"motif_unban\": \"null\", \"motif_unmute\": \"null\", \"motif_tempban\": \"null\", \"motif_tempmute\": \"null\"}";
                                    statement1_insert.setString(1, uuidPlayers);
                                    statement1_insert.setString(2, pseudo);
                                    statement1_insert.setString(3, pass);
                                    statement1_insert.setString(4, historiqueSanctionDefault);
                                    statement1_insert.executeUpdate();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (Main.plugin.getTaskRegisterTime(p) != null) {
                                Bukkit.getScheduler().cancelTask(Main.plugin.getTaskRegisterTime(p));
                                Main.plugin.getTaskRegisterTimeRemove(p);
                                Main.plugin.setTaskLoginTime(p);
                                MessageLogin.sendLogin(p);
                                errorCommande = true;
                            } else {
                                p.sendMessage(MessageRegister.getErrorRegister());
                                errorCommande = false;
                            }

                        } else {
                            p.sendMessage(MessageRegister.getwrongRegisterPass());
                            errorCommande = true;

                        }
                    }

                }
                if (errorCommande == true) {
                    errorCommande = true;
                } else if (errorCommande == false) {
                    errorCommande = false;
                    p.sendMessage(MessageRegister.getErrorRegister());
                }
            }
        }

        return errorCommande;
    }

}

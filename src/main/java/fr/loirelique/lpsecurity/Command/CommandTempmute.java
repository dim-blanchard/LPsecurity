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
import fr.loirelique.lpsecurity.String.MessageTempmute;
import fr.loirelique.lpsecurity.Usefull.DataListPlayers;
import fr.loirelique.lpsecurity.Usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.Usefull.DateAndTime;
import fr.loirelique.lpsecurity.Usefull.TestString;

public class CommandTempmute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (cmd.getName().equalsIgnoreCase("tempmute")) { // Si c'est la commande "banish" qui a été tapée:

                if (args.length >= 2) {
                    int mute = 2;

                    String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                    int donneTemps = 0;
                    String typeTemps = "";
                    String temp_mute = "";

                    if (TestString.isNumber(args[1]) == false) {
                        p.sendMessage("Le nombre de temps donner ne dois comporter que des chiffres.");
                        errorCommande = true;
                    } else if (TestString.isNumber(args[1]) == true) {
                        donneTemps = Integer.parseInt(args[1]);
                        typeTemps = args[2];
                        temp_mute = DateAndTime.getDateToString(donneTemps, typeTemps);

                        StringBuilder builder = new StringBuilder();
                        for (int i = 3; i < args.length; i++) {

                            String ar = Main.plugin.sansAccent(args[i].replace(" ' ", " \' "));
                            builder.append(ar).append(" ");
                        }
                        String motif_tempmute = builder.toString();

                        try (Connection connection_register = DriverManager.getConnection(
                                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                                        + ConfigBdd.getDatabase1()
                                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                            String requet_Select_sql2 = "SELECT historique_sanctions->>'$.mute' FROM "
                                    + ConfigBdd.getTable1() + " WHERE uuid=?";
                            try (PreparedStatement statement2_select = connection_register
                                    .prepareStatement(requet_Select_sql2)) {
                                statement2_select.setString(1, uuidPlayers);

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

                            mute = 1 ;
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
                                    statement2_select.setInt(2, mute);
                                    statement2_select.setString(3, "temp_mute");
                                    statement2_select.setString(4, temp_mute);
                                    statement2_select.setString(5, "motif_tempmute");
                                    statement2_select.setString(6, motif_tempmute);
                                    statement2_select.setString(7, uuidPlayers);
                                    statement2_select.executeUpdate();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            DataPlayersFiles.setMuteTempMuteAndMotif(uuidPlayers, mute, temp_mute, motif_tempmute, Main.plugin.dataPlayer);
                            p.sendMessage(MessageTempmute.setColorTempmute() + "[" + args[0] + "] "
                                    + MessageTempmute.getTempmute());
                            if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer) == true ) {
                            Player player = DataListPlayers.getObjectPlayers(uuidPlayers);                              
                            player.sendMessage("Mute temporaire:" + motif_tempmute);
                            }
                            errorCommande = true;

                        } else if (mute == 1) {
                            p.sendMessage(MessageTempmute.setColoralreadyTempmute() + "[" + args[0] + "] "
                                    + MessageTempmute.getAlreadyTempmute());
                            errorCommande = true;
                        }
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

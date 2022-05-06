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

public class CommandHistorique implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.*
            if (p.hasPermission("lpsecurity.historique")) {
                if (cmd.getName().equalsIgnoreCase("historique")) { // Si c'est la commande "banish" qui a été tapée:
                    errorCommande = false;
                    if (args.length == 1) {
                        String uuid = Main.plugin.getUuidHash(p);
                        String motif_ban = "null";
                        String motif_tempban = "null";
                        String motif_unban = "null";
                        String motif_kick = "null";
                        String motif_mute = "null";
                        String motif_warn = "null";
                        String motif_unmute = "null";
                        String motif_tempmute = "null";
                       
                        try (Connection connection_historique = DriverManager.getConnection(
                                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                                        + ConfigBdd.getDatabase1()
                                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                            // -2 Fait une ou plusieure requete connection au jeux
    
                            String requet_Select_sql3 = "SELECT * FROM " + ConfigBdd.getTable1() + "WHERE uuid=?";
                            try (PreparedStatement statement2_select = connection_historique
                                    .prepareStatement(requet_Select_sql3)) {
                                statement2_select.setString(1, uuid);
    
                                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                                    while (resultat_requete_select.next()) {
                                      //  motif_ban = resultat_requete_select.getString("historique_sanctions");
                                      
    
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        p.sendMessage(motif_ban);

                        errorCommande = true;
                    }

                }
            }else{
                p.sendMessage("Tu n'a pas les permission!");
                errorCommande = false;
            }

            if (errorCommande == true) {
                errorCommande = true;
            } else if (errorCommande == false) {
                errorCommande = false;
                p.sendMessage("la commande n'a pas était exécuter");
            }
        }

        return errorCommande;
    }
}

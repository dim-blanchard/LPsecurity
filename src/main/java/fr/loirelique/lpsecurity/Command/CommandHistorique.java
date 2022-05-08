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
            
                if (cmd.getName().equalsIgnoreCase("historique")) { // Si c'est la commande "banish" qui a été tapée:
                    errorCommande = false;
                    if (args.length == 1) {
                        String uuid = Main.plugin.getUuidHash(args[0]);
                        String motif_ban = "null";
                        String motif_tempban = "null";
                        String motif_unban = "null";
                        String motif_kick = "null";
                        String motif_mute = "null";
                        String motif_warn = "null";
                        String motif_unmute = "null";
                        String motif_tempmute = "null";
                        String ban = "0";
                        String warn = "0";
                        try (Connection connection_register = DriverManager.getConnection(
                            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                                    + ConfigBdd.getDatabase1()
                                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
                            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                        String requet_Select_sql2 = "SELECT   historique_sanctions->>'$.ban',historique_sanctions->>'$.warn',historique_sanctions->>'$.motif_ban',historique_sanctions->>'$.motif_tempban',historique_sanctions->>'$.motif_unban',historique_sanctions->>'$.motif_kick',historique_sanctions->>'$.motif_warn',historique_sanctions->>'$.motif_mute',historique_sanctions->>'$.motif_unmute',historique_sanctions->>'$.motif_tempmute' FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
                        try (PreparedStatement statement2_select = connection_register
                                .prepareStatement(requet_Select_sql2)) {
                            statement2_select.setString(1, uuid);

                            try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                                while (resultat_requete_select.next()) {
                                   // ban = resultat_requete_select.getInt("ban");
                                   motif_ban = resultat_requete_select.getString("historique_sanctions->>'$.motif_ban'");
                                   motif_tempban = resultat_requete_select.getString("historique_sanctions->>'$.motif_tempban'");
                                   motif_unban = resultat_requete_select.getString("historique_sanctions->>'$.motif_unban'");

                                   motif_mute = resultat_requete_select.getString("historique_sanctions->>'$.motif_mute'");
                                   motif_tempmute = resultat_requete_select.getString("historique_sanctions->>'$.motif_tempmute'");
                                   motif_unmute = resultat_requete_select.getString("historique_sanctions->>'$.motif_unmute'");

                                   motif_kick = resultat_requete_select.getString("historique_sanctions->>'$.motif_kick'");
                                   motif_warn = resultat_requete_select.getString("historique_sanctions->>'$.motif_warn'");

                                   ban = resultat_requete_select.getString("historique_sanctions->>'$.ban'");
                                   warn = resultat_requete_select.getString("historique_sanctions->>'$.warn'"); 
                                }   
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                        if (!ban.equals("0")) {
                            p.sendMessage("["+args[0]+"]"+" Ban: "+ban);
                        }
                        if (!warn.equals("0")) {
                            p.sendMessage("["+args[0]+"]"+" Warn: "+warn);
                        }
                        if (!motif_ban.equals("null")) {
                            p.sendMessage("["+args[0]+"]"+" Motif Ban: "+motif_ban);
                        }
                        if (!motif_tempban.equals("null")) {
                            p.sendMessage("["+args[0]+"]"+" Motif Tempban: "+motif_tempban);
                        }
                        if (!motif_unban.equals("null")) {
                            p.sendMessage("["+args[0]+"]"+" Motif Unban: "+motif_unban);
                        }

                        if (!motif_mute.equals("null")) {
                            p.sendMessage("["+args[0]+"]"+" Motif Mute: "+motif_mute);
                        }
                        if (!motif_tempmute.equals("null")) {
                            p.sendMessage("["+args[0]+"]"+" Motif Tempmute: "+motif_tempmute);
                        }
                        if (!motif_unmute.equals("null")) {
                            p.sendMessage("["+args[0]+"]"+" Motif Unmute: "+motif_unmute);
                        }

                        if (!motif_kick.equals("null")) {
                            p.sendMessage("["+args[0]+"]"+" Motif Kick: "+motif_kick);
                        }
                        if (!motif_warn.equals("null")) {
                            p.sendMessage("["+args[0]+"]"+" Motif Warn: "+motif_warn);
                        }

                        if(motif_ban.equals("null")&&motif_tempban.equals("null")&&motif_unban.equals("null")&&motif_mute.equals("null")&&motif_tempmute.equals("null")&&motif_unmute.equals("null")&&motif_kick.equals("null")&&motif_warn.equals("null"))
                        {
                            p.sendMessage("Le joueur n'a pas de motif de sanction archiver.");
                        }
                       
                        

                        errorCommande = true;
                    }

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

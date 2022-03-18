package fr.loirelique.lpsecurity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandRegister implements CommandExecutor {

    private String driver = Main.plugin.getConfigBdd("bdd.driver");
    private String host = Main.plugin.getConfigBdd("bdd.host");
    private String port = Main.plugin.getConfigBdd("bdd.port");
    private String data = Main.plugin.getConfigBdd("bdd.database");

    private String username = Main.plugin.getConfigBdd("bdd.user");
    private String password = Main.plugin.getConfigBdd("bdd.pass");

    private String url = driver + "://" + host + ":" + port + "/" + data
            + "?characterEncoding=latin1&useConfigs=maxPerformance";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("register")) { // Si c'est la commande "register" qui a été tapée:

            if (sender instanceof Player) {
                // C'est un joueur qui a effectué la commande
                Player p = (Player) sender;// On récupère le joueur.
                
                try (Connection connection_register = DriverManager.getConnection(url, username, password)) {
                    // -2 Fait une ou plusieure requete connection au jeux

                    String requet_Select_sql2 = "SELECT * FROM pf8kr9g9Playersauthentification WHERE uuid=?";
                    try (PreparedStatement statement2_select = connection_register
                            .prepareStatement(requet_Select_sql2)) {
                        String uuid = p.getUniqueId().toString();
                        statement2_select.setObject(1, uuid);
                        
                        try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                            while(resultat_requete_select.next()) {
                                p.sendMessage("C'est okay register");
                                p.sendMessage(resultat_requete_select.getString(3));
                                
                            }
                            System.out.println("Pas d'uuid correspondant");

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                // C'est la console du serveur qui a effectuée la commande.
            }

            return true;// On renvoie "true" pour dire que la commande était valide
        }
        return false;// Si une autre commande a été tapée on renvoie "false" pour dire qu'elle
                     // n'était pas valide.

    }

}
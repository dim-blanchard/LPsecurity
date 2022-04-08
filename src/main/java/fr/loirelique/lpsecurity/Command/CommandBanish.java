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

public class CommandBanish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (cmd.getName().equalsIgnoreCase("banish")) { // Si c'est la commande "banish" qui a été tapée:

                if (args.length == 0) {

                }
                if (args.length == 1) {

                }

                if (args.length == 2) {
                    String pseudo = args[0];
                    String uuid_testeur = Main.plugin.getUuidHash(pseudo);

                    int ban = Integer.parseInt(args[1]);

                    try (Connection connection_update = DriverManager.getConnection(
                            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                                    + ConfigBdd.getDatabase1()
                                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
                            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                        String requet_Select_sql2 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
                        try (PreparedStatement statement2_select = connection_update
                                .prepareStatement(requet_Select_sql2)) {
                            statement2_select.setString(1, uuid_testeur);

                            try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                                if (resultat_requete_select.next()) {
                                    String requet_Update_sql3 = "UPDATE " + ConfigBdd.getTable1()
                                            + " SET ban=? WHERE uuid=?";
                                    try (PreparedStatement statement3_update = connection_update
                                            .prepareStatement(requet_Update_sql3)) {
                                        statement3_update.setInt(1, ban);
                                        statement3_update.setString(2, uuid_testeur);
                                        statement3_update.executeUpdate();
                                    }
                                }
                            }
                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    if(ban == 0)
                    {
                        p.sendMessage(pseudo+" a étais débannie.");
                    
                    }

                    
                    if(ban == 1)
                    {
                        p.sendMessage(pseudo+" a étais bannie.");
                        Player player = Main.plugin.getListPlayer(uuid_testeur);
                        player.kickPlayer("Tu viens d'être bannie");
                    }
                }

            }
        }

        return false;
    }

}

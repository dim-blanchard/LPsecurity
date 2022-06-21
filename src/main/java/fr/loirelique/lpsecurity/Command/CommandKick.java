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
import fr.loirelique.lpsecurity.Usefull.DataListPlayers;
import fr.loirelique.lpsecurity.Usefull.DataPlayersFiles;

public class CommandKick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("kick")) { // Si c'est la commande "kick" qui a été tapée:
                if (args.length == 1) {                 
                    String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {

                        String ar = Main.plugin.sansAccent(args[i].replace(" ' ", " \' "));
                        builder.append(ar).append(" ");
                    }
                    String motif_kick = builder.toString();


                        try (Connection connection_reset = DriverManager.getConnection(
                            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                                    + "/"
                                    + ConfigBdd.getDatabase1()
                                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
                            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {

                        String requet_insert_sql1 = "UPDATE " + ConfigBdd.getTable1()
                                + " SET historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,''))  WHERE uuid=?";
                        try (PreparedStatement statement1_insert = connection_reset
                                .prepareStatement(requet_insert_sql1)) {
                            
                            statement1_insert.setString(1, "motif_kick");
                            statement1_insert.setString(2, motif_kick);

                            statement1_insert.setString(3, uuidPlayers);
                            statement1_insert.executeUpdate();
                        }
                        p.sendMessage("Le joueur "+ args[0]+" à était exclue de la communauté de serveur.");
                        if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer)==true) {
                            Player player = DataListPlayers.getObjectPlayers(uuidPlayers);
                            player.kickPlayer("Motif Kick: "+ motif_kick); 
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    p.sendMessage("Commande non executer");
                }
            }
        }else{
            System.out.println("Que les joueurs peuvent executer cette commande.");
        }

        return errorCommande;
    }

}
    
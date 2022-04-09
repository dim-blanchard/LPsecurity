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

public class CommandUnban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (cmd.getName().equalsIgnoreCase("unban")) { // Si c'est la commande "banish" qui a été tapée:

                if (args.length >= 2) {
                    String pseudo = args[0];
                    String uuid = Main.plugin.getUuidHash(pseudo);

                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }
                    String msg = builder.toString();
                    // System.out.println(msg);

                    try (Connection connection_update = DriverManager.getConnection(
                            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
                                    ConfigBdd.getPort()
                                    + "/"
                                    + ConfigBdd.getDatabase1()
                                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
                            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                        String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() +
                                " SET ban=?, historique=? WHERE uuid=?";
                        try (PreparedStatement statement2_select = connection_update
                                .prepareStatement(requet_Update_sql2)) {
                            statement2_select.setInt(1,0);
                            statement2_select.setString(2,"Unban: "+msg);
                            statement2_select.setString(3, uuid);
                            statement2_select.executeUpdate();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    p.sendMessage(pseudo + " a étais débannie.");
                }

            }
        }
        return false;
    }
}

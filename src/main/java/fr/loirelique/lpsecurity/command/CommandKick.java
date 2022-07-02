package fr.loirelique.lpsecurity.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.sqlrequest.RequestDatabase;
import fr.loirelique.lpsecurity.usefull.DataListPlayers;
import fr.loirelique.lpsecurity.usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.usefull.MotifBuilder;

public class CommandKick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("lpsecurity.kick")) { 
                if (cmd.getName().equalsIgnoreCase("kick")) { // Si c'est la commande "kick" qui a été tapée:
                    if (args.length >= 2) {                 
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                        String motif_kick = MotifBuilder.getMotif(args, 1);

                        RequestDatabase.upHS(uuidPlayers,"motif_kick", motif_kick);

                        p.sendMessage("Le joueur "+ args[0]+" à était exclue de la communauté de serveur.");
                        if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer)==true){Player player = DataListPlayers.getObjectPlayers(uuidPlayers);player.kickPlayer("Motif Kick: "+ motif_kick);}
                        return true;
                    }else{p.sendMessage("La commande n'a pas était exécuter");return false;}                   
                }
            }else{p.sendMessage("Pas la permission");return true;} 
        }else{return false;}
    return false;
    }
}
    
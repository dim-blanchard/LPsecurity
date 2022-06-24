package fr.loirelique.lpsecurity.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.Request.RequestResetHistorique;

/**
 * CommandeResetHistorique
 */
public class CommandResetHistorique implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("lpsecurity.resethistorique")){   
                if (cmd.getName().equalsIgnoreCase("resethistorique")) { // Si c'est la commande "resethistorique" qui a été tapée:
                    if (args.length == 1) {                 
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                        //Requet Sql Update.
                        RequestResetHistorique.setResetHistorique(uuidPlayers);
                        p.sendMessage("Historique de sanctions "+ args[0]+" reset.");
                        return true;
                    }else{p.sendMessage("La commande n'a pas était exécuter");return false;}
                }
            }else{p.sendMessage("Pas la permission");return true;}
        }else{return false;}   
        return false;
    }

}
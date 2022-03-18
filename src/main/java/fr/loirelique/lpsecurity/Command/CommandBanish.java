package fr.loirelique.lpsecurity.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBanish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("banish")) { // Si c'est la commande "banish" qui a été tapée:

        if (sender instanceof Player) {
            // C'est un joueur qui a effectué la commande
            Player p = (Player) sender;// On récupère le joueur.
            p.sendMessage("C'est okay banish");

        } else {
            // C'est la console du serveur qui a effectuée la commande.
        }
        
        return true;// On renvoie "true" pour dire que la commande était valide
    }
    return false;// Si une autre commande a été tapée on renvoie "false" pour dire qu'elle
                 // n'était pas valide.
    }

}

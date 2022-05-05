package fr.loirelique.lpsecurity.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHistorique implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("LP.security.historique")) {
                if (cmd.getName().equalsIgnoreCase("historique")) { // Si c'est la commande "banish" qui a été tapée:
                    System.out.println("test permission");
                    errorCommande = true;
                }
            }else{
                errorCommande = true;
                p.sendMessage("Pas la permission.");
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

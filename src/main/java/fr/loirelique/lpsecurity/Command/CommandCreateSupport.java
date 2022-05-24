package fr.loirelique.lpsecurity.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Useful.List.ListSupport;

public class CommandCreateSupport implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.ban")) {
                if (cmd.getName().equalsIgnoreCase("createsupport")) {
                    if (args.length >= 2) {
                        String nom = args[0];
                        ListSupport.creatSupport(nom, p);

                        if (errorCommande == true) {
                            errorCommande = true;
                        } else if (errorCommande == false) {
                            errorCommande = false;

                        }

                    }
                } else {
                    p.sendMessage("Pas la permission.");
                }
            }
           
        }
        return errorCommande;

    }

}

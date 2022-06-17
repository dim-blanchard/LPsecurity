package fr.loirelique.lpsecurity.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;

public class CommandCreateSupport implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean errorCommande = false;
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("lpsecurity.createsupport")) {
                if (cmd.getName().equalsIgnoreCase("createsupport")) {
                    String uuidPlayers = Main.plugin.getUuidHash(p);
                    if (args.length >= 2) {
                        String nomSupport = args[0];
                        errorCommande = ListSupport.creatSupport(nomSupport, p, uuidPlayers);
                    } else {
                        errorCommande = false;
                    }
                } else {
                    p.sendMessage("Pas la permission.");
                }
            }
        }
        if (errorCommande == true) {
            errorCommande = true;
        } else if (errorCommande == false) {
            errorCommande = false;
        }
        return errorCommande;
    }

}

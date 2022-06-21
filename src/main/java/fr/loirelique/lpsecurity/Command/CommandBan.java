package fr.loirelique.lpsecurity.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.Request.RequestBan;
import fr.loirelique.lpsecurity.String.MessageBan;
import fr.loirelique.lpsecurity.Usefull.DataListPlayers;
import fr.loirelique.lpsecurity.Usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.Usefull.MotifBuilder;

public class CommandBan implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.ban")) {
                if (cmd.getName().equalsIgnoreCase("ban")) { // Si c'est la commande "banish" qui a été tapée:
                    if (args.length >= 2) { 
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                        int ban = 2;
                        //Request SQL select. 
                        ban = RequestBan.getBan(uuidPlayers);
                        if (ban == 0) {
                            //Motif Builder.
                            String motif_ban = MotifBuilder.getMotif(args);
                            //Request SQL Update.
                            RequestBan.setBanAndMotif(uuidPlayers, motif_ban);
                            //Exclusion du joueur.
                            if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer) == true ){Player player = DataListPlayers.getObjectPlayers(uuidPlayers);player.kickPlayer("Bannie:" + motif_ban);}
                            //Message d'exclusion
                            p.sendMessage(MessageBan.setColorBan() + "[" + args[0] + "] " + MessageBan.getBan());
                            //True pour commande bien éxécuté.
                            return true;
                        }
                        if (ban == 1) {p.sendMessage(MessageBan.setColorAlreadyBan() + "[" + args[0] + "] " + MessageBan.getAlreadyBan());return true;}
                    }else{p.sendMessage(MessageBan.setColorErrorBan() + MessageBan.getErrorBan()); return true;}
                }
            }else{p.sendMessage("Pas la permission.");return true;}
        }else{return false;}   
    return false; 
    }
}

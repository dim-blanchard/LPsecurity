package fr.loirelique.lpsecurity.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.Request.RequestBan;
import fr.loirelique.lpsecurity.Request.RequestUnban;
import fr.loirelique.lpsecurity.String.MessageUnban;
import fr.loirelique.lpsecurity.Usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.Usefull.MotifBuilder;

public class CommandUnban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.unban")) {
                if (cmd.getName().equalsIgnoreCase("unban")) { // Si c'est la commande "banish" qui a été tapée:
                    if (args.length >= 2) {
                        int ban = 2;
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);      
                        String motif_unban = MotifBuilder.getMotif(args,1);
                        //Request Sql Select.
                        ban = RequestBan.getBan(uuidPlayers);
                        if (ban == 1) {
                            //Request Sql Update.
                            RequestUnban.setUnbanAndMotif(uuidPlayers, motif_unban);
                            //Data Player Update
                            DataPlayersFiles.setUnbanAndMotif(uuidPlayers, motif_unban);
                            //Valid Message Unban.
                            p.sendMessage(MessageUnban.setColorUnban() + "[" + args[0] + "] " + MessageUnban.getUnban());
                            return true;
                        }else if (ban == 0) {p.sendMessage(MessageUnban.setColorAlreadyUnban() + "[" + args[0] + "] "+ MessageUnban.getAlreadyUnban());return true;}
                    }else {p.sendMessage(MessageUnban.setColorErrorUnban() + MessageUnban.getErrorUnban());return false;}
                }
            }
        }else{return false;}
        return false;
    }
}

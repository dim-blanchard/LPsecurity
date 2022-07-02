package fr.loirelique.lpsecurity.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.sqlrequest.RequestDatabase;
import fr.loirelique.lpsecurity.string.MessageUnmute;
import fr.loirelique.lpsecurity.usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.usefull.MotifBuilder;

public class CommandUnmute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.unmute")) {
                if (cmd.getName().equalsIgnoreCase("unmute")) { // Si c'est la commande "banish" qui a été tapée:
                    if (args.length >= 2) {
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                        //Motif Bulder.
                        String motif_unmute = MotifBuilder.getMotif(args, 1);
                        //Request Sql Select.
                        RequestDatabase request = new RequestDatabase();
                        request.getHS(uuidPlayers);
                        int mute = request.getMute();
                        if (mute == 1) {
                            //Request Sql Update.
                            RequestDatabase.upHS(uuidPlayers, "motif_unmute", "null");
                            RequestDatabase.upHS(uuidPlayers, "mute", 0);
                            //Data Player Update.
                            DataPlayersFiles.setUnmuteAndMotif(uuidPlayers, motif_unmute);
                            //Valid Message.
                            p.sendMessage(MessageUnmute.setColorUnmute() + "[" +  args[0] + "] " + MessageUnmute.getUnmute());
                            return true;
                        }else if(mute == 0){p.sendMessage(MessageUnmute.setColorAlreadyUnmute() + "[" +  args[0] + "] " + MessageUnmute.getAlreadyUnmute());return true;}
                    }else{p.sendMessage(MessageUnmute.setColorErrorUnmute() + MessageUnmute.getErrorUnmute());return false;}
                }
            }else{p.sendMessage("Pas la permission"); return true;}
        }else{return false;}
    return false;
    }

}
        



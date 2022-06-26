package fr.loirelique.lpsecurity.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.sqlrequest.RequestDatabase;
import fr.loirelique.lpsecurity.string.MessageMute;
import fr.loirelique.lpsecurity.usefull.DataListPlayers;
import fr.loirelique.lpsecurity.usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.usefull.MotifBuilder;

public class CommandMute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.mute")) {
                if (cmd.getName().equalsIgnoreCase("mute")){ // Si c'est la commande "mute" qui a été tapée:
                    if (args.length >= 2) {
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                        //Request Sql Select
                        RequestDatabase request = new RequestDatabase();
                        request.getHS(uuidPlayers);
                        int mute = request.getMute();
                        if (mute == 0) {
                            ////Motif Builder.
                            String motif_mute = MotifBuilder.getMotif(args, 1);
                            //Request Sql Update.
                            RequestDatabase.upHS(uuidPlayers, "motif_mute", motif_mute);
                            RequestDatabase.upHS(uuidPlayers, "mute", 1);
                            //Data Player Update.
                            DataPlayersFiles.setMuteAndMotif(uuidPlayers,motif_mute);
                            //Valid Message.
                            p.sendMessage(MessageMute.setColorMute() + "[" + args[0] + "] " + MessageMute.getMute());
                            //Mute player.
                            if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer)==true) {Player player = DataListPlayers.getObjectPlayers(uuidPlayers);player.sendMessage("Mute: " + motif_mute);}
                            return true;
                        }else if(mute == 1){p.sendMessage(MessageMute.setColorAlreadyMute() + "[" + args[0] + "] " + MessageMute.getAlreadyMute());return true;}
                    }else{p.sendMessage(MessageMute.setColorErrorMute() + MessageMute.getErrorMute());return false;}
                }
            }else {p.sendMessage("Pas la permission."); return true;}
        }else{return false;}
        return false;
    }
}

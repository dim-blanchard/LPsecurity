package fr.loirelique.lpsecurity.Command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.Request.RequestMute;
import fr.loirelique.lpsecurity.String.MessageMute;
import fr.loirelique.lpsecurity.Usefull.DataListPlayers;
import fr.loirelique.lpsecurity.Usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.Usefull.MotifBuilder;

public class CommandMute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.mute")) {
                if (cmd.getName().equalsIgnoreCase("mute")) { // Si c'est la commande "banish" qui a été tapée:
                    if (args.length >= 2) {
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                        int mute = 2;
                        //Request Sql Select
                        mute = RequestMute.getMute(uuidPlayers);
                        if (mute == 0) {
                            ////Motif Builder.
                            String motif_mute = MotifBuilder.getMotif(args, 1);
                            //Request Sql Update.
                            RequestMute.setMuteAndMotif(uuidPlayers, motif_mute);
                            //Data Player Update.
                            DataPlayersFiles.setMuteAndMotif(uuidPlayers,motif_mute);
                            //Valid Message.
                            p.sendMessage(MessageMute.setColorMute() + "[" + args[0] + "] " + MessageMute.getMute());
                            //Mute player.
                            if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer)==true) {Player player = DataListPlayers.getObjectPlayers(uuidPlayers);player.sendMessage("Mute: " + motif_mute);}
                            return true;
                        }else if(mute == 1){p.sendMessage(MessageMute.setColorAlreadyMute() + "[" + args[0] + "] " + MessageMute.getAlreadyMute());return true;}
                    }else{p.sendMessage(MessageMute.setColorErrorMute() + MessageMute.getErrorMute());return false;}
                }else {p.sendMessage(MessageMute.setColorErrorMute() + MessageMute.getErrorMute());return false;}
            }else {p.sendMessage("Pas la permission."); return true;}
        }else{return false;}
        return false;
    }
}

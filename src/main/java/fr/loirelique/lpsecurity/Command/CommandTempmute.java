package fr.loirelique.lpsecurity.Command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.Request.RequestMute;
import fr.loirelique.lpsecurity.Request.RequestTempmute;
import fr.loirelique.lpsecurity.String.MessageTempmute;
import fr.loirelique.lpsecurity.Usefull.DataListPlayers;
import fr.loirelique.lpsecurity.Usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.Usefull.DateAndTime;
import fr.loirelique.lpsecurity.Usefull.MotifBuilder;
import fr.loirelique.lpsecurity.Usefull.TestString;

public class CommandTempmute implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.tempmute")) {
                if (cmd.getName().equalsIgnoreCase("tempmute")) { // Si c'est la commande "banish" qui a été tapée:
                    if (args.length >= 3) {
                        int mute = 2;
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                        int donneTemps = 0;
                        String typeTemps = "";
                        String temp_mute = "";
                        if (TestString.isNumber(args[1]) == false){p.sendMessage("Le nombre de temps donner ne dois comporter que des chiffres.");return true;
                        }else if (TestString.isNumber(args[1]) == true) {
                            donneTemps = Integer.parseInt(args[1]);
                            typeTemps = args[2];
                            temp_mute = DateAndTime.getDateToString(donneTemps, typeTemps);
                            //Motif Builder.
                            String motif_tempmute = MotifBuilder.getMotif(args, 3);
                            //Request Sql Select.
                            mute = RequestMute.getMute(uuidPlayers);
                            if (mute == 0) {
                                //Request Sql Update.
                                RequestTempmute.setMuteAndTempMuteMotif(uuidPlayers, temp_mute, motif_tempmute);
                                //Data Player Update.
                                DataPlayersFiles.setMuteTempMuteAndMotif(uuidPlayers ,temp_mute, motif_tempmute);
                                //Valid Message.
                                p.sendMessage(MessageTempmute.setColorTempmute() + "[" + args[0] + "] "+ MessageTempmute.getTempmute());
                                //Mute Message.
                                if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer) == true ) {Player player = DataListPlayers.getObjectPlayers(uuidPlayers);player.sendMessage("Mute temporaire:" + motif_tempmute);}
                                return true;
                            }else if (mute == 1) {p.sendMessage(MessageTempmute.setColoralreadyTempmute() + "[" + args[0] + "] "+ MessageTempmute.getAlreadyTempmute());return true;} 
                        }
                    }else {p.sendMessage(MessageTempmute.setColorErrorTempmute() + MessageTempmute.getErrorTempmute());return false;}
                }else {p.sendMessage(MessageTempmute.setColorErrorTempmute() + MessageTempmute.getErrorTempmute());return false;}
            }else{p.sendMessage("Pas la permission"); return true;}
        }else{return false;}
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 2) {
            return DateAndTime.getListNumber();
        }else if (args.length == 3) {
            return DateAndTime.getListTypeTemps();
        }else if (args.length == 4) {
            return MessageTempmute.getListDefaultReasonMessage();
        }
        return null;
    }
}

  

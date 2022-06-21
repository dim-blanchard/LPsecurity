package fr.loirelique.lpsecurity.Command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.Request.RequestBan;
import fr.loirelique.lpsecurity.Request.RequestTempban;
import fr.loirelique.lpsecurity.String.MessageTempban;
import fr.loirelique.lpsecurity.Usefull.DataListPlayers;
import fr.loirelique.lpsecurity.Usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.Usefull.DateAndTime;
import fr.loirelique.lpsecurity.Usefull.MotifBuilder;
import fr.loirelique.lpsecurity.Usefull.TestString;

public class CommandTempban implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.tempban")) {
                if (cmd.getName().equalsIgnoreCase("tempban")) { // Si c'est la commande "banish" qui a été tapée:
                    if (args.length >= 2) {
                        int ban = 2;
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);

                        if (TestString.isNumber(args[1])==false) {p.sendMessage("Le nombre de temps donner ne dois comporter que des chiffres.");return true;
                        }else if(TestString.isNumber(args[1])==true) {
                            int donneTemps = Integer.parseInt(args[1]);
                            String typeTemps = args[2];                       
                            if (typeTemps.length()==0){
                                p.sendMessage(MessageTempban.setColorErrorTempban() + MessageTempban.getErrorTempban());
                                return false;
                            }else{
                            String temp_ban = DateAndTime.getDateToString(donneTemps, typeTemps);
                            String motif_tempban = MotifBuilder.getMotif(args, 3);
                            
                            //Request Sql Select.
                            ban = RequestBan.getBan(uuidPlayers);
                                if (ban == 0) {
                                    if (motif_tempban.length()==0) {
                                        p.sendMessage(MessageTempban.setColorErrorTempban() + MessageTempban.getErrorTempban());return false;
                                    }else{
                                    //Request Sql Update.
                                    RequestTempban.setBanAndTempBanMotif(uuidPlayers, motif_tempban, temp_ban);
                                    //Data Player Update.
                                    DataPlayersFiles.setBanAndTempBanMotif(uuidPlayers, motif_tempban , temp_ban);
                                    //Valid Message Tempban. 
                                    p.sendMessage(MessageTempban.setColorTempban() + "[" + args[0] + "] "+ MessageTempban.getTempban());
                                    //Message Kick
                                    if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer) == true ) {
                                        Player player = DataListPlayers.getObjectPlayers(uuidPlayers);                              
                                        player.kickPlayer("Banniessement temporaire: " + motif_tempban);
                                    }
                                    return true;  
                                    }                             
                                }else if(ban == 1){p.sendMessage(MessageTempban.setColoralreadyTempban() + "[" + args[0] + "] "+ MessageTempban.getAlreadyTempban());return true;}
                        }
                            }
         
                    }else{p.sendMessage(MessageTempban.setColorErrorTempban() + MessageTempban.getErrorTempban());return false;}
                }
            }
        }else{return false;}
    return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 3) {
            return DateAndTime.getListTypeTemps();
        }
        return null;
    }
}

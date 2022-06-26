package fr.loirelique.lpsecurity.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.sqlrequest.RequestDatabase;
import fr.loirelique.lpsecurity.string.MessageTempban;
import fr.loirelique.lpsecurity.usefull.DataListPlayers;
import fr.loirelique.lpsecurity.usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.usefull.DateAndTime;
import fr.loirelique.lpsecurity.usefull.MotifBuilder;
import fr.loirelique.lpsecurity.usefull.TestString;

public class CommandTempban implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.tempban")) {
                if (cmd.getName().equalsIgnoreCase("tempban")) { // Si c'est la commande "banish" qui a été tapée:
                    if (args.length >= 3) {  
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                        
                        if (TestString.isNumber(args[1])==false) {p.sendMessage("§dLe nombre de temps donner ne dois comporter que des chiffres.");return true;
                        }else if(TestString.isNumber(args[1])==true) {
                            RequestDatabase request = new RequestDatabase();
                            request.getHS(uuidPlayers);
                            int donneTemps = Integer.parseInt(args[1]);
                            String typeTemps = args[2];                       
                            String temp_ban = DateAndTime.getDateToString(donneTemps, typeTemps);
                            //Motif builder
                            String motif_tempban = MotifBuilder.getMotif(args, 3);
                            if (temp_ban.equals("error")==true) {p.sendMessage(MessageTempban.setColorErrorTempban() + MessageTempban.getErrorTempban());return false;
                            }else if (motif_tempban.length()==0) {p.sendMessage(MessageTempban.setColorErrorTempban() + MessageTempban.getErrorTempban());return false;
                            }else{
                                //Request Sql Select.
                               int ban = request.getBan();
                                    if (ban == 0) {
                                        //Request Sql Update.
                                        RequestDatabase.upHS(uuidPlayers, "motif_tempban", motif_tempban);
                                        RequestDatabase.upHS(uuidPlayers, "tempban", temp_ban);
                                        RequestDatabase.upHS(uuidPlayers, "ban", 1);
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
                                    }else if(ban == 1){p.sendMessage(MessageTempban.setColoralreadyTempban() + "[" + args[0] + "] "+ MessageTempban.getAlreadyTempban());return true;}                       
                            }
                        }
         
                    }else{p.sendMessage(MessageTempban.setColorErrorTempban() + MessageTempban.getErrorTempban());return false;}
                }
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
            return MessageTempban.getListDefaultReasonMessage();
        }
        return null;
    }
}

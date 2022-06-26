package fr.loirelique.lpsecurity.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.sqlrequest.RequestDatabase;
import fr.loirelique.lpsecurity.string.MessageTempmute;
import fr.loirelique.lpsecurity.usefull.DataListPlayers;
import fr.loirelique.lpsecurity.usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.usefull.DateAndTime;
import fr.loirelique.lpsecurity.usefull.MotifBuilder;
import fr.loirelique.lpsecurity.usefull.TestString;

public class CommandTempmute implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;// On récupère le joueur.
            if (p.hasPermission("lpsecurity.tempmute")) {
                if (cmd.getName().equalsIgnoreCase("tempmute")) { // Si c'est la commande "tempmute" qui a été tapée:
                    if (args.length >= 3) {
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);

                        if (TestString.isNumber(args[1])==false) {p.sendMessage("§dLe nombre de temps donner ne dois comporter que des chiffres.");return true;
                        }else if(TestString.isNumber(args[1])==true) {
                            int donneTemps = Integer.parseInt(args[1]);
                            String typeTemps = args[2];                       
                            String temp_mute = DateAndTime.getDateToString(donneTemps, typeTemps);
                            //Motif builder
                            String motif_tempmute = MotifBuilder.getMotif(args, 3);
                            if (temp_mute.equals("error")==true) {p.sendMessage(MessageTempmute.setColorErrorTempmute() + MessageTempmute.getErrorTempmute());return false;
                            }else if (motif_tempmute.length()==0) {p.sendMessage(MessageTempmute.setColorErrorTempmute() + MessageTempmute.getErrorTempmute());return false;
                            }else{
                                //Request Sql Select.
                                RequestDatabase request = new RequestDatabase();
                                request.getHS(uuidPlayers);
                                int mute = request.getMute();
                                    if (mute == 0) {
                                        //Request Sql Update.
                                        RequestDatabase.upHS(uuidPlayers, "motif_tempmute", motif_tempmute);
                                        RequestDatabase.upHS(uuidPlayers, "tempmute", temp_mute);
                                        RequestDatabase.upHS(uuidPlayers, "mute", 1);
                                        //Data Player Update.
                                        DataPlayersFiles.setMuteTempMuteAndMotif(uuidPlayers, temp_mute, motif_tempmute);
                                         //Valid Message Tempmute. 
                                        p.sendMessage(MessageTempmute.setColorTempmute() + "[" + args[0] + "] "+ MessageTempmute.getTempmute());
                                        //Message Mute.
                                        if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer) == true ) {
                                            Player player = DataListPlayers.getObjectPlayers(uuidPlayers);                              
                                            player.sendMessage("Mute temporaire: " + motif_tempmute);
                                        }
                                        return true;                              
                                    }else if(mute == 1){p.sendMessage(MessageTempmute.setColoralreadyTempmute() + "[" + args[0] + "] "+ MessageTempmute.getAlreadyTempmute());return true;}                       
                            }
                        }
         
                    }else{p.sendMessage(MessageTempmute.setColorErrorTempmute() + MessageTempmute.getErrorTempmute());return false;}
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
            return MessageTempmute.getListDefaultReasonMessage();
        }
        return null;
    }
}


  

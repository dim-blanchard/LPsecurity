package fr.loirelique.lpsecurity.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.sqlrequest.RequestDatabase;
import fr.loirelique.lpsecurity.string.MessageLogin;
import fr.loirelique.lpsecurity.string.MessageRegister;
import fr.loirelique.lpsecurity.usefull.DataListIp;
import fr.loirelique.lpsecurity.usefull.DataPlayersFiles;

public class CommandRegister implements CommandExecutor {

    @Override @Deprecated
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("lpsecurity.login")) {
                if (cmd.getName().equalsIgnoreCase("register")) { // Si c'est la commande "register" qui a été tapée:
                    if (args.length == 2) {
                        String uuidPlayers = Main.plugin.getUuidHash(p);
                        String password = Main.plugin.getHash(args[0]);
                        String ipPlayers= p.getAddress().getHostString();
                        String pseudo = p.getName();
                        pseudo = pseudo.toLowerCase();
                        pseudo = pseudo.replaceAll("\\s", "");               
                        
                        RequestDatabase requet = new RequestDatabase();
                        //If the DataBase Was Online
                        if(RequestDatabase.isOnline()==false){p.kickPlayer("La base de donné n'est pas en ligne merci de reitérer."); return false;}
                        else{
                            requet.getColumn(uuidPlayers);
                            String uuidPlayersBdd = requet.getUuidPlayers();

                            if (uuidPlayers.equals(uuidPlayersBdd)){p.sendMessage(MessageRegister.getwrongRegister());} 
                            else {
                                if (args[0].equals(args[1]) && args[0].length() >= 8 && args[1].length() >= 8) {
                                    //Data Players Files Update
                                    DataPlayersFiles.setHistoriqueSanctions(uuidPlayers, 0, "null", "null",
                                    "null", "null", 0, "null", "null", "null", "null",
                                    "null", 0, "null");
                                    //??
                                    DataListIp.setFile(uuidPlayers, ipPlayers,p);

                                    //Request Sql Insert.
                                    RequestDatabase.insertNewPlayers(uuidPlayers, pseudo, password);
                                    
                                    if (Main.plugin.getTaskRegisterTime(p) != null) {Bukkit.getScheduler().cancelTask(Main.plugin.getTaskRegisterTime(p)); Main.plugin.getTaskRegisterTimeRemove(p);Main.plugin.setTaskLoginTime(p);MessageLogin.sendLogin(p);return true;}
                                    else{ p.sendMessage(MessageRegister.getErrorRegister());return false;}
                                }else{p.sendMessage(MessageRegister.getwrongRegisterPass());return true;}
                            }
                        }
                    }else{p.sendMessage(MessageRegister.getErrorRegister());return false;}
                }
            }else{p.sendMessage("Pas la permission");return true;}
        }else{return false;} 
    return false;
    }

   
}



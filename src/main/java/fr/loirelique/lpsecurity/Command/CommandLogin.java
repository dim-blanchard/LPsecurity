package fr.loirelique.lpsecurity.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.sqlrequest.RequestDatabase;
import fr.loirelique.lpsecurity.string.MessageLogin;
import fr.loirelique.lpsecurity.usefull.DataPlayersFiles;

public class CommandLogin implements CommandExecutor {
    
    @Override @Deprecated
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("lpsecurity.login")) {
                if (cmd.getName().equalsIgnoreCase("login")) { // Si c'est la commande "login" qui a été tapée:
                    if (args.length == 1) {
                        String uuidPlayers = Main.plugin.getUuidHash(p);
                        String password = Main.plugin.getHash(args[0]);

                        //Request Sql Select.
                        RequestDatabase requet = new RequestDatabase();
                        requet.getColumn(uuidPlayers);
                        String uuidPlayersbdd = requet.getUuidPlayers();
                        String passwordBdd = requet.getPassword();
                        
                        if (uuidPlayers.equals(uuidPlayersbdd)) {
                            if (DataPlayersFiles.getIsLogin(uuidPlayers, Main.plugin.dataPlayer)==false) {
                                if (password.equals(passwordBdd)) {
                                    DataPlayersFiles.setIsLogin(uuidPlayers, true, Main.plugin.dataPlayer);
                                    if (Main.plugin.getTaskRegisterTime(p) != null) {Bukkit.getScheduler().cancelTask(Main.plugin.getTaskRegisterTime(p)); Main.plugin.getTaskRegisterTimeRemove(p);}
                                    if (Main.plugin.getTaskLoginTime(p) != null) {Bukkit.getScheduler().cancelTask(Main.plugin.getTaskLoginTime(p));Main.plugin.getTaskLoginTimeRemove(p);}
                                    if (Main.plugin.getTaskBlockSpawn(p) != null) {Bukkit.getScheduler().cancelTask(Main.plugin.getTaskBlockSpawn(p));Main.plugin.getTaskBlockSpawnRemove(p);}
        
                                    MessageLogin.sendAfterLogin(p);
                                    DataPlayersFiles.setNumberTentativeLogin(uuidPlayers, 0, Main.plugin.dataPlayer);
                        
                                    return true;
        
                                }else{DataPlayersFiles.setNumberTentativeLogin(uuidPlayers, (DataPlayersFiles.getNumberTentativeLogin(uuidPlayers, Main.plugin.dataPlayer)+1), Main.plugin.dataPlayer);
                                    if (DataPlayersFiles.getNumberTentativeLogin(uuidPlayers, Main.plugin.dataPlayer)>= MessageLogin.getWrongPassTentativeNumber()) {p.kickPlayer(MessageLogin.getWrongPassTentativeKick());}
                                    p.sendMessage(MessageLogin.getWrongLoginPass());
                                    return true;
                                }
                            }else{p.sendMessage(MessageLogin.getAlreadyLogin());return true;}
                        }else {p.sendMessage(MessageLogin.getWrongLogin());return true;}
                    }else{p.sendMessage(MessageLogin.getErrorLogin());return false;}
                }
            }else{p.sendMessage("Pas la permission");return true;}
        }else{return false;}
    return false;
    }
}

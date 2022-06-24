package fr.loirelique.lpsecurity.Command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.List.ListWarningDegresAndMotifs;
import fr.loirelique.lpsecurity.Request.RequestHistoriqueSanction;
import fr.loirelique.lpsecurity.Request.RequestWarn;
import fr.loirelique.lpsecurity.String.MessageWarn;
import fr.loirelique.lpsecurity.Usefull.DataListPlayers;
import fr.loirelique.lpsecurity.Usefull.DataPlayersFiles;

public class CommandWarn implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // C'est un joueur qui a effectué la commande
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("lpsecurity.warn")) {
                if (cmd.getName().equalsIgnoreCase("warn")) { // Si c'est la commande "warn" qui a été tapée:
                    if (args.length == 2 ) {
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                        boolean condition = false;                    
                        String motif_warn = args[1]; 

                        //Request Sql Select
                        RequestHistoriqueSanction requet = new RequestHistoriqueSanction(uuidPlayers);
                        int ban = requet.getBan();
                        int warn = requet.getWarn();

                        //Get degres of motifs.
                        if (motif_warn.equals(MessageWarn.getMotifWarnLvl1())) {
                            warn += ListWarningDegresAndMotifs.getDegres(motif_warn);
                        }
                        if (motif_warn.equals(MessageWarn.getMotifWarnLvl2())) {
                            warn += ListWarningDegresAndMotifs.getDegres(motif_warn);
                        }
                        if (motif_warn.equals(MessageWarn.getMotifWarnLvl3())) {
                            warn += ListWarningDegresAndMotifs.getDegres(motif_warn);
                        }

                        //Condition of degres.
                        if (warn >= ListWarningDegresAndMotifs.getWarnDegresMax()) {condition = true;}
                        else{condition = false;}

                        //Test of condition
                        if (condition == true && ban ==1) {
                            p.sendMessage(MessageWarn.setColorAlreadyWarnAndBan() + "[" + args[0] + "] "
                            + MessageWarn.getAlreadyWarnAndBan());
                            return true;
                        }else if (condition == false && ban ==1) {
                            p.sendMessage("Le joueur est bannie les warn ne fonctionne plus.");
                        }       
                        //If the condition was true and ban zero the player is ban 1 and kick if islogin true.
                        if (condition == true && ban == 0) {
                            RequestWarn.setWarnBanAndMotifs(uuidPlayers,warn,motif_warn);
                            if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer)==true) {Player player = DataListPlayers.getObjectPlayers(uuidPlayers);player.kickPlayer("Motif Warn: "+ ListWarningDegresAndMotifs.getMotifs(motif_warn));}    
                            p.sendMessage(MessageWarn.setColorWarnAndBan() + "[" + args[0] + "] " + MessageWarn.getWarnAndBan());
                            return true;
                        
                        //If the condition was false and ban zero the player is warn incrementation.
                        }else if (condition == false && ban == 0) { 
                            RequestWarn.setWarnAndMotif(uuidPlayers, warn, motif_warn);
                            if (DataPlayersFiles.getIsOnline(uuidPlayers, Main.plugin.dataPlayer)==true) {Player player = DataListPlayers.getObjectPlayers(uuidPlayers);player.sendMessage("Motif Warn: "+ ListWarningDegresAndMotifs.getMotifs(motif_warn));}
                            p.sendMessage(MessageWarn.setColorWarn() + "[" + args[0] + "] " + MessageWarn.getWarn());
                            return true;
                        }

                    }else{p.sendMessage(MessageWarn.setColorErrorWarn() + MessageWarn.getErrorWarn());return false;}
                }
            }else{p.sendMessage("Pas la permission");return true;} 
        }else{return false;}
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 2) {
            List<String> listMotif = new ArrayList<String>();
            listMotif.add(MessageWarn.getMotifWarnLvl1());
            listMotif.add(MessageWarn.getMotifWarnLvl2());
            listMotif.add(MessageWarn.getMotifWarnLvl3());
            return listMotif;
        }
        return null;
    }

}

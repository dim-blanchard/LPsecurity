package fr.loirelique.lpsecurity.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.Request.RequestHistorique;

public class CommandHistorique implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("lpsecurity.historique")) {  
                if (cmd.getName().equalsIgnoreCase("historique")){ 
                    if (args.length == 1) {
                        String uuidPlayers = Main.plugin.getUuidHash(args[0]);
                        //Objet Requet Sql 
                        RequestHistorique requet = new RequestHistorique(uuidPlayers);

                        String motif_ban = requet.getMotif_ban();
                        String motif_tempban = requet.getMotif_tempban();
                        String motif_unban = requet.getMotif_unban();
                        String motif_kick = requet.getMotif_kick();
                        String motif_mute = requet.getMotif_mute();
                        String motif_warn = requet.getMotif_warn();
                        String motif_unmute = requet.getMotif_unmute();
                        String motif_tempmute = requet.getMotif_tempmute();
                        int ban = requet.getBan();
                        int warn = requet.getWarn();
                        int mute = requet.getMute();

                        if (ban!=0) {p.sendMessage("["+args[0]+"]"+" Ban: "+ban);}
                        if (warn!=0) {p.sendMessage("["+args[0]+"]"+" Warn: "+warn);}
                        if (mute!=0) {p.sendMessage("["+args[0]+"]"+" Mute: "+mute);}
                        if (!motif_ban.equals("null")) {p.sendMessage("["+args[0]+"]"+" Motif Ban: "+motif_ban);}
                        if (!motif_tempban.equals("null")) {p.sendMessage("["+args[0]+"]"+" Motif Tempban: "+motif_tempban);}
                        if (!motif_unban.equals("null")) {p.sendMessage("["+args[0]+"]"+" Motif Unban: "+motif_unban);}
                        if (!motif_mute.equals("null")) {p.sendMessage("["+args[0]+"]"+" Motif Mute: "+motif_mute);}
                        if (!motif_tempmute.equals("null")) {p.sendMessage("["+args[0]+"]"+" Motif Tempmute: "+motif_tempmute);}
                        if (!motif_unmute.equals("null")) {p.sendMessage("["+args[0]+"]"+" Motif Unmute: "+motif_unmute);}
                        if (!motif_kick.equals("null")) {p.sendMessage("["+args[0]+"]"+" Motif Kick: "+motif_kick);}
                        if (!motif_warn.equals("null")) {p.sendMessage("["+args[0]+"]"+" Motif Warn: "+motif_warn);}
                        if(motif_ban.equals("null")&&motif_tempban.equals("null")&&motif_unban.equals("null")&&motif_mute.equals("null")&&motif_tempmute.equals("null")&&motif_unmute.equals("null")&&motif_kick.equals("null")&&motif_warn.equals("null")){p.sendMessage("Le joueur n'a pas de motif de sanction archiver.");return true;}
                        return true;
                    }else{p.sendMessage("La commande n'a pas était exécuter");return false;}
                }
            }else{p.sendMessage("Pas la permission");return true;}
        }
        return false;
    }
}

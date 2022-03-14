package fr.loirelique.lpsecurity;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RunJoin implements Runnable {

    private Player player;
    private  JavaPlugin config;

    public RunJoin(Player player) {

        this.player = player;
    }

    @Override
    public void run() {
       
        double x = 0;
        double y = 70;
        double z = 0;
        World player_world = player.getWorld();
        Location player_tp = new Location(player_world, x, y, z);
        int time_run1 = Integer.parseInt(config.getConfig().getString("Connection.temps_enregistrement"));

        player.teleport(player_tp);
        System.out.println("Temps: " + time_run1);
        if (time_run1 == 0) {
            Main.tache1.cancel();
            player.kickPlayer(config.getConfig().getString("Connection.message_kick"));
        }
        time_run1--;
    }

}

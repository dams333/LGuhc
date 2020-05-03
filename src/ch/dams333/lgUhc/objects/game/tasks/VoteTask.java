package ch.dams333.lgUhc.objects.game.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class VoteTask extends BukkitRunnable {
    Player p;
    public VoteTask(Player voted) {
        p = voted;
    }


    int timer = 0;

    @Override
    public void run() {
        timer = timer + 1;
        if(timer >= 600){
            p.setMaxHealth(p.getMaxHealth() + 10);
            cancel();
        }
    }
}

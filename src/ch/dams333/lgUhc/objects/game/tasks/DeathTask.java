package ch.dams333.lgUhc.objects.game.tasks;

import ch.dams333.lgUhc.LgUhc;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathTask extends BukkitRunnable {
    LgUhc main;
    Player p;
    int countdown;
    public DeathTask(LgUhc main, Player p) {
        this.main = main;
        this.p = p;
        this.countdown = 30;
    }

    @Override
    public void run() {
        countdown = countdown - 1;
        if(countdown <= 0){
            main.lgGame.kill(p);
            cancel();
        }
    }
}

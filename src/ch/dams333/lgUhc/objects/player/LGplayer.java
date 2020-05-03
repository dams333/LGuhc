package ch.dams333.lgUhc.objects.player;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.events.events.LGepisodeEvent;
import ch.dams333.lgUhc.objects.game.tasks.Time;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LGplayer implements Listener {

    private Player p;
    private boolean infect;
    LgUhc main;

    public Player getP() {
        return p;
    }

    public boolean isInfect() {
        return infect;
    }

    public void setInfect(boolean infect) {
        this.infect = infect;
    }

    public LGplayer(Player p, LgUhc main) {
        this.main = main;
        this.p = p;
        this.infect = false;

        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void episode(LGepisodeEvent e){
        if(e.getTimer() >= (Time.EPISODETIME.time() * 3)) {
            p.sendMessage(ChatColor.GOLD + "Vous avez une minute pour voter ! Utilisez la commande /lg vote. Le joueur qui obtiendra le plus de vote perdra 5 coeurs pour les 10 prochaines minutes");
        }
    }

    public void setPlayer(Player p) {
        this.p = p;
    }
}

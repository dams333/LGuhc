package ch.dams333.lgUhc.listener.events.statu;

import ch.dams333.lgUhc.LgUhc;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {
    LgUhc main;
    public MoveEvent(LgUhc main) {
        this.main = main;
    }

    @EventHandler
    public void move(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(main.lgGame.isInCouple(p)){
            if(p.getLocation().distance(main.lgGame.getCouple(p).getLocation()) < 10) {
                if (!main.lgGame.getCouple().isFounded()) {
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Vous avez retrouvé votre amoureux. Votre boussole ne vous servira plus qu'à retrouver le centre");
                    main.lgGame.getCouple(p).sendMessage(ChatColor.LIGHT_PURPLE + "Vous avez retrouvé votre amoureux. Votre boussole ne vous servira plus qu'à retrouver le centre");
                    main.lgGame.foundCouple();
                }
            }
        }
    }

}

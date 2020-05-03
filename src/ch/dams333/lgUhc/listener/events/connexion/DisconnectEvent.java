package ch.dams333.lgUhc.listener.events.connexion;

import ch.dams333.lgUhc.LgUhc;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class DisconnectEvent implements Listener {
    LgUhc main;
    public DisconnectEvent(LgUhc main) {
        this.main = main;
    }

    @EventHandler
    public void quit(PlayerQuitEvent e){

        if (main.lgGame.isInGame(e.getPlayer())) {
            main.lgGame.disconnect(e.getPlayer());
        }

    }

}

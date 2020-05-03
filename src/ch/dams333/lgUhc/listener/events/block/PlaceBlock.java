package ch.dams333.lgUhc.listener.events.block;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.game.gameStep.GameStep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceBlock implements Listener {
    LgUhc main;
    public PlaceBlock(LgUhc main) {
        this.main = main;
    }

    @EventHandler
    public void damage(BlockPlaceEvent e){
        if(main.gameStepManager.isStep(GameStep.PREGAME)){
            e.setCancelled(true);
            return;
        }
        if(main.lgGame.death.keySet().contains(e.getPlayer())){
            e.setCancelled(true);
        }
    }
}

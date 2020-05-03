package ch.dams333.lgUhc.listener.events.statu;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.game.gameStep.GameStep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodEvent implements Listener {
    LgUhc main;
    public FoodEvent(LgUhc main) {
        this.main = main;
    }

    @EventHandler
    public void damage(FoodLevelChangeEvent e){
        if(main.gameStepManager.isStep(GameStep.PREGAME)){
            e.setCancelled(true);
        }
    }
}

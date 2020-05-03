package ch.dams333.lgUhc.listener.events.block;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.game.gameStep.GameStep;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.Map;

public class BreakBlockEvent implements Listener {
    LgUhc main;
    public BreakBlockEvent(LgUhc main) {
        this.main = main;
        diamonLimit = new HashMap<>();
    }


    private Map<Player, Integer> diamonLimit;

    @EventHandler
    public void damage(BlockBreakEvent e){
        if(main.gameStepManager.isStep(GameStep.PREGAME)){
            e.setCancelled(true);
            return;
        }
        if(main.lgGame.death.keySet().contains(e.getPlayer())){
            e.setCancelled(true);
        }
        if(e.getBlock().getType() == Material.DIAMOND_ORE){
            if(diamonLimit.containsKey(e.getPlayer())){
                if(diamonLimit.get(e.getPlayer()) >= 17){
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED + "Vous avez déjà atteint votre limite de diamant");
                }else{
                    diamonLimit.put(e.getPlayer(), diamonLimit.get(e.getPlayer()) + 1);
                }
            }else{
                diamonLimit.put(e.getPlayer(), 1);
            }
        }
    }
}

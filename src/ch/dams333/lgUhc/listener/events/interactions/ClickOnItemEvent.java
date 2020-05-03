package ch.dams333.lgUhc.listener.events.interactions;

import ch.dams333.lgUhc.LgUhc;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClickOnItemEvent implements Listener {
    LgUhc main;
    public ClickOnItemEvent(LgUhc main) {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getItem() != null){
            Player p = e.getPlayer();
            ItemStack it = e.getItem();
            if(it.getType() == Material.COMPASS){
                if(main.lgGame.isInCouple(p)) {
                    if (!main.lgGame.getCouple().isFounded()) {
                        Location toPoint = main.lgGame.getCouple(p).getLocation();
                        p.setCompassTarget(toPoint);
                        p.sendMessage(ChatColor.LIGHT_PURPLE + "La boussole pointe vers votre amoureux. Cliquez à nouveau pour actualiser");
                        return;
                    }
                }
                p.setCompassTarget(new Location(Bukkit.getWorld("world"), 0, e.getPlayer().getLocation().getY(), 0));
                p.sendMessage(ChatColor.LIGHT_PURPLE + "La boussole pointe vers le centre");
            }
        }
    }

}

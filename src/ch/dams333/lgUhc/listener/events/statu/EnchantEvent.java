package ch.dams333.lgUhc.listener.events.statu;

import ch.dams333.lgUhc.LgUhc;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class EnchantEvent implements Listener {
    LgUhc main;
    public EnchantEvent(LgUhc main) {
        this.main = main;
    }

    private boolean isDiamond(ItemStack it){
        if(it.getType() == Material.DIAMOND_HELMET) return true;
        if(it.getType() == Material.DIAMOND_CHESTPLATE) return true;
        if(it.getType() == Material.DIAMOND_LEGGINGS) return true;
        if(it.getType() == Material.DIAMOND_BOOTS) return true;
        return false;
    }

}

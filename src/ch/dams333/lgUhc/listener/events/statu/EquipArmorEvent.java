package ch.dams333.lgUhc.listener.events.statu;

import ch.dams333.lgUhc.LgUhc;
import com.codingforcookies.armorequip.ArmorEquipEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class EquipArmorEvent implements Listener {

    LgUhc main;
    public EquipArmorEvent(LgUhc main) {
        this.main = main;
    }

    private int countLeather(Player p){
        int count = 0;
        if(p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType() == Material.LEATHER_HELMET) count++;
        if(p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE) count++;
        if(p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS) count++;
        if(p.getInventory().getBoots() != null && p.getInventory().getBoots().getType() == Material.LEATHER_BOOTS) count++;
        return count;
    }
    private int countChain(Player p){
        int count = 0;
        if(p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType() == Material.CHAINMAIL_HELMET) count++;
        if(p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE) count++;
        if(p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType() == Material.CHAINMAIL_LEGGINGS) count++;
        if(p.getInventory().getBoots() != null && p.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) count++;
        return count;
    }
    private int countIron(Player p){
        int count = 0;
        if(p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType() == Material.IRON_HELMET) count++;
        if(p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) count++;
        if(p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType() == Material.IRON_LEGGINGS) count++;
        if(p.getInventory().getBoots() != null && p.getInventory().getBoots().getType() == Material.IRON_BOOTS) count++;
        return count;
    }
    private int countGold(Player p){
        int count = 0;
        if(p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType() == Material.GOLDEN_HELMET) count++;
        if(p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() == Material.GOLDEN_CHESTPLATE) count++;
        if(p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType() == Material.GOLDEN_LEGGINGS) count++;
        if(p.getInventory().getBoots() != null && p.getInventory().getBoots().getType() == Material.GOLDEN_BOOTS) count++;
        return count;
    }
    private int countDiamond(Player p){
        int count = 0;
        if(p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType() == Material.DIAMOND_HELMET) count++;
        if(p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE) count++;
        if(p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType() == Material.DIAMOND_LEGGINGS) count++;
        if(p.getInventory().getBoots() != null && p.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS) count++;
        return count;
    }

    private boolean isLeather(ItemStack it){
        if(it.getType() == Material.LEATHER_HELMET) return true;
        if(it.getType() == Material.LEATHER_CHESTPLATE) return true;
        if(it.getType() == Material.LEATHER_LEGGINGS) return true;
        if(it.getType() == Material.LEATHER_BOOTS) return true;
        return false;
    }
    private boolean isChain(ItemStack it){
        if(it.getType() == Material.CHAINMAIL_HELMET) return true;
        if(it.getType() == Material.CHAINMAIL_CHESTPLATE) return true;
        if(it.getType() == Material.CHAINMAIL_LEGGINGS) return true;
        if(it.getType() == Material.CHAINMAIL_BOOTS) return true;
        return false;
    }
    private boolean isIron(ItemStack it){
        if(it.getType() == Material.IRON_HELMET) return true;
        if(it.getType() == Material.IRON_CHESTPLATE) return true;
        if(it.getType() == Material.IRON_LEGGINGS) return true;
        if(it.getType() == Material.IRON_BOOTS) return true;
        return false;
    }
    private boolean isGold(ItemStack it){
        if(it.getType() == Material.GOLDEN_HELMET) return true;
        if(it.getType() == Material.GOLDEN_CHESTPLATE) return true;
        if(it.getType() == Material.GOLDEN_LEGGINGS) return true;
        if(it.getType() == Material.GOLDEN_BOOTS) return true;
        return false;
    }
    private boolean isDiamond(ItemStack it){
        if(it.getType() == Material.DIAMOND_HELMET) return true;
        if(it.getType() == Material.DIAMOND_CHESTPLATE) return true;
        if(it.getType() == Material.DIAMOND_LEGGINGS) return true;
        if(it.getType() == Material.DIAMOND_BOOTS) return true;
        return false;
    }

    @EventHandler
    public void equipArmor(ArmorEquipEvent e){
        Player p = e.getPlayer();
        if(e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR){
            if(isDiamond(e.getNewArmorPiece())){
                if(countDiamond(p) >= 2){
                    e.setCancelled(true);
                    p.getInventory().addItem(e.getNewArmorPiece());
                    p.sendMessage(ChatColor.RED + "Tu ne peut pas équiper plus de 2 pièces en diamant");
                }
            }
        }
    }

}

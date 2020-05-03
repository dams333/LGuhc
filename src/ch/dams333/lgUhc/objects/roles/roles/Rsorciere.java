package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rsorciere extends Role {
    public Rsorciere(LgUhc main) {
        super(main);
        canRes = new HashMap<>();
        canBeResed = new ArrayList<>();
    }


    private Map<LGplayer, Boolean> canRes;

    public boolean canRes(Player p){
        for(LGplayer lGplayer : this.canRes.keySet()){
            if(lGplayer.getP() == p){
                return this.canRes.get(lGplayer);
            }
        }
        return false;
    }

    public void hasRes(Player p){
        for(LGplayer lGplayer : this.canRes.keySet()){
            if(lGplayer.getP() == p){
                this.canRes.put(lGplayer, false);
            }
        }
    }

    private List<Player> canBeResed;

    public boolean canBeResed(Player p){
        return canBeResed.contains(p);
    }

    public void removePlayerBeResed(Player p){
        if(this.canBeResed.contains(p)){
            this.canBeResed.remove(p);
        }
    }

    @Override
    public String getName() {
        return "Sorcière";
    }

    @Override
    public String getSimpleName() {
        return "Sorciere";
    }

    @Override
    public String getDescription() {
        return "Vous pouvez faire un revivre un joueur qui meurt. Votre but est de tuer tous les loups";
    }

    @Override
    public RoleSort getSort() {
        return RoleSort.SORCIERE;
    }

    @Override
    public RoleWin getWin() {
        return RoleWin.VILLAGE;
    }
    @Override
    public void night() {

    }

    @Override
    public void day() {

    }

    @Override
    public void episode() {

    }

    @Override
    protected void reloadPerm(Player p) {
        main.lgPermissionManager.addPerm(p, "sauver");
    }

    @Override
    public void playerGives(Player p) {
        ItemStack healthPotion = main.API.itemStackManager.create(Material.SPLASH_POTION, 3);
        PotionMeta healthM = (PotionMeta) healthPotion.getItemMeta();
        healthM.setColor(Color.RED);
        healthM.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 1,0, true), true);
        healthPotion.setItemMeta(healthM);
        ItemStack damagePotion = main.API.itemStackManager.create(Material.SPLASH_POTION, 3);
        PotionMeta damageM = (PotionMeta) damagePotion.getItemMeta();
        damageM.setColor(Color.PURPLE);
        damageM.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 1,0, true), true);
        damagePotion.setItemMeta(damageM);
        ItemStack regenPotion = main.API.itemStackManager.create(Material.SPLASH_POTION, 1);
        PotionMeta regenM = (PotionMeta) regenPotion.getItemMeta();
        regenM.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 610,0, true), true);
        regenPotion.setItemMeta(regenM);
        p.getInventory().addItem(healthPotion);
        p.getInventory().addItem(damagePotion);
        p.getInventory().addItem(regenPotion);
        main.lgPermissionManager.addPerm(p, "sauver");
        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == p){
                this.canRes.put(lGplayer, true);
            }
        }
    }

    public void addPlayerToBeRes(Player p) {
        this.canBeResed.add(p);
    }
}

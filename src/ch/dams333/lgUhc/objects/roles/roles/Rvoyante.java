package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class Rvoyante extends Role {
    public Rvoyante(LgUhc main) {
        super(main);
        canSee = new HashMap<>();
        lastSeeGood = new HashMap<>();
    }

    private Map<LGplayer, Boolean> canSee;
    private Map<LGplayer, Boolean> lastSeeGood;

    @Override
    public String getName() {
        return "Voyante";
    }

    @Override
    public String getSimpleName() {
        return "Voyante";
    }

    @Override
    public String getDescription() {
        return "Chaque épisode, vous pouvez espionner le rôle d'un joueur. Si vous espionnez un membre du village, vous perdez 3 coeurs et ne pouvez pas utiliser votre pouvoir à l'épisode d'après. Votre but est de tuer tous les loups";
    }
    @Override
    public RoleSort getSort() {
        return RoleSort.VOYANTE;
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

    public boolean canSee(Player p){
        for(LGplayer lGplayer : getPlayers()) {
            if (lGplayer.getP() == p) {
                return this.canSee.get(lGplayer);
            }
        }
        return false;
    }

    public void lastSeeNotGood(Player p){
        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == p){
                lastSeeGood.put(lGplayer, false);
            }
        }
    }

    public void saw(Player p){
        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == p){
                canSee.put(lGplayer, false);
            }
        }
    }

    @Override
    public void episode() {
        for(LGplayer lGplayer : this.canSee.keySet()) {
            if (this.lastSeeGood.get(lGplayer)) {
                canSee.put(lGplayer, true);
                lGplayer.getP().sendMessage(ChatColor.GOLD + "Vous pouvez espionner quelqu'un grâce à la commande /lg voir");
            }else{
                this.lastSeeGood.put(lGplayer, true);
            }
        }
    }

    @Override
    protected void reloadPerm(Player p) {
        main.lgPermissionManager.addPerm(p, "voir");
    }

    @Override
    public void playerGives(Player p) {
        p.getInventory().addItem(main.API.itemStackManager.create(Material.BOOKSHELF, 5));
        p.getInventory().addItem(main.API.itemStackManager.create(Material.OBSIDIAN, 4));
        main.lgPermissionManager.addPerm(p, "voir");
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1, false, false), true);
        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == p){
                canSee.put(lGplayer, false);
                lastSeeGood.put(lGplayer, true);
            }
        }
    }
}

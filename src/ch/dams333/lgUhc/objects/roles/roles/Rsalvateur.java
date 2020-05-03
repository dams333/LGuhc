package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.ChatColor;
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

public class Rsalvateur extends Role {
    public Rsalvateur(LgUhc main) {
        super(main);
        protect = new ArrayList<>();
        canNotBeProtect = new ArrayList<>();
        canProtect = new HashMap<>();
    }

    private List<LGplayer> canNotBeProtect;

    private List<LGplayer> protect;

    private Map<LGplayer, Boolean> canProtect;

    @Override
    public String getName() {
        return "Salvateur";
    }

    @Override
    public String getSimpleName() {
        return "Salvateur";
    }

    @Override
    public String getDescription() {
        return "Une fois par épisode, vous pouvez donner NoFall et Resistance I au joueur de votre choix (y compris vous même). Votre but est de tuer tous les loups";
    }

    @Override
    public RoleSort getSort() {
        return RoleSort.SALVATEUR;
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

        canNotBeProtect = new ArrayList<>();
        for(LGplayer p : protect){
            canNotBeProtect.add(p);
            p.getP().sendMessage(ChatColor.LIGHT_PURPLE + "Vous n'êtes plus protégé par le salvateur");
        }
        protect = new ArrayList<>();
        for(LGplayer p : canProtect.keySet()){
            canProtect.put(p, true);
            p.getP().sendMessage(ChatColor.GOLD + "Vous pouvez protéger quelqu'un grâce à la commande /lg proteger");
        }

    }

    @Override
    protected void reloadPerm(Player p) {
        main.lgPermissionManager.addPerm(p, "proteger");
    }

    public boolean canProtect(Player p){
        for(LGplayer lGplayer : this.canProtect.keySet()){
            if(lGplayer.getP() == p){
                return this.canProtect.get(lGplayer);
            }
        }
        return false;
    }

    public boolean isProtected(Player p){
        for(LGplayer lGplayer : this.protect){
            if(lGplayer.getP() == p){
                return true;
            }
        }
        return false;
    }

    public boolean canBeProtected(Player p){
        for(LGplayer lGplayer : this.canNotBeProtect){
            if(lGplayer.getP() == p){
                return false;
            }
        }
        return true;
    }

    public void protect(Player renard, Player protect){
        for(Role role : main.lgGame.getRoles()){
            for(LGplayer lGplayer : role.getPlayers()){
                if(lGplayer.getP() == protect){
                    this.protect.add(lGplayer);
                }
            }
        }
        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == renard){
                this.canProtect.put(lGplayer, false);
            }
        }
    }

    @Override
    public void playerGives(Player p) {
        ItemStack healthPotion = main.API.itemStackManager.create(Material.SPLASH_POTION, 2);
        PotionMeta healthM = (PotionMeta) healthPotion.getItemMeta();
        healthM.setColor(Color.RED);
        healthM.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 1,0, true), true);
        healthPotion.setItemMeta(healthM);
        main.lgPermissionManager.addPerm(p, "proteger");
        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == p){
                canProtect.put(lGplayer, false);
            }
        }
    }
}

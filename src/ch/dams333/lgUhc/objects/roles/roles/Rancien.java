package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class Rancien extends Role {
    public Rancien(LgUhc main) {
        super(main);
        canRes = new HashMap<>();
    }

    private Map<LGplayer, Boolean> canRes;

    @Override
    public String getName() {
        return "Ancien";
    }

    @Override
    public String getSimpleName() {
        return "Ancien";
    }

    @Override
    public String getDescription() {
        return "Vous pouvez revivre une fois. Votre but est de tuer tous les loups";
    }

    @Override
    public RoleSort getSort() {
        return RoleSort.ANCIEN;
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

    }

    public boolean canRes(Player p){
        for(LGplayer lGplayer : this.canRes.keySet()){
            if(lGplayer.getP() == p){
                return this.canRes.get(lGplayer);
            }
        }
        return false;
    }

    public void hasRes(Player p){
        for(LGplayer lGplayer : this.canRes.keySet()) {
            if (lGplayer.getP() == p) {
                this.canRes.put(lGplayer, false);
            }
        }
    }

    @Override
    public void playerGives(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, 0, false, false), true);
        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == p){
                canRes.put(lGplayer, true);
            }
        }
    }
}

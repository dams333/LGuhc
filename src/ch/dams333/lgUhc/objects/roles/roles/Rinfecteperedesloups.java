package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rinfecteperedesloups extends Role {
    public Rinfecteperedesloups(LgUhc main) {
        super(main);
        canRes = new HashMap<>();
        canBeResed = new ArrayList<>();
    }


    private Map<LGplayer, Boolean> canRes;

    private List<Player> canBeResed;

    public boolean canBeResed(Player p){
        return canBeResed.contains(p);
    }

    public void removePlayerBeResed(Player p){
        if(this.canBeResed.contains(p)){
            this.canBeResed.remove(p);
        }
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
        for(LGplayer lGplayer : this.canRes.keySet()){
            if(lGplayer.getP() == p){
                this.canRes.put(lGplayer, false);
            }
        }
    }

    public void addPlayerToBeRes(Player p) {
        this.canBeResed.add(p);
    }

    @Override
    public String getName() {
        return "Infect Père des Loups";
    }

    @Override
    public String getSimpleName() {
        return "InfectPereDesLoups";
    }

    @Override
    public String getDescription() {
        return "Vous avez force et nightvision la nuit. Si un joueur est tué par un loup, vous pouvez le faire revivre en le faisant rejoindre le camps des loups. Lorsque vous faites un kill, vous obtenez Speed 1 et 2 coeurs d'absorption pendant 1 minute. Votre but est de tuer tous les villageois";
    }

    @Override
    public RoleSort getSort() {
        return RoleSort.INFECT;
    }

    @Override
    public RoleWin getWin() {
        return RoleWin.LOUPGAROU;
    }

    @Override
    public void night() {
        for(LGplayer lGplayer : super.getPlayers()){
            Player p = lGplayer.getP();
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 0, false, false), true);
        }
    }

    @Override
    public void day() {
        for(LGplayer lGplayer : super.getPlayers()){
            Player p = lGplayer.getP();
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        }
    }

    @Override
    public void episode() {

    }

    @Override
    protected void reloadPerm(Player p) {
        main.lgPermissionManager.addPerm(p, "infecter");
    }

    @Override
    public void playerGives(Player p) {
        main.lgPermissionManager.addPerm(p, "infecter");
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1, false, false), true);
        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == p){
                this.canRes.put(lGplayer, true);
            }
        }
    }
}

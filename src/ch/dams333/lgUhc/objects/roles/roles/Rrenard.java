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

public class Rrenard extends Role {
    public Rrenard(LgUhc main) {
        super(main);
        canSee = new HashMap<>();
    }


    private Map<LGplayer, Integer> canSee;

    @Override
    public String getName() {
        return "Renard";
    }

    @Override
    public String getSimpleName() {
        return "Renard";
    }

    @Override
    public String getDescription() {
        return "3 fois dans la partie, vous pouvez flairer un joueur qui se trouve à moins de 10 blocks grâce à la commande /lg flairer. Vous saurez alors sîl est du camps des loups ou innocent. Votre but est de tuer tous les loups";
    }

    @Override
    public RoleSort getSort() {
        return RoleSort.RENARD;
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
        main.lgPermissionManager.addPerm(p, "flairer");
    }

    public boolean canSee(Player p){
        for(LGplayer lGplayer : getPlayers()) {
            if (lGplayer.getP() == p) {
                return this.canSee.get(lGplayer) > 0;
            }
        }
        return false;
    }

    public void saw(Player p){
        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == p){
                canSee.put(lGplayer, canSee.get(lGplayer) - 1);
            }
        }
    }

    @Override
    public void playerGives(Player p) {
        main.lgPermissionManager.addPerm(p, "flairer");
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 0, false, false), true);
        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == p){
                canSee.put(lGplayer, 3);
            }
        }
    }
}

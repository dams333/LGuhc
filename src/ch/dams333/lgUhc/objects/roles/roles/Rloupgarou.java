package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Rloupgarou extends Role {
    public Rloupgarou(LgUhc main) {
        super(main);
    }

    @Override
    public String getName() {
        return "Loup garou";
    }

    @Override
    public String getSimpleName() {
        return "LoupGarou";
    }

    @Override
    public String getDescription() {
        return "Vous avez force et nightvision la nuit. Lorsque vous faites un kill, vous obtenez Speed 1 et 2 coeurs d'absorption pendant 1 minute. Votre but est de tuer tous les villageois";
    }

    @Override
    public RoleSort getSort() {
        return RoleSort.LOUPGAROU;
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

    }

    @Override
    public void playerGives(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1, false, false), true);
    }
}

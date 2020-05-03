package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Rvilainpetitloup extends Role {
    public Rvilainpetitloup(LgUhc main) {
        super(main);
    }

    @Override
    public String getName() {
        return "Vilain petit Loup";
    }

    @Override
    public String getSimpleName() {
        return "VilainPetitLoup";
    }

    @Override
    public String getDescription() {
        return "Vous avez force, nightvision et speed la nuit. Lorsque vous faites un kill, vous obtenez Speed 1 et 2 coeurs d'absorption pendant 1 minute. Votre but est de toues tous les villageois";
    }

    @Override
    public RoleSort getSort() {
        return RoleSort.VILAINLOUP;
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
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 0, false, false), true);
        }
    }

    @Override
    public void day() {
        for(LGplayer lGplayer : super.getPlayers()){
            Player p = lGplayer.getP();
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            p.removePotionEffect(PotionEffectType.SPEED);
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
    }
}

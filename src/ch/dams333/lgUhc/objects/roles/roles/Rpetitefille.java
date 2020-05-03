package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Rpetitefille extends Role {
    public Rpetitefille(LgUhc main) {
        super(main);
    }

    @Override
    public String getName() {
        return "Petite Fille";
    }

    @Override
    public String getSimpleName() {
        return "PetiteFille";
    }

    @Override
    public String getDescription() {
        return "Vous êtes invisible la nuit. Votre but est de tuer tous les loups";
    }
    @Override
    public RoleSort getSort() {
        return RoleSort.PETITEFILLE;
    }

    @Override
    public RoleWin getWin() {
        return RoleWin.VILLAGE;
    }

    @Override
    public void night() {
        for(LGplayer lGplayer : super.getPlayers()){
            Player p = lGplayer.getP();
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 0, false, false), true);
        }
    }

    @Override
    public void day() {
        for(LGplayer lGplayer : super.getPlayers()){
            Player p = lGplayer.getP();
            p.removePotionEffect(PotionEffectType.INVISIBILITY);
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
        p.getInventory().addItem(super.main.API.itemStackManager.create(Material.TNT, 5));
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1, false, false), true);
    }
}

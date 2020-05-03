package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Rdresseur extends Role {
    public Rdresseur(LgUhc main) {
        super(main);
    }

    @Override
    public String getName() {
        return "Dresseur d'ours";
    }

    @Override
    public String getSimpleName() {
        return "DresseurOurs";
    }

    @Override
    public String getDescription() {
        return "A chaque début d'épisode, votre ours grognera pour chaque loups dans un rayon de 50 blocks. Votre but est de tuer tous les loups";
    }

    @Override
    public RoleSort getSort() {
        return RoleSort.DRESSEUR;
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
        boolean gr = false;
        for(LGplayer lGplayer : getPlayers()){
            for(Role role : main.lgGame.getRoles()){
                if(main.lgsSorts.contains(role.getSort())){
                    for(LGplayer loup : role.getPlayers()){
                        if(lGplayer.getP().getLocation().distance(loup.getP().getLocation()) < 50){
                            Bukkit.broadcastMessage(ChatColor.GOLD + "Grrrrrrrr");
                            gr = true;
                        }
                    }
                }
            }
        }
        if(gr){
            for(Player p : Bukkit.getOnlinePlayers()){
                p.playSound(p.getLocation(), Sound.ENTITY_POLAR_BEAR_WARNING, 10, 1);
            }
        }
    }

    @Override
    protected void reloadPerm(Player p) {

    }

    @Override
    public void playerGives(Player p) {

    }
}

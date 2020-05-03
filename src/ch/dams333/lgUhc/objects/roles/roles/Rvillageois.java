package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.entity.Player;

public class Rvillageois extends Role {
    public Rvillageois(LgUhc main) {
        super(main);
    }

    @Override
    public String getName() {
        return "Simple Villageois";
    }

    @Override
    public String getSimpleName() {
        return "SimpleVillageois";
    }

    @Override
    public String getDescription() {
        return "Vous n'avez pas de pouvoir particulier. Vous gagnez si vous éliminez tous les loups";
    }
    @Override
    public RoleSort getSort() {
        return RoleSort.VILLAGEOIS;
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

    @Override
    public void playerGives(Player p) {

    }
}

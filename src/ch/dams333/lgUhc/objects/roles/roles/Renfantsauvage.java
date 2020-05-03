package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Renfantsauvage extends Role {
    public Renfantsauvage(LgUhc main) {
        super(main);
        transformed = new HashMap<>();
        model = new HashMap<>();
    }

    private Map<LGplayer, Boolean> transformed;
    private Map<LGplayer, String> model;

    @Override
    public String getName() {
        return "Enfant sauvage";
    }

    @Override
    public String getSimpleName() {
        return "EnfantSauvage";
    }

    @Override
    public String getDescription() {
        return "Vous devez choisir un modèle grâce à la commande /lg choisir. Vous appartenez au camps de villageois jusqu'à ce que votre modèle meurt. A partir de là, vous appartenez au camps des loups";
    }
    @Override
    public RoleSort getSort() {
        return RoleSort.ENFANT;
    }

    @Override
    public RoleWin getWin() {
        return RoleWin.CHANGE;
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
        main.lgPermissionManager.addPerm(p, "choisir");
    }

    public boolean isTransformed(Player p){
        for(LGplayer lGplayer : this.transformed.keySet()){
            if(lGplayer.getP() == p){
                return this.transformed.get(lGplayer);
            }
        }
        return false;
    }

    public void transform(Player p){
        for(LGplayer lGplayer : this.transformed.keySet()) {
            if (lGplayer.getP() == p) {
                this.transformed.put(lGplayer, true);
            }
        }
    }

    public boolean hasModel(Player p){
        for(LGplayer lGplayer : this.model.keySet()){
            if(lGplayer.getP() == p){
                return true;
            }
        }
        return false;
    }

    public void chooseModel(Player p, String model){
        for(LGplayer lGplayer : getPlayers()) {
            if (lGplayer.getP() == p) {
                this.model.put(lGplayer, model);
                return;
            }
        }
    }

    public String getModel(Player p ){
        for(LGplayer lGplayer : this.model.keySet()){
            if(lGplayer.getP() == p){
                return this.model.get(lGplayer);
            }
        }
        return "";
    }

    @Override
    public void playerGives(Player p) {
        main.lgPermissionManager.addPerm(p, "choisir");
        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == p){
                this.transformed.put(lGplayer, false);
                return;
            }
        }
    }
}

package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rdetective extends Role {
    public Rdetective(LgUhc main) {
        super(main);
        canEnquete = new HashMap<>();
        hasEnqueted = new HashMap<>();
    }

    @Override
    public String getName() {
        return "Détective";
    }

    @Override
    public String getSimpleName() {
        return "Detective";
    }

    @Override
    public String getDescription() {
        return "Une fois par épisode vous pouvez enquêter 2 joueurs. Vous saurez alors si ceux-ci apparatiennent au mêm camps ou non. Vous ne pouvez pas vous enquêter vous même ou enquêter 2 fois le même joueur Vous gagnez si vous éliminez tous les loups";
    }
    @Override
    public RoleSort getSort() {
        return RoleSort.DETECTIVE;
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
        for(LGplayer lGplayer : canEnquete.keySet()){
            canEnquete.put(lGplayer, true);
            lGplayer.getP().sendMessage(ChatColor.LIGHT_PURPLE + "Vous pouvez mainteant enquêter grâce à la commande /lg enquete");
        }
    }

    @Override
    protected void reloadPerm(Player p) {
        main.lgPermissionManager.addPerm(p, "enquete");
    }

    private Map<LGplayer, Boolean> canEnquete;
    private Map<LGplayer, List<Player>> hasEnqueted;

    public boolean canEnquete(Player p){
        for(LGplayer lGplayer : canEnquete.keySet()){
            if(lGplayer.getP() == p){
                return canEnquete.get(lGplayer);
            }
        }
        return false;
    }

    public boolean canBeEnqueted(Player enqueter, Player p1, Player p2){
        for(LGplayer lGplayer : hasEnqueted.keySet()){
            if(lGplayer.getP() == enqueter){
                if(hasEnqueted.get(lGplayer).contains(p1) || hasEnqueted.get(lGplayer).contains(p2)){
                    return false;
                }
            }
        }
        return true;
    }

    public void hasEnqueted(Player enqueter, Player p1, Player p2){
        for(LGplayer lGplayer : canEnquete.keySet()){
            if(lGplayer.getP() == enqueter){
                canEnquete.put(lGplayer, false);
                List<Player> enqueted = hasEnqueted.get(lGplayer);
                enqueted.add(p1);
                enqueted.add(p2);
                hasEnqueted.put(lGplayer, enqueted);
            }
        }
    }

    @Override
    public void playerGives(Player p) {

        for(LGplayer lGplayer : getPlayers()){
            if(lGplayer.getP() == p){
                canEnquete.put(lGplayer, false);
                hasEnqueted.put(lGplayer, new ArrayList<>());
                main.lgPermissionManager.addPerm(p, "enquete");
            }
        }

    }
}

package ch.dams333.lgUhc.commands.command.permissions;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LgPermissionManager {

    private Map<Player, List<String>> permissions;

    public LgPermissionManager() {
        permissions = new HashMap<>();
    }

    public void addPerm(Player p, String permission){
        if(!this.permissions.keySet().contains(p)){
            List<String> perms = new ArrayList<>();
            perms.add(permission);
            this.permissions.put(p, perms);
        }else{
            List<String> perms = this.permissions.get(p);
            perms.add(permission);
            this.permissions.put(p, perms);
        }
    }

    public boolean hasPerm(Player p, String permission){
        if(this.permissions.keySet().contains(p)){
            if(this.permissions.get(p).contains(permission)){
                return true;
            }
        }
        return false;
    }

    public void removePerm(Player p, String permission){
        if(hasPerm(p, permission)){
            List<String> perms = this.permissions.get(p);
            perms.remove(permission);
            this.permissions.put(p, perms);
        }
    }

    public void removeAllPerm(){
        this.permissions = new HashMap<>();
    }

}

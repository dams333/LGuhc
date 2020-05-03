package ch.dams333.lgUhc.objects.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.events.events.LGdayEvent;
import ch.dams333.lgUhc.events.events.LGepisodeEvent;
import ch.dams333.lgUhc.events.events.LGnightEvent;
import ch.dams333.lgUhc.objects.player.LGplayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public abstract class Role implements Listener {

    private int neededPlayers;

    private List<LGplayer> players;

    public int getNeededPlayers() {
        return neededPlayers;
    }

    public void setNeededPlayers(int neededPlayers) {
        this.neededPlayers = neededPlayers;
    }

    protected LgUhc main;

    public Role(LgUhc main) {
        players = new ArrayList<>();
        Bukkit.getPluginManager().registerEvents(this, main);
        FileConfiguration config = main.getConfig();
        String roleConfigName = "role."+getName();
        if(config.contains(roleConfigName)) {
            neededPlayers = config.getInt(roleConfigName);
        }
        this.main = main;
    }
    public abstract String getName();
    public abstract String getSimpleName();
    public abstract String getDescription();
    public abstract RoleSort getSort();
    public abstract RoleWin getWin();
    protected abstract void night();
    protected abstract void day();
    protected abstract void episode();
    public List<LGplayer> getPlayers(){
        return this.players;
    }
    public void addPlayer(Player p){
        LGplayer lGplayer = new LGplayer(p, main);
        this.players.add(lGplayer);
        playerGives(p);
    }
    protected abstract void reloadPerm(Player p);

    protected abstract void playerGives(Player p);

    @EventHandler
    public void night(LGnightEvent e){
        night();
    }

    @EventHandler
    public void night(LGdayEvent e){
        day();
    }

    @EventHandler
    public void night(LGepisodeEvent e){
        episode();
    }

    public void setPlayers(List<LGplayer> players) {
        this.players = players;
    }

    public void reloadPlayer(Player p) {
        for(LGplayer lGplayer : this.getPlayers()){
            if(lGplayer.getP().getName().equals(p.getName())){
                this.players.remove(lGplayer);
                lGplayer.setPlayer(p);
                this.players.add(lGplayer);
                reloadPerm(p);
                return;
            }
        }
    }
}

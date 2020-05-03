package ch.dams333.lgUhc;

import ch.dams333.damsLib.DamsLIB;
import ch.dams333.lgUhc.commands.CommandManager;
import ch.dams333.lgUhc.commands.command.permissions.LgPermissionManager;
import ch.dams333.lgUhc.listener.ListenerManager;
import ch.dams333.lgUhc.objects.game.gameStep.GameStepManager;
import ch.dams333.lgUhc.objects.game.lgGame.LgGame;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.roles.*;
import ch.dams333.lgUhc.utils.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LgUhc extends JavaPlugin {

    public GameStepManager gameStepManager;
    public LgPermissionManager lgPermissionManager;
    public LgGame lgGame;
    public DamsLIB API;
    public ScoreboardManager scoreboardManager;
    public List<RoleSort> lgsSorts;

    @Override
    public void onEnable(){

        API = (DamsLIB) getServer().getPluginManager().getPlugin("DamsLIB");

        this.gameStepManager = new GameStepManager(this);
        this.lgPermissionManager = new LgPermissionManager();
        this.scoreboardManager = new ScoreboardManager(this);

        new ListenerManager(this);
        new CommandManager(this);

        lgsSorts = new ArrayList<>();

        lgGame = new LgGame(this);

        loadRoles();
        if(!new File(getDataFolder(), "config.yml").exists()) {
            FileConfiguration config = getConfig();
            for(Role role : lgGame.getRoles())
                config.set("role."+role.getName(), 0);
            saveConfig();
        }
        loadConfig();

        for(Player p : Bukkit.getOnlinePlayers()){
            if(p.hasPermission("lg.commands.lg")){
                lgPermissionManager.addPerm(p, "config");
                lgPermissionManager.addPerm(p, "start");
            }
        }

    }


    private void loadRoles() {
        this.lgGame.addRole(new Rvillageois(this));
        this.lgGame.addRole(new Rloupgarou(this));
        this.lgGame.addRole(new Rpetitefille(this));
        this.lgGame.addRole(new Rvoyante(this));
        this.lgGame.addRole(new Rassassin(this));
        this.lgGame.addRole(new Rancien(this));
        this.lgGame.addRole(new Rcupidon(this));
        this.lgGame.addRole(new Rdresseur(this));
        this.lgGame.addRole(new Rloupgaroublanc(this));
        this.lgGame.addRole(new Rvilainpetitloup(this));
        this.lgGame.addRole(new Rinfecteperedesloups(this));
        this.lgGame.addRole(new Rsorciere(this));
        this.lgGame.addRole(new Rrenard(this));
        this.lgGame.addRole(new Rsalvateur(this));
        this.lgGame.addRole(new Rmineur(this));
        this.lgGame.addRole(new Rdetective(this));
        this.lgGame.addRole(new Renfantsauvage(this));

        lgsSorts.add(RoleSort.LOUPBLANC);
        lgsSorts.add(RoleSort.LOUPGAROU);
        lgsSorts.add(RoleSort.VILAINLOUP);
        lgsSorts.add(RoleSort.INFECT);
    }

    private void loadConfig() {
        for(Role role : this.lgGame.getRoles()) {
            int players = getConfig().getInt("role." + role.getName());
            lgGame.setRole(role, players);
        }
    }

}

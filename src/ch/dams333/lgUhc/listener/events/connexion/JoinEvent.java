package ch.dams333.lgUhc.listener.events.connexion;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.game.gameStep.GameStep;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

public class JoinEvent implements Listener {
    LgUhc main;
    public JoinEvent(LgUhc main) {
        this.main = main;
    }

    @EventHandler
    public void join(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getAttribute(org.bukkit.attribute.Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16.0D);
        if(main.gameStepManager.isStep(GameStep.PREGAME)){
            if(new Location(Bukkit.getWorld("world"), 0, 149, 0).getBlock().getType() != Material.BARRIER){
                for(int x = -50; x <= 50; x++){
                    for(int z = -50; z <= 50; z++){
                        new Location(Bukkit.getWorld("world"), x, 149, z).getBlock().setType(Material.BARRIER);
                    }
                }
            }
            for(PotionEffect potionEffect : p.getActivePotionEffects()){
                p.removePotionEffect(potionEffect.getType());
            }
            p.teleport(new Location(Bukkit.getWorld("world"), 0, 150, 0));
            p.setGameMode(GameMode.ADVENTURE);
            p.setMaxHealth(20);
            p.setHealth(20);
            p.setFoodLevel(20);
            p.getInventory().clear();
            if(p.hasPermission("lg.commands.lg")){
                main.lgPermissionManager.addPerm(p, "config");
                main.lgPermissionManager.addPerm(p, "start");
            }
        }else{
            if(main.lgGame.isDeconnected(p)){
                main.lgGame.reloadPlayer(p);
            }else{
                p.teleport(new Location(Bukkit.getWorld("world"), 0, 150, 0));
                p.setGameMode(GameMode.SPECTATOR);
            }
        }
    }

}

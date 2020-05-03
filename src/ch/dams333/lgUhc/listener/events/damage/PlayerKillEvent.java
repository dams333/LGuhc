package ch.dams333.lgUhc.listener.events.damage;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.events.events.LGplayerdieEvent;
import ch.dams333.lgUhc.events.events.LGplayerkillEvent;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerKillEvent implements Listener {
    LgUhc main;
    public PlayerKillEvent(LgUhc main) {
        this.main = main;
    }

    @EventHandler
    public void damage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            if (!e.isCancelled()) {
                if (((Player) e.getEntity()).getHealth() <= e.getFinalDamage()) {
                    e.setCancelled(true);
                    Bukkit.getPluginManager().callEvent(new LGplayerkillEvent(main.lgGame, (Player) e.getEntity(), (Player) e.getDamager()));
                    for(Role role : main.lgGame.getRoles()){
                        if(main.lgsSorts.contains(role.getSort())){
                            for(LGplayer lGplayer : role.getPlayers()){
                                if(lGplayer.getP() == e.getDamager()){
                                    ((Player) e.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 0));
                                    ((Player) e.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1200, 0));
                                }
                            }
                        }
                    }
                    return;
                }
            }
        }
        if (e.getDamager() instanceof Arrow) {
            if (e.getEntity() instanceof Player) {
                if (((Arrow) e.getDamager()).getShooter() instanceof Player) {
                    if (((Player) e.getEntity()).getHealth() <= e.getFinalDamage()) {
                        Player shooted = (Player) e.getEntity();
                        Player shooter = (Player) ((Arrow) e.getDamager()).getShooter();
                        Bukkit.getPluginManager().callEvent(new LGplayerkillEvent(main.lgGame, shooted, shooter));
                        for (Role role : main.lgGame.getRoles()) {
                            if (main.lgsSorts.contains(role.getSort())) {
                                for (LGplayer lGplayer : role.getPlayers()) {
                                    if (lGplayer.getP() == e.getDamager()) {
                                        ((Player) e.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 0));
                                        ((Player) e.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1200, 0));
                                    }
                                }
                            }
                        }
                    }
                    return;
                }
            }
        }
        if (e.getEntity() instanceof Player && !(e.getDamager() instanceof Player)) {
            if (!e.isCancelled()) {
                if (((Player) e.getEntity()).getHealth() <= e.getFinalDamage()) {
                    e.setCancelled(true);
                    Bukkit.getPluginManager().callEvent(new LGplayerdieEvent(main.lgGame, (Player) e.getEntity()));
                }
            }
        }
    }

}

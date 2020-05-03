package ch.dams333.lgUhc.listener.events.damage;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.events.events.LGplayerdieEvent;
import ch.dams333.lgUhc.objects.game.gameStep.GameStep;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.roles.Rsalvateur;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvent implements Listener {
    LgUhc main;
    public DamageEvent(LgUhc main) {
        this.main = main;
    }

    @EventHandler
    public void damage(EntityDamageEvent e){
        if(main.gameStepManager.isStep(GameStep.PREGAME)){
            e.setCancelled(true);
        }

        if(e.getEntity() instanceof Player) {
            if (((Rsalvateur) main.lgGame.getRole(RoleSort.SALVATEUR)).isProtected((Player) e.getEntity())) {
                if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
                    e.setCancelled(true);
                }
            }
            if(main.lgGame.death.keySet().contains(e.getEntity())){
                e.setCancelled(true);
            }
            if(!e.isCancelled()){
                if(e.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK){
                    if(((Player) e.getEntity()).getHealth() <= e.getFinalDamage()){
                        e.setCancelled(true);
                        Bukkit.getPluginManager().callEvent(new LGplayerdieEvent(main.lgGame, (Player) e.getEntity()));
                    }
                }
            }
        }
    }

}

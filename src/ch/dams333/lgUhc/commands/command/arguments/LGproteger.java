package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.objects.game.tasks.Time;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.roles.Rsalvateur;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LGproteger {
    public LGproteger(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if(main.lgPermissionManager.hasPerm(p, "proteger")){
            if(((Rsalvateur)main.lgGame.getRole(RoleSort.SALVATEUR)).canProtect(p)){
                if(args.length == 2){
                    if(Bukkit.getPlayer(args[1]) != null){
                        Player protect = Bukkit.getPlayer(args[1]);
                        if(((Rsalvateur)main.lgGame.getRole(RoleSort.SALVATEUR)).canBeProtected(protect)) {
                           protect.sendMessage(ChatColor.LIGHT_PURPLE + "Le salvateur a décidé de vous protéger pour cet épisode. ");
                           if(!protect.getActivePotionEffects().contains(PotionEffectType.DAMAGE_RESISTANCE)) {
                               protect.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, (Time.EPISODETIME.time() * 20), 0, false, false), true);
                           }
                           p.sendMessage(ChatColor.LIGHT_PURPLE + protect.getName() + " est protégé pour cet épisode");
                           main.lgGame.salvateurProtect(p, protect);
                           return;
                        }
                        p.sendMessage(ChatColor.RED + "Ce joueur a déjà été protégé au dernier épisode");
                        return;
                    }
                    p.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté");
                    return;
                }
                p.sendMessage(ChatColor.RED + "/lg proteger <pseudo>");
                return;
            }
            p.sendMessage(ChatColor.RED + "Vous ne pouvez pas protéger pour le moment");
            return;
        }
        p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire celà");
    }
}

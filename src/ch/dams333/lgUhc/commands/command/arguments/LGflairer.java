package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.roles.Rrenard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LGflairer {
    public LGflairer(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if(main.lgPermissionManager.hasPerm(p, "flairer")){
            if(((Rrenard)main.lgGame.getRole(RoleSort.RENARD)).canSee(p)){
                if(args.length == 2){
                    if(Bukkit.getPlayer(args[1]) != null){
                        Player see = Bukkit.getPlayer(args[1]);
                        if(p.getLocation().distance(see.getLocation()) < 10) {
                            Role role = main.lgGame.getRole(see);
                            if (main.lgsSorts.contains(role.getSort())) {
                                p.sendMessage(ChatColor.LIGHT_PURPLE + see.getDisplayName() + " appartient au camp des loups");
                            } else {
                                p.sendMessage(ChatColor.LIGHT_PURPLE + see.getDisplayName() + " appartient au camp des innocents");
                            }
                            main.lgGame.renardSaw(p);
                            return;
                        }
                        p.sendMessage(ChatColor.RED + "Ce joueur est trop loin");
                        return;
                    }
                    p.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté");
                    return;
                }
                p.sendMessage(ChatColor.RED + "/lg flairer <pseudo>");
                return;
            }
            p.sendMessage(ChatColor.RED + "Vous ne pouvez plus flairer");
            return;
        }
        p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire celà");
    }
}

package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.events.events.LGplayerdieEvent;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.roles.Rvoyante;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LGvoir {
    public LGvoir(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if(main.lgPermissionManager.hasPerm(p, "voir")){
            if(((Rvoyante)main.lgGame.getRole(RoleSort.VOYANTE)).canSee(p)){
                if(args.length == 2){
                    if(Bukkit.getPlayer(args[1]) != null){
                        Player see = Bukkit.getPlayer(args[1]);
                        Role role = main.lgGame.getRole(see);
                        p.sendMessage(ChatColor.LIGHT_PURPLE + see.getName() + " est " + role.getName());
                        main.lgGame.voyanteSaw(p);
                        if(!main.lgsSorts.contains(role.getSort())){
                            p.sendMessage(ChatColor.RED + "Vous avez espionné un membre du village ! Vous perdez 3 coeurs et ne pourrez pas espionner au prochain épisode");
                            main.lgGame.voyanteFail(p);
                            if(p.getHealth() > 6){
                                p.setHealth(p.getHealth() - 6);
                            }else{
                                Bukkit.getPluginManager().callEvent(new LGplayerdieEvent(main.lgGame, p));
                            }
                        }
                        return;
                    }
                    p.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté");
                    return;
                }
                p.sendMessage(ChatColor.RED + "/lg voir <pseudo>");
                return;
            }
            p.sendMessage(ChatColor.RED + "Vous ne pouvez pas espionner pour le moment");
            return;
        }
        p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire celà");
    }
}

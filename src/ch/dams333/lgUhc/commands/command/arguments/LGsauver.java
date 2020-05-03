package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.roles.Rsorciere;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LGsauver {
    public LGsauver(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if(main.lgPermissionManager.hasPerm(p, "sauver")){
            if(args.length == 2){
                if(Bukkit.getPlayer(args[1]) != null){
                    Player res = Bukkit.getPlayer(args[1]);
                    if(((Rsorciere)main.lgGame.getRole(RoleSort.SORCIERE)).canBeResed(res)){
                        main.lgGame.res(res);
                        main.lgGame.sorciereHasRes(p);
                        p.sendMessage(ChatColor.LIGHT_PURPLE + res.getName() + " a été réssucité");
                        return;
                    }
                    p.sendMessage(ChatColor.RED + "Vous ne pouvez pas réssuciter ce joueur");
                    return;
                }
                p.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté");
                return;
            }
            p.sendMessage(ChatColor.RED + "/lg sauver <pseudo>");
            return;
        }
        p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire celà");
    }
}

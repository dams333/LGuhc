package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.roles.Rinfecteperedesloups;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Lginfecter {
    public Lginfecter(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if(main.lgPermissionManager.hasPerm(p, "infecter")){
            if(args.length == 2){
                if(Bukkit.getPlayer(args[1]) != null){
                    Player res = Bukkit.getPlayer(args[1]);
                    if(((Rinfecteperedesloups)main.lgGame.getRole(RoleSort.INFECT)).canBeResed(res)){
                        main.lgGame.infect(res);
                        main.lgGame.infectHasRes(p);
                        p.sendMessage(ChatColor.LIGHT_PURPLE + res.getName() + " a été infecté");
                        return;
                    }
                    p.sendMessage(ChatColor.RED + "Vous ne pouvez pas infecter ce joueur");
                    return;
                }
                p.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté");
                return;
            }
            p.sendMessage(ChatColor.RED + "/lg infecter <pseudo>");
            return;
        }
        p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire celà");
    }
}

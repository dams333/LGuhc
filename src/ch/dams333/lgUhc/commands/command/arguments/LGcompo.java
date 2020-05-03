package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.objects.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LGcompo {
    public LGcompo(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if(main.lgPermissionManager.hasPerm(p, "compo")){
            for(Role role : main.lgGame.getRoles()){
                if(role.getPlayers().size() > 0) {
                    p.sendMessage(ChatColor.GOLD + "- " + ChatColor.WHITE + role.getName() + ChatColor.GRAY + " : " + ChatColor.GREEN + role.getPlayers().size());
                }
            }
            return;
        }
        p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire cela");
    }
}

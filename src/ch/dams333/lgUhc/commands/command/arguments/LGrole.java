package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.roles.Renfantsauvage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LGrole {
    public LGrole(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if(main.lgPermissionManager.hasPerm(p, "role")) {
            if (main.lgGame.getRole(p) != null) {
                Role role = main.lgGame.getRole(p);
                p.sendMessage(ChatColor.BLUE + "[Privé] Vous êtes " + ChatColor.BOLD + role.getName());
                p.sendMessage(ChatColor.GOLD + role.getDescription());
                if (main.lgsSorts.contains(role.getSort())) {
                    p.sendMessage(ChatColor.RED + "===== Liste des Loups =====");
                    StringBuilder sb = new StringBuilder();
                    for (Player lg : main.lgGame.lgList()) {
                        sb.append(lg.getName() + "     ");
                    }
                    p.sendMessage(ChatColor.RED + sb.toString());
                }
                if(role.getSort() == RoleSort.ENFANT){
                    if(((Renfantsauvage) role).isTransformed(p)){
                        p.sendMessage(ChatColor.RED + "===== Liste des Loups =====");
                        StringBuilder sb = new StringBuilder();
                        for (Player lg : main.lgGame.lgList()) {
                            sb.append(lg.getName() + "     ");
                        }
                        p.sendMessage(ChatColor.RED + sb.toString());
                    }
                }
                for(LGplayer lGplayer : role.getPlayers()){
                    if(lGplayer.getP() == p){
                        if(lGplayer.isInfect()){
                            p.sendMessage(ChatColor.RED + "===== Liste des Loups =====");
                            StringBuilder sb = new StringBuilder();
                            for (Player lg : main.lgGame.lgList()) {
                                sb.append(lg.getName() + "     ");
                            }
                            p.sendMessage(ChatColor.RED + sb.toString());
                        }
                    }
                }
                return;
            }
            p.sendMessage(ChatColor.RED + "Vous n'avez pas de rôle");
            return;
        }
        p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire celà");
    }
}

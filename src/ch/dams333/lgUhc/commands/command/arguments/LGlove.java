package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.roles.Rcupidon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LGlove {
    public LGlove(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if(main.lgPermissionManager.hasPerm(p, "love")) {
            if (!((Rcupidon) main.lgGame.getRole(RoleSort.CUPIDON)).hasMadeACouple()) {
                if (args.length == 3) {
                    if (Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[2]) != null) {
                        Player p1 = Bukkit.getPlayer(args[1]);
                        Player p2 = Bukkit.getPlayer(args[2]);
                        if (p1 != p2) {
                            main.lgGame.setCouple(p1, p2);
                            p1.sendMessage(ChatColor.LIGHT_PURPLE + "Vous êtes en couple avec " + p2.getName() + ". Craftez une boussole pour le retrouver");
                            p2.sendMessage(ChatColor.LIGHT_PURPLE + "Vous êtes en couple avec " + p1.getName() + ". Craftez une boussole pour le retrouver");
                            return;
                        }
                        p.sendMessage(ChatColor.RED + "Un joueur ne peut pas être en couple avec lui-même");
                        return;
                    }
                    p.sendMessage(ChatColor.RED + "Ces joueurs ne sont pas en ligne");
                    return;
                }
                p.sendMessage(ChatColor.RED + "/lg love <pseudo> <pseudo>");
                return;
            }
            p.sendMessage(ChatColor.RED + "Un couple a déjà été formé");
            return;
        }
        p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire celà");
    }
}

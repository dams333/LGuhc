package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.objects.game.gameStep.GameStep;
import ch.dams333.lgUhc.objects.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LGconfig {
    public LGconfig(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if (main.gameStepManager.isStep(GameStep.PREGAME)) {
            if (args.length > 1) {
                if (args[1].equalsIgnoreCase("list")) {
                    p.sendMessage(ChatColor.GREEN + "======= Liste des rôles =======");
                    for (Role role : main.lgGame.getRoles()) {
                        p.sendMessage(ChatColor.GOLD + "- " + ChatColor.WHITE + role.getSimpleName() + ChatColor.GRAY + " : " + ChatColor.WHITE + role.getNeededPlayers());
                    }
                    return;
                }
                if (args[1].equalsIgnoreCase("set")) {
                    if (args.length == 4) {
                        String roleName = args[2];
                        if (main.lgGame.isRole(roleName)) {
                            if (main.API.isInt(args[3])) {
                                int number = Integer.parseInt(args[3]);
                                if (number >= 0) {
                                    main.lgGame.setRole(roleName, number);
                                    p.sendMessage(ChatColor.GREEN + "Il y a maintenant " + number + " " + roleName);
                                    return;
                                }
                            }
                            p.sendMessage(ChatColor.RED + "Veuillez entrer un nombre valide");
                            return;
                        }
                        p.sendMessage(ChatColor.RED + "Ce rôle n'existe pas");
                        return;
                    }
                    p.sendMessage(ChatColor.RED + "/lg config set <role name> <role number>");
                    return;
                }
            }
            p.sendMessage(ChatColor.RED + "/lg config <list/set>");
            return;
        }
        p.sendMessage(ChatColor.RED + "Ce n'est pas le moment de changer la config");
    }
}

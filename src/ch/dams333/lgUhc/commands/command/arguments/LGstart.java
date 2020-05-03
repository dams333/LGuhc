package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.events.events.LGstartgameEvent;
import ch.dams333.lgUhc.objects.game.gameStep.GameStep;
import ch.dams333.lgUhc.objects.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LGstart {
    public LGstart(LgCommand lgCommand, Player p, String[] args, LgUhc main) {


        if (main.gameStepManager.isStep(GameStep.PREGAME)) {
            int neededPlayers = 0;

            for (Role role : main.lgGame.getRoles()) {
                neededPlayers += role.getNeededPlayers();
            }

            if (neededPlayers == Bukkit.getOnlinePlayers().size()) {

                for(Player online : Bukkit.getOnlinePlayers()){
                    if(!online.hasPermission("lg.commands.lg") || !online.hasPermission("lg.commands.lgreload")){
                        p.sendMessage(ChatColor.RED + "Tous les joueurs n'ont pas les permissions(lg.commands.lg et lg.commands.lgreload)");
                        return;
                    }
                }

                Bukkit.getPluginManager().callEvent(new LGstartgameEvent(main.lgGame));
                return;
            }
            p.sendMessage(ChatColor.RED + "Le nombre de rôle dans la config ne correspond pas au nombre de joueur connectés");
            return;
        }
        p.sendMessage(ChatColor.RED + "Ce n'est pas le moment de démarrer la partie");
    }
}

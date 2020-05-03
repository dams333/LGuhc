package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LGvote {
    public LGvote(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if(main.lgPermissionManager.hasPerm(p, "vote")){
            if(main.lgGame.timeToVote){
                if(!main.lgGame.vote.keySet().contains(p)){
                    if(args.length == 2){
                        if(Bukkit.getPlayer(args[1]) != null){
                            Player voted = Bukkit.getPlayer(args[1]);
                            main.lgGame.vote.put(p, voted);
                            p.sendMessage(ChatColor.GREEN + "Votre vote a été pris en compte");
                            return;
                        }
                        p.sendMessage(ChatColor.RED + "ce joueur n'est pas en ligne");
                        return;
                    }
                    p.sendMessage(ChatColor.RED + "/lg vote <pseudo>");
                    return;
                }
                p.sendMessage(ChatColor.RED + "Vous avez déjà voté");
                return;
            }
            p.sendMessage(ChatColor.RED + "Ce n'est pas le moment de voter");
            return;
        }
        p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire celà");
    }
}

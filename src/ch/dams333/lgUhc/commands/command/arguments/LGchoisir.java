package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.roles.Renfantsauvage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LGchoisir {
    public LGchoisir(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if(main.lgPermissionManager.hasPerm(p, "choisir")){
            if(args.length == 2){
                String pseudo = args[1];
                for(Role role : main.lgGame.getRoles()){
                    for(LGplayer lGplayer : role.getPlayers()){
                        if(lGplayer.getP().getName().equalsIgnoreCase(pseudo)){
                            if(!((Renfantsauvage)main.lgGame.getRole(RoleSort.ENFANT)).hasModel(p)){
                                ((Renfantsauvage)main.lgGame.getRole(RoleSort.ENFANT)).chooseModel(p, pseudo);
                                p.sendMessage(ChatColor.DARK_GREEN + "Vous avez bien choisi votre modèle. Si celui-ci vient à mourir, vous passerez dans le camps des loups");
                                return;
                            }
                            p.sendMessage(ChatColor.RED + "Vous avez déjà un modèle");
                            return;
                        }
                    }
                }
                p.sendMessage(ChatColor.RED + "Ce joueur n'est plus en vie");
                return;
            }
            p.sendMessage(ChatColor.RED + "/lg choisir <pseudo>");
            return;
        }
        p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire cela");
    }
}

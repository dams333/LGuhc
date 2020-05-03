package ch.dams333.lgUhc.commands.command.arguments;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import ch.dams333.lgUhc.objects.roles.roles.Rdetective;
import ch.dams333.lgUhc.objects.roles.roles.Renfantsauvage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LGenquete {
    public LGenquete(LgCommand lgCommand, Player p, String[] args, LgUhc main) {
        if(main.lgPermissionManager.hasPerm(p, "enquete")){
            if(((Rdetective)main.lgGame.getRole(RoleSort.DETECTIVE)).canEnquete(p)){
                if(Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[2]) != null){
                    Player p1 = Bukkit.getPlayer(args[1]);
                    Player p2 = Bukkit.getPlayer(args[2]);
                    if(p1 != p && p2 != p) {
                        if (((Rdetective) main.lgGame.getRole(RoleSort.DETECTIVE)).canBeEnqueted(p, p1, p2)) {
                            main.lgGame.enqueterHasMade(p, p1, p2);

                            Role role1 = null;
                            Role role2 = null;

                            for (Role role : main.lgGame.getRoles()) {
                                for (LGplayer lGplayer : role.getPlayers()) {
                                    if (lGplayer.getP() == p1) {
                                        role1 = role;
                                    }
                                    if (lGplayer.getP() == p2) {
                                        role2 = role;
                                    }
                                }
                            }

                            if (role1.getWin() == role2.getWin()) {
                                p.sendMessage(ChatColor.LIGHT_PURPLE + "Ils appartiennent tous les 2 au même camps");
                            } else {
                                RoleWin win1 = role1.getWin();
                                RoleWin win2 = role2.getWin();
                                if (role1.getSort() == RoleSort.ENFANT) {
                                    if (((Renfantsauvage) role1).isTransformed(p1)) {
                                        win1 = RoleWin.LOUPGAROU;
                                    } else {
                                        win1 = RoleWin.VILLAGE;
                                    }
                                }
                                if (role2.getSort() == RoleSort.ENFANT) {
                                    if (((Renfantsauvage) role2).isTransformed(p1)) {
                                        win2 = RoleWin.LOUPGAROU;
                                    } else {
                                        win2 = RoleWin.VILLAGE;
                                    }
                                }
                                for (LGplayer lGplayer : role1.getPlayers()) {
                                    if (lGplayer.isInfect()) {
                                        win1 = RoleWin.LOUPGAROU;
                                    }
                                }
                                for (LGplayer lGplayer : role2.getPlayers()) {
                                    if (lGplayer.isInfect()) {
                                        win2 = RoleWin.LOUPGAROU;
                                    }
                                }
                                if (win1 == win2) {
                                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Ils appartiennent tous les 2 au même camps");
                                } else {
                                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Ils n'appartiennent pas au même camps");
                                }
                            }

                            return;
                        }
                        p.sendMessage(ChatColor.RED + "Vous avez déjà espionné au moins un de ces joueurs");
                        return;
                    }
                    p.sendMessage(ChatColor.RED + "Vous ne pouvez pas vous enquêter vous-même");
                    return;
                }
                p.sendMessage(ChatColor.RED + "Ces joueurs ne sont pas connectés");
                return;
            }
            p.sendMessage(ChatColor.RED + "Vous ne pouvez pas enquêter pour le moment");
            return;
        }
        p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire celà");
    }
}

package ch.dams333.lgUhc.commands;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgArg;
import ch.dams333.lgUhc.objects.roles.Role;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabCompletion implements TabCompleter {

    LgUhc main;
    public TabCompletion(LgUhc main) {
        this.main = main;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("lg")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(args.length == 1) {
                    List<String> complete = new ArrayList<>();
                    for (LgArg lgArgs : LgArg.getLgArgs()) {
                        if (main.lgPermissionManager.hasPerm(p, lgArgs.getPerm())) {
                            complete.add(lgArgs.toString());
                        }
                    }
                    return complete;
                }
                if(args.length == 2){
                    if(args[0].equalsIgnoreCase("config")){
                        return Arrays.asList("list", "set");
                    }
                }
                if(args.length == 3){
                    if(args[0].equalsIgnoreCase("config") && args[1].equalsIgnoreCase("set")){
                        List<String> complete = new ArrayList<>();
                        for(Role role : main.lgGame.getRoles()){
                            complete.add(role.getSimpleName());
                        }
                        return complete;
                    }
                }
                if(args.length == 4){
                    List<String> roles = new ArrayList<>();
                    for(Role role : main.lgGame.getRoles()){
                        roles.add(role.getSimpleName());
                    }
                    if(args[0].equalsIgnoreCase("config") && args[1].equalsIgnoreCase("set") && roles.contains(args[2])){
                        return Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
                    }
                }
            }
        }
        return null;
    }
}

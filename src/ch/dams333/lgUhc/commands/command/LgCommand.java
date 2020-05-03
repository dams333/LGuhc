package ch.dams333.lgUhc.commands.command;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.arguments.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LgCommand implements CommandExecutor {
    LgUhc main;
    public LgCommand(LgUhc main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){

                if(args[0].equalsIgnoreCase(LgArg.CONFIG.toString())){
                    new LGconfig(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.START.toString())){
                    new LGstart(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.LOVE.toString())){
                    new LGlove(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.INFECTER.toString())){
                    new Lginfecter(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.SAUVER.toString())){
                    new LGsauver(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.VOIR.toString())){
                    new LGvoir(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.FLAIRER.toString())){
                    new LGflairer(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.PROTEGER.toString())){
                    new LGproteger(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.ROLE.toString())){
                    new LGrole(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.COMPO.toString())){
                    new LGcompo(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.VOTE.toString())){
                    new LGvote(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.ENQUETE.toString())){
                    new LGenquete(this, p, args, main);
                    return true;
                }
                if(args[0].equalsIgnoreCase(LgArg.CHOISIR.toString())){
                    new LGchoisir(this, p, args, main);
                    return true;
                }

            }
            StringBuilder sb = new StringBuilder();
            sb.append(ChatColor.RED + "/lg <");
            for(LgArg lgArgs : LgArg.getLgArgs()){
                if(main.lgPermissionManager.hasPerm(p, lgArgs.getPerm())) {
                    sb.append(lgArgs.toString() + "/");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(">");
            p.sendMessage(sb.toString());
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Il faut être sur le serveur pour exécuter cela");
        return false;
    }
}

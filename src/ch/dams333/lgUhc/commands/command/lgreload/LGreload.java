package ch.dams333.lgUhc.commands.command.lgreload;

import ch.dams333.lgUhc.LgUhc;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LGreload implements CommandExecutor {
    LgUhc main;
    public LGreload(LgUhc main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            main.lgGame.reloadPlayer(p);
            p.sendMessage(ChatColor.LIGHT_PURPLE + "Vous avez été reload");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Vous devez être connecté sur le servezr");
        return false;
    }
}

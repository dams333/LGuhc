package ch.dams333.lgUhc.utils;

import ch.dams333.damsLib.ScoreboardSign;
import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.game.tasks.Time;
import ch.dams333.lgUhc.objects.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ScoreboardManager {
    LgUhc main;
    public ScoreboardManager(LgUhc lgUhc) {
        this.main = lgUhc;
        this.scoreboards = new HashMap<>();
    }

    private Map<Player, ScoreboardSign> scoreboards;

    public void createSB(){
        for(Player p : Bukkit.getOnlinePlayers()){
            ScoreboardSign sb = new ScoreboardSign(p, ChatColor.WHITE + "" + ChatColor.BOLD + "LG UHC");
            sb.create();

            sb.setLine(0, "§a");
            sb.setLine(1, ChatColor.AQUA + "Episode 1");

            int inGamePlayers = Bukkit.getOnlinePlayers().size();

            sb.setLine(2, ChatColor.RED + String.valueOf(inGamePlayers) + " " + ChatColor.DARK_RED + "joueurs");
            sb.setLine(3, "§c");
            sb.setLine(4, ChatColor.GOLD + "Timer: " + ChatColor.YELLOW + getTimeIntoStringWithoutHour(0));
            sb.setLine(5, "§r");
            sb.setLine(6, ChatColor.DARK_GREEN + "Border: " + ChatColor.GREEN + "±" + main.lgGame.border);

            this.scoreboards.put(p, sb);
        }
    }

    public void updateSB(int timer){
            for (Player p : this.scoreboards.keySet()) {
                ScoreboardSign sb = this.scoreboards.get(p);
                sb.setLine(0, "§a");
                int day = (timer / Time.EPISODETIME.time()) + 1;
                sb.setLine(1, ChatColor.AQUA + "Episode " + day);

                int inGamePlayers = 0;
                if(timer <= Time.CALLROLS.time()){
                    inGamePlayers = main.lgGame.inGamePlayers.size();
                }else {
                    for (Role role : main.lgGame.getRoles()) {
                        inGamePlayers += role.getPlayers().size();
                    }
                }

                sb.setLine(2, ChatColor.RED + String.valueOf(inGamePlayers) + " " + ChatColor.DARK_RED + "joueurs");
                sb.setLine(3, "§c");
                int thisTime = timer - ((timer / Time.EPISODETIME.time()) * Time.EPISODETIME.time());
                sb.setLine(4, ChatColor.GOLD + "Timer: " + ChatColor.YELLOW + getTimeIntoStringWithoutHour(thisTime));
                sb.setLine(5, "§r");
                sb.setLine(6, ChatColor.DARK_GREEN + "Border: " + ChatColor.GREEN + "±" + main.lgGame.border);

                this.scoreboards.put(p, sb);
            }
    }



    private String getTimeIntoStringWithoutHour(int seconds){
        int min = (seconds % 3600) / 60;
        int sec = seconds % 60;

        String secSTR = "";
        String minSTR = "";
        if(sec < 10){
            secSTR = "0" + String.valueOf(sec);
        }else{
            secSTR = String.valueOf(sec);
        }
        if(min < 10){
            minSTR = "0" + String.valueOf(min);
        }else{
            minSTR = String.valueOf(min);
        }

        return minSTR + ":" + secSTR;
    }

    public void reloadPlayer(Player p) {
        ScoreboardSign sb = new ScoreboardSign(p, ChatColor.WHITE + "" + ChatColor.BOLD + "LG UHC");
        sb.create();
        this.scoreboards.put(p, sb);
    }
}

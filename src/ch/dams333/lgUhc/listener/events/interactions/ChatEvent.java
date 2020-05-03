package ch.dams333.lgUhc.listener.events.interactions;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.game.gameStep.GameStep;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    LgUhc main;
    public ChatEvent(LgUhc main) {
        this.main = main;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(main.gameStepManager.isStep(GameStep.GAME)){
            e.setCancelled(true);
            if(e.getMessage().startsWith("!") && e.getPlayer().hasPermission("lg.br")){
                Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Annonce]" + ChatColor.RESET + ChatColor.GRAY + " " + e.getMessage());
            }else{
                Bukkit.broadcastMessage(ChatColor.GRAY + e.getMessage());
            }
        }
    }

}

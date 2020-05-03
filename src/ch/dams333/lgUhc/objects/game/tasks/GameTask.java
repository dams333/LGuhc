package ch.dams333.lgUhc.objects.game.tasks;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.events.events.*;
import ch.dams333.lgUhc.objects.game.gameStep.GameStep;
import net.minecraft.server.v1_15_R1.ChatMessageType;
import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTask extends BukkitRunnable {
    LgUhc main;
    public GameTask(LgUhc main) {
        this.main = main;
    }

    int preStart = Time.PRESTART.time();

    int timer = 0;

    @Override
    public void run() {
        if(preStart > 0) {
            preStart = preStart - 1;
            if (preStart == 0) {
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.removePotionEffect(PotionEffectType.BLINDNESS);
                    p.removePotionEffect(PotionEffectType.SLOW);
                }
                main.gameStepManager.setStep(GameStep.GAME);
            }
        }else {

            timer = timer + 1;

            main.scoreboardManager.updateSB(timer);

            if (timer == Time.CALLROLS.time()) {
                Bukkit.getPluginManager().callEvent(new LGselectrolesEvent(main.lgGame));
                Bukkit.getWorld("world").setTime(0);
                Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            }

            if (timer >= Time.CALLROLS.time()) {
                Bukkit.getWorld("world").setTime(Bukkit.getWorld("world").getTime() + (Time.EPISODETIME.time()/30));

                for (Player p : Bukkit.getOnlinePlayers()) {
                    Location loc = new Location(Bukkit.getWorld("world"), 0, p.getLocation().getY(), 0);
                    if (p.getLocation().distance(loc) > 1200) {
                        String toSend =
                                ChatColor.DARK_PURPLE + "Distance au centre : "
                                        + ChatColor.DARK_BLUE + "✈ Supérieur à 1200 blocks";
                        sendActionBar(p, toSend);
                    }
                    if (p.getLocation().distance(loc) <= 1200 && p.getLocation().distance(loc) > 900) {
                        String toSend =
                                ChatColor.DARK_PURPLE + "Distance au centre : "
                                        + ChatColor.DARK_AQUA + "✈ Entre 900 et 1200 blocks";
                        sendActionBar(p, toSend);
                    }
                    if (p.getLocation().distance(loc) <= 900 && p.getLocation().distance(loc) > 600) {
                        String toSend =
                                ChatColor.DARK_PURPLE + "Distance au centre : "
                                        + ChatColor.GREEN + "✈ Entre 600 et 900 blocks";
                        sendActionBar(p, toSend);
                    }
                    if (p.getLocation().distance(loc) <= 600 && p.getLocation().distance(loc) > 300) {
                        String toSend =
                                ChatColor.DARK_PURPLE + "Distance au centre : "
                                        + ChatColor.YELLOW + "✈ Entre 300 et 600 blocks";
                        sendActionBar(p, toSend);
                    }
                    if (p.getLocation().distance(loc) <= 300) {
                        String toSend =
                                ChatColor.DARK_PURPLE + "Distance au centre : "
                                        + ChatColor.GOLD + "✈ Entre 0 et 300 blocks";
                        sendActionBar(p, toSend);
                    }
                }

                if (timer % Time.EPISODETIME.time() == 0) {
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "===== Fin de l'épisode " + (timer / Time.EPISODETIME.time()) + " =====");
                    Bukkit.getPluginManager().callEvent(new LGepisodeEvent(main.lgGame, timer));
                }

                if(timer % (Time.EPISODETIME.time() + Time.VOTETIME.time()) == 0){
                    Bukkit.getPluginManager().callEvent(new LGvoteendEvent(main.lgGame));
                }

                int time = timer - ((timer / Time.EPISODETIME.time()) * Time.EPISODETIME.time());

                if (time == Time.DAY1.time() || time == Time.DAY2.time()) {
                    Bukkit.getPluginManager().callEvent(new LGdayEvent(main.lgGame));
                }
                if (time == Time.NIGHT1.time() || time == Time.NIGHT2.time()) {
                    Bukkit.getPluginManager().callEvent(new LGnightEvent(main.lgGame));
                }

            }

            if(timer > Time.TIMEREDUCTION.time() && timer < Time.TIMEENDREDUCTION.time()){
                main.lgGame.border = main.lgGame.border - 1;
                Bukkit.getWorld("world").getWorldBorder().setSize(main.lgGame.border * 2);
            }

        }
    }

    private void sendActionBar(Player player, String message) {
        IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");

        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(chat, ChatMessageType.GAME_INFO);

        CraftPlayer craft = (CraftPlayer) player;

        craft.getHandle().playerConnection.sendPacket(packetPlayOutChat);
    }

}

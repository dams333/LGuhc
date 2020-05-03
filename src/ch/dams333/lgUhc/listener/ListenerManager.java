package ch.dams333.lgUhc.listener;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.listener.events.block.BreakBlockEvent;
import ch.dams333.lgUhc.listener.events.block.PlaceBlock;
import ch.dams333.lgUhc.listener.events.connexion.DisconnectEvent;
import ch.dams333.lgUhc.listener.events.connexion.JoinEvent;
import ch.dams333.lgUhc.listener.events.damage.DamageEvent;
import ch.dams333.lgUhc.listener.events.damage.PlayerKillEvent;
import ch.dams333.lgUhc.listener.events.interactions.ChatEvent;
import ch.dams333.lgUhc.listener.events.interactions.ClickOnItemEvent;
import ch.dams333.lgUhc.listener.events.statu.EnchantEvent;
import ch.dams333.lgUhc.listener.events.statu.EquipArmorEvent;
import ch.dams333.lgUhc.listener.events.statu.FoodEvent;
import ch.dams333.lgUhc.listener.events.statu.MoveEvent;

public class ListenerManager {
    public ListenerManager(LgUhc main) {

        main.getServer().getPluginManager().registerEvents(new JoinEvent(main), main);
        main.getServer().getPluginManager().registerEvents(new DamageEvent(main), main);
        main.getServer().getPluginManager().registerEvents(new FoodEvent(main), main);
        main.getServer().getPluginManager().registerEvents(new PlaceBlock(main), main);
        main.getServer().getPluginManager().registerEvents(new BreakBlockEvent(main), main);
        main.getServer().getPluginManager().registerEvents(new ClickOnItemEvent(main), main);
        main.getServer().getPluginManager().registerEvents(new MoveEvent(main), main);
        main.getServer().getPluginManager().registerEvents(new PlayerKillEvent(main), main);
        main.getServer().getPluginManager().registerEvents(new EquipArmorEvent(main), main);
        main.getServer().getPluginManager().registerEvents(new EnchantEvent(main), main);
        main.getServer().getPluginManager().registerEvents(new ChatEvent(main), main);
        main.getServer().getPluginManager().registerEvents(new DisconnectEvent(main), main);
    }

}

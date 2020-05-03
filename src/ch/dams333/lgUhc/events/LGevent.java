package ch.dams333.lgUhc.events;

import ch.dams333.lgUhc.objects.game.lgGame.LgGame;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LGevent extends Event {
    final LgGame game;

    public LGevent(LgGame game) {
        this.game = game;
    }

    public LgGame getGame() {
        return game;
    }

    private static final HandlerList handlers = new HandlerList();
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

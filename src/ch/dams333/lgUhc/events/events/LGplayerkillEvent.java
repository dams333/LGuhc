package ch.dams333.lgUhc.events.events;

import ch.dams333.lgUhc.events.LGevent;
import ch.dams333.lgUhc.objects.game.lgGame.LgGame;
import org.bukkit.entity.Player;

public class LGplayerkillEvent extends LGevent {
    private Player p;
    private Player killer;

    public Player getKiller() {
        return killer;
    }

    public Player getPlayer() {
        return p;
    }

    public LGplayerkillEvent(LgGame game, Player p, Player killer) {
        super(game);
        this.p = p;
        this.killer = killer;
    }
}

package ch.dams333.lgUhc.events.events;

import ch.dams333.lgUhc.events.LGevent;
import ch.dams333.lgUhc.objects.game.lgGame.LgGame;
import org.bukkit.entity.Player;

public class LGplayerdieEvent extends LGevent {
    private Player p;

    public Player getPlayer() {
        return p;
    }

    public LGplayerdieEvent(LgGame game, Player p) {
        super(game);
        this.p = p;

    }
}

package ch.dams333.lgUhc.events.events;

import ch.dams333.lgUhc.events.LGevent;
import ch.dams333.lgUhc.objects.game.lgGame.LgGame;

public class LGepisodeEvent extends LGevent {
    private int timer;

    public int getTimer() {
        return timer;
    }

    public LGepisodeEvent(LgGame game, int timer) {
        super(game);
        this.timer = timer;

    }
}

package ch.dams333.lgUhc.events.events;

import ch.dams333.lgUhc.events.LGevent;
import ch.dams333.lgUhc.objects.game.lgGame.LgGame;

public class LGstartgameEvent extends LGevent {
    public LGstartgameEvent(LgGame game) {
        super(game);
    }

}

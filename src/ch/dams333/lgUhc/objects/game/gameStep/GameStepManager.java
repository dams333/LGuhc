package ch.dams333.lgUhc.objects.game.gameStep;

import ch.dams333.lgUhc.LgUhc;

public class GameStepManager {
    LgUhc main;
    private GameStep gameStep;
    public GameStepManager(LgUhc lgUhc) {
        this.main = lgUhc;
        this.gameStep = GameStep.PREGAME;
    }

    public boolean isStep(GameStep gameStep){
        return this.gameStep == gameStep;
    }
    public void setStep(GameStep gameStep){
        this.gameStep = gameStep;
    }

}

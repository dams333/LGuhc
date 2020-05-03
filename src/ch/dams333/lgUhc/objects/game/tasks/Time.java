package ch.dams333.lgUhc.objects.game.tasks;

public enum Time {

    PRESTART(10), //10
    CALLROLS(10), //1200
    EPISODETIME(600), //1200
    DAY1(0), //0
    DAY2(300), //600
    NIGHT1(150), //300
    NIGHT2(450), //900
    VOTETIME(60), //60
    TIMEREDUCTION(5400), //5400 milieu ep 5
    TIMEENDREDUCTION(TIMEREDUCTION.time() + 1100),
    EPISODESTARTVOTE(2); //3

    private int timeInSec;

    Time(int timeInSec) {
        this.timeInSec = timeInSec;
    }

    public int time(){
        return timeInSec;
    }
}

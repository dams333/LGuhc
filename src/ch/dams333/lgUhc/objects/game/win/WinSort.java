package ch.dams333.lgUhc.objects.game.win;

public enum WinSort {

    COUPLE("Le couple remporte la partie"),
    LOUPS("Les loups remportent la partie"),
    VILLAGE("Le village remporte la partie"),
    ASSASSIN("L'assassin remporte la partie"),
    LOUPBLANC("Le loup garou blanc remporte la partie");

    String text = "";

    WinSort(String text) {
        this.text = text;
    }

    public String getString(){
        return text;
    }

}

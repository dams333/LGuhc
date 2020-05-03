package ch.dams333.lgUhc.objects.couple;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.entity.Player;

public class Couple {

    private Player p1;
    private Player p2;
    private CoupleWin win;
    private boolean founded;

    LgUhc main;

    public boolean isFounded() {
        return founded;
    }

    public void setFounded(boolean founded) {
        this.founded = founded;
    }

    public Couple(Player p1, Player p2, LgUhc main) {
        this.p1 = p1;
        this.p2 = p2;
        this.main = main;
        this.founded = false;


        if(main.lgGame.getRole(p1).getWin() == RoleWin.VILLAGE && main.lgGame.getRole(p2).getWin() == RoleWin.VILLAGE){
            this.win = CoupleWin.VILLAGE;
        }else if(main.lgGame.getRole(p1).getWin() == RoleWin.LOUPGAROU && main.lgGame.getRole(p2).getWin() == RoleWin.LOUPGAROU){
            this.win = CoupleWin.LOUP;
        }else{
            this.win = CoupleWin.SOLO;
        }

    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public void setWin(CoupleWin win) {
        this.win = win;
    }

    public Player getP1() {

        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public CoupleWin getWin() {
        return win;
    }

    public void reloadPlayer(Player p) {
        if(p1.getName().equals(p.getName())){
            p1 = p;
        }
        if(p2.getName().equals(p.getName())){
            p2 = p;
        }
    }
}

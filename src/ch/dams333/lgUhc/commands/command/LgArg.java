package ch.dams333.lgUhc.commands.command;

import java.util.ArrayList;
import java.util.List;

public enum LgArg {

    CONFIG("config", "config"),
    START("start", "start"),
    LOVE("love", "love"),
    INFECTER("infecter", "infecter"),
    SAUVER("sauver", "sauver"),
    VOIR("voir", "voir"),
    FLAIRER("flairer", "flairer"),
    PROTEGER("proteger", "proteger"),
    ROLE("role", "role"),
    VOTE("vote", "vote"),
    COMPO("compo", "compo"),
    CHOISIR("choisir", "choisir"),
    ENQUETE("enquete", "enquete");

    private String text = "";

    LgArg(String name, String perm){
        this.text = name;
        this.perm = perm;
    }

    public String toString(){
        return text;
    }

    public static LgArg fromString(String text) {
        for (LgArg lgArgs : LgArg.values()) {
            if (lgArgs.text.equalsIgnoreCase(text)) {
                return lgArgs;
            }
        }
        return null;
    }

    private String perm = "";

    public String getPerm(){
        return perm;
    }

    public static List<LgArg> getLgArgs(){
        List<LgArg> args = new ArrayList<>();
        for (LgArg lgArgs : LgArg.values()) {
            args.add(lgArgs);
        }
        return args;
    }

}

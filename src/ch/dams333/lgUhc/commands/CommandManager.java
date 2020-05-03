package ch.dams333.lgUhc.commands;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.commands.command.LgCommand;
import ch.dams333.lgUhc.commands.command.lgreload.LGreload;

public class CommandManager {
    public CommandManager(LgUhc main) {
        main.getCommand("lg").setExecutor(new LgCommand(main));
        main.getCommand("lg").setTabCompleter(new TabCompletion(main));
        main.getCommand("lgreload").setExecutor(new LGreload(main));
    }
}

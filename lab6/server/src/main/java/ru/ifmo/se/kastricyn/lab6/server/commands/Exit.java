package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommandManager;

/**
 * Команда выйти из программы
 */
public class Exit extends AbstractCommand {

    public Exit() {
        super("exit", "завершить программу");

        setArgumentTypes(AbstractCommandManager.class);
    }

    //TODO: общая функция для всех выходов из программы

    @Override
    public void execute(String ... args) {
        AbstractCommandManager cm = (AbstractCommandManager) this.args.get(0);
        cm.executeCommand("Save");
        cm.exit();
    }

}

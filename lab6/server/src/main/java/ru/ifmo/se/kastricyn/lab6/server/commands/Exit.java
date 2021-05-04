package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.server.CommandManager;

/**
 * Команда выйти из программы
 */
public class Exit extends AbstractCommand {

    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");

        setArgumentTypes(CommandManager.class);
    }

    //TODO: общая функция для всех выходов из программы

    @Override
    public void execute(String ... args) {
        CommandManager cm = (CommandManager) this.args.get(0);
        cm.exit();
    }

}

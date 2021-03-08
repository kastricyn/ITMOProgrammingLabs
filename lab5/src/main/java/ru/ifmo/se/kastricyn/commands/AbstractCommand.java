package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.Command;
import ru.ifmo.se.kastricyn.CommandManager;

public abstract class AbstractCommand implements Command {
    final String name;
    final String description;

    AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }
}

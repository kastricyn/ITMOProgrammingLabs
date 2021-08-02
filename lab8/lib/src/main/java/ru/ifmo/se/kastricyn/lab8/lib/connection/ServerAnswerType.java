package ru.ifmo.se.kastricyn.lab8.lib.connection;

import java.io.Serializable;

public enum ServerAnswerType implements Serializable {
    OK,
    NEED_ARGS,
    NOT_FOUND_COMMAND,
}

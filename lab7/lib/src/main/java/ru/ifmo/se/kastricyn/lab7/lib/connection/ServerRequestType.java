package ru.ifmo.se.kastricyn.lab7.lib.connection;

import java.io.Serializable;

public enum ServerRequestType implements Serializable {
    COMMAND,
    INITIALIZATION;
}

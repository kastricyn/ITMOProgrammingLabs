package ru.ifmo.se.kastricyn.lab6.server;

import ru.ifmo.se.kastricyn.lab6.lib.ServerAnswer;
import ru.ifmo.se.kastricyn.lab6.lib.ServerRequest;

public class Process {
    static int answnumb = 0;
    static ServerAnswer getAnswer(ServerRequest request){
        return new ServerAnswer(Integer.toString(answnumb++));
    }

}

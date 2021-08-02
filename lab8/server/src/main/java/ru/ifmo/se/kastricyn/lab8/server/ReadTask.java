package ru.ifmo.se.kastricyn.lab8.server;

import ru.ifmo.se.kastricyn.lab8.lib.connection.ServerRequest;

import java.util.concurrent.Callable;

public class ReadTask implements Callable<ServerRequest> {
    private final Client client;

    public ReadTask(Client client) {
        this.client = client;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public ServerRequest call() throws Exception {
        return client.read();
    }
}

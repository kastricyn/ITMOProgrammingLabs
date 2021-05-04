package ru.ifmo.se.kastricyn.lab6.lib.connection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerRequest implements Serializable {
    public String getCommandName() {
        return commandName;
    }

    private String commandName;
    private ArrayList<Object> params;

    public ServerRequest() {
    }

    public ServerRequest(String commandName) {
        this.commandName = commandName;
        params = new ArrayList<>();
    }

    public ServerRequest addParam(Object... objects) {
        params.add(objects);
        return this;
    }

    public ServerRequest clear(){
        params.clear();
        return this;
    }

    @Override
    public String toString() {
        return "ServerRequest{" +
                "commandName='" + commandName + '\'' +
                ", params=" + params +
                '}';
    }


}

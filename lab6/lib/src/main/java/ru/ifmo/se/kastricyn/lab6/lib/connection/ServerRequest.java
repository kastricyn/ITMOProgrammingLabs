package ru.ifmo.se.kastricyn.lab6.lib.connection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerRequest implements Serializable {
    private String commandName = "";

    private ArrayList<Object> params;

    //for JAXB
    public ServerRequest() {
    }

    public ServerRequest(String commandName) {
        this.commandName = commandName;
        params = new ArrayList<>();
    }

    public ServerRequest addParams(Object... objects) {
        params.addAll(Arrays.asList(objects));
        return this;
    }

    public ServerRequest clearParams() {
        params.clear();
        return this;
    }

    public String getCommandName() {
        return commandName;
    }

    @Override
    public String toString() {
        return "ServerRequest{" +
                "commandName='" + commandName + '\'' +
                ", params=" + params +
                '}';
    }


}

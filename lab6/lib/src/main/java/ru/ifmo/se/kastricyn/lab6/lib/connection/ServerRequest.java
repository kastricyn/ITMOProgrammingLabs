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
    private String input = "";
    private String stringArgs;
    private ArrayList<Object> objectsArgs;

    //for JAXB
    public ServerRequest() {
    }

    public ServerRequest(String commandName) {
        this.input = commandName;
        objectsArgs = new ArrayList<>();
    }

    public ServerRequest addParams(Object... objects) {
        this.objectsArgs.addAll(Arrays.asList(objects));
        return this;
    }

    public ArrayList<Object> getObjectsArgs() {
        return objectsArgs;
    }

    public ServerRequest clearParams() {
        objectsArgs.clear();
        return this;
    }

    public String getInput() {
        return input;
    }

    @Override
    public String toString() {
        return "ServerRequest{" +
                "input='" + input + '\'' +
                ", params=" + objectsArgs +
                '}';
    }


}

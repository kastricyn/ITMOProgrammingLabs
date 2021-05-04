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
    private String commandName;
    private ArrayList<Class> paramTypes;
    private ArrayList<Object> params;

    public ServerRequest() {
    }

    public ServerRequest(String commandName) {
        this.commandName = commandName;
        paramTypes = new ArrayList<>();
        params = new ArrayList<>();
    }

    public ServerRequest addParam(Object... objects) {
        for (Object o :
                objects) {
            paramTypes.add(o.getClass());
            params.add(objects);
        }
        return this;
    }

    @Override
    public String toString() {
        return "ServerRequest{" +
                "commandName='" + commandName + '\'' +
                ", paramTypes=" + paramTypes +
                ", params=" + params +
                '}';
    }


}

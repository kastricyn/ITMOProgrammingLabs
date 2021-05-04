package ru.ifmo.se.kastricyn.lab6.lib.connection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerAnswer{
    private String commandName;
    private ArrayList<Class> paramTypes;

    /**
     * for JAXB
     */
    public ServerAnswer(){}
    public ServerAnswer(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public ArrayList<Class> getParamTypes() {
        return paramTypes;
    }

    public ServerAnswer setParamTypes(ArrayList<Class> paramTypes) {
        this.paramTypes = paramTypes;
        return this;
    }

    @Override
    public String toString() {
        return "ServerAnswer{" +
                "commandName='" + commandName + '\'' +
                '}';
    }
}

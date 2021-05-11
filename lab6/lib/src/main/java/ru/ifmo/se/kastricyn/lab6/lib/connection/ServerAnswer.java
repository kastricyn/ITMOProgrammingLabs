package ru.ifmo.se.kastricyn.lab6.lib.connection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerAnswer {
    private ServerAnswerType sat;
    private String answer;
    private String commandName;
    private ArrayList<Class> paramTypes;

    /**
     * for JAXB
     */
    public ServerAnswer() {
    }

    public ServerAnswer(String commandName, ServerAnswerType sat) {
        this.commandName = commandName;
        this.sat = sat;
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

    public ServerAnswerType getSat() {
        return sat;
    }

    public String getAnswer() {
        return answer;
    }

    public ServerAnswer setAnswer(String answer) {
        this.answer = answer;
        return this;
    }
}

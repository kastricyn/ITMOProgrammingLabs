package ru.ifmo.se.kastricyn.lab6.lib.connection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerAnswer {
    private ServerAnswerType sat;
    private String answer;
    private String input;
    private Set<Class> argTypes;

    public ServerAnswer(ServerAnswerType sat) {
        this.sat = sat;
    }

    /**
     * for JAXB
     */
    public ServerAnswer() {
    }

    public ServerAnswer(String commandName, ServerAnswerType sat) {
        this.input = commandName;
        this.sat = sat;
    }

    public Set<Class> getArgTypes() {
        return argTypes;
    }

    public ServerAnswer setArgTypes(Set<Class> argTypes) {
        this.argTypes = argTypes;
        return this;
    }

    public String getInput() {
        return input;
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

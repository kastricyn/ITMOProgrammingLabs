package ru.ifmo.se.kastricyn.lab7.lib.connection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerAnswer implements Serializable {
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

    public ServerAnswer setInput(String input) {
        this.input = input;
        return this;
    }

    public ServerAnswerType getSat() {
        return sat;
    }

    public ServerAnswer setSat(ServerAnswerType sat) {
        this.sat = sat;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public ServerAnswer setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    @Override
    public String toString() {
        return "ServerAnswer{" +
                "sat=" + sat +
                ", answer='" + answer + '\'' +
                ", input='" + input + '\'' +
                ", argTypes=" + argTypes +
                '}';
    }
}

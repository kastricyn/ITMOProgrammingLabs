package ru.ifmo.se.kastricyn.lab8.lib.connection;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Set;

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

    public @NotNull ServerAnswer setArgTypes(Set<Class> argTypes) {
        this.argTypes = argTypes;

        return this;
    }

    public String getInput() {
        return input;
    }

    public @NotNull ServerAnswer setInput(String input) {
        this.input = input;
        return this;
    }

    public ServerAnswerType getSat() {
        return sat;
    }

    public @NotNull ServerAnswer setSat(ServerAnswerType sat) {
        this.sat = sat;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public @NotNull ServerAnswer setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    @Override
    public @NotNull String toString() {
        return "ServerAnswer{" +
                "sat=" + sat +
                ", answer='" + answer + '\'' +
                ", input='" + input + '\'' +
                ", argTypes=" + argTypes +
                '}';
    }
}

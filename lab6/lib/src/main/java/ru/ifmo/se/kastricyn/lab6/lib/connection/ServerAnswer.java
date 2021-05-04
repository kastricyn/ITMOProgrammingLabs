package ru.ifmo.se.kastricyn.lab6.lib.connection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerAnswer{
    private String a;

    public ServerAnswer(){}
    public ServerAnswer(String answ) {
        a = answ;
    }

    public String getA() {
        return a;
    }

    @Override
    public String toString() {
        return "ServerAnswer{" +
                "a='" + a + '\'' +
                '}';
    }
}

package ru.ifmo.se.kastricyn.lab6.lib.connection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerRequest implements Serializable {
    private String a;
    public ServerRequest(){}
    public ServerRequest(String answ) {
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

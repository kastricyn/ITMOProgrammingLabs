package ru.ifmo.se.kastricyn.lab6.lib.connection;

import ru.ifmo.se.kastricyn.lab6.lib.CommandArgument;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerRequest implements Serializable {
    private String input = "";
    private CommandArgument objArgs = new CommandArgument();

    //for JAXB
    public ServerRequest() {
    }

    public ServerRequest(String commandName) {
        this.input = commandName;
    }

    @Override
    public String toString() {
        return "ServerRequest{" +
                "input='" + input + '\'' +
                ", objArgs=" + objArgs +
                '}';
    }

    public CommandArgument getObjArgs() {
        return objArgs;
    }

    /**
     * устанавливает нестроковые аргументы
     *
     * @throws NullPointerException если cca=null
     */
    public ServerRequest setObjArgs(CommandArgument cca) {
        if (cca == null)
            throw new NullPointerException();
        objArgs = cca;
        return this;
    }

    public ServerRequest clearArgs() {
        objArgs = new CommandArgument();
        return this;
    }

    public String getInput() {
        return input;
    }

    public ServerRequest setInput(String input) {
        this.input = input;
        return this;
    }

}

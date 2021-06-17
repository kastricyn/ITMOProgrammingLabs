package ru.ifmo.se.kastricyn.lab7.lib.connection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerRequest implements Serializable {
    private String input = "";
    private @Nullable CommandArgument objArgs = new CommandArgument();
    private ServerRequestType type;

    //for JAXB
    public ServerRequest() {
    }

    public ServerRequest(ServerRequest sr) {
        this.input = sr.input;
        this.objArgs = sr.objArgs;
        this.type = sr.type;
    }

    public ServerRequest(ServerRequestType type) {
        this.type = type;
    }

    public ServerRequest(String commandName) {
        this.input = commandName;
    }

    public ServerRequestType getType() {
        return type;
    }

    public ServerRequest setType(ServerRequestType type) {
        this.type = type;
        return this;
    }

    @Override
    public @NotNull String toString() {
        return "ServerRequest{" +
                "input='" + input + '\'' +
                ", objArgs=" + objArgs +
                '}';
    }

    public @Nullable CommandArgument getObjArgs() {
        return objArgs;
    }

    /**
     * устанавливает нестроковые аргументы
     *
     * @throws NullPointerException если cca=null
     */
    public @NotNull ServerRequest setObjArgs(@Nullable CommandArgument cca) {
        if (cca == null)
            throw new NullPointerException();
        objArgs = cca;
        return this;
    }

    public @NotNull ServerRequest clearArgs() {
        objArgs = new CommandArgument();
        return this;
    }

    public String getInput() {
        return input;
    }

    public @NotNull ServerRequest setInput(String input) {
        this.input = input;
        return this;
    }

}

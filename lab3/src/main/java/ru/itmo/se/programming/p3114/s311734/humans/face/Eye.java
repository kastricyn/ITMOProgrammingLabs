package ru.itmo.se.programming.p3114.s311734.humans.face;

import ru.itmo.se.programming.p3114.s311734.Side;
import ru.itmo.se.programming.p3114.s311734.Size;

public class Eye {
    private Size size = Size.NORMAL;
    private Side side;
    private Object similar;
    Eye(){}
    Eye(Size size, Side side, Object similar){
        this.size = size;
        this.side = side;
        this.similar = similar;
    }

}

package moves;

import ru.ifmo.se.pokemon.*;

public class IronDefense extends StatusMove {
    public IronDefense() {
        super(Type.STEEL, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        p.addEffect(new Effect().stat(Stat.DEFENSE, +2));
    }

    @Override
    protected String describe() {
        return "повышает свою Защиту на две ступени";
    }
}

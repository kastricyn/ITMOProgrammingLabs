package moves;

import ru.ifmo.se.pokemon.*;

public class OminousWind extends SpecialMove {
    public OminousWind() {
        super(Type.GHOST, 60, 100);
    }

    @Override
    protected boolean checkAccuracy(Pokemon att, Pokemon def) {
        return true;
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().chance(0.1);
        e.stat(Stat.ATTACK, +1);
        e.stat(Stat.DEFENSE, +1);
        e.stat(Stat.SPECIAL_ATTACK, +1);
        e.stat(Stat.SPECIAL_DEFENSE, +1);
        e.stat(Stat.SPEED, +1);
        p.addEffect(e);
    }

    @Override
    protected String describe() {
        return "СИЛЬНО ДУЕТ";
    }
}

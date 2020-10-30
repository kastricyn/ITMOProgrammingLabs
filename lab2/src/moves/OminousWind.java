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
        for (Stat currentStat : Stat.values())
            e.stat(currentStat, +1);
        p.addEffect(e);
    }

    @Override
    protected String describe() {
        return "СИЛЬНО ДУЕТ";
    }

}

package pokemons;

import moves.Astonish;
import moves.OminousWind;
import moves.ShadowBall;
import moves.ThunderWave;
import ru.ifmo.se.pokemon.*;

public class Rotom extends Pokemon {
    private void setParametrs() {
        setType(Type.ELECTRIC, Type.GHOST);
        setStats(50, 50, 77, 95, 77, 91);
        setMove(new ThunderWave(), new OminousWind(), new ShadowBall(), new Astonish());
    }

    public Rotom(String name, int level) {
        super(name, level);
        setParametrs();
    }

    public Rotom() {
        super();
        setParametrs();
    }
}

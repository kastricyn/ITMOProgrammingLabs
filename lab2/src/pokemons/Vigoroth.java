package pokemons;

import moves.DoubleTeam;
import moves.FurySwipes;
import moves.Thunder;
import ru.ifmo.se.pokemon.*;

public class Vigoroth extends Pokemon {
    private void setParametrs() {
        setType(Type.NORMAL);
        setStats(80, 80, 80, 55, 55, 90);
        setMove(new DoubleTeam(), new Thunder(), new FurySwipes());
    }

    public Vigoroth(String name, int level) {
        super(name, level);
        setParametrs();
    }

    public Vigoroth() {
        super();
        setParametrs();
    }
}

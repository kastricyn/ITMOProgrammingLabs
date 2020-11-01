package pokemons;

import moves.DoubleTeam;
import moves.Thunder;
import ru.ifmo.se.pokemon.*;

public class Slakoth extends Pokemon {
    protected void setParametrs() {
        setType(Type.NORMAL);
        setStats(60, 60, 60, 35, 35, 30);
        setMove(new DoubleTeam(), new Thunder());
    }

    public Slakoth(String name, int level) {
        super(name, level);
        setParametrs();
    }

}

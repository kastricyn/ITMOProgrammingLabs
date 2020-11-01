package pokemons;

import moves.DoubleTeam;
import moves.FurySwipes;
import moves.IceBeam;
import moves.Thunder;
import ru.ifmo.se.pokemon.*;

public class Slaking extends Vigoroth {
    @Override
    protected void setParametrs() {
        setType(Type.NORMAL);
        setStats(150, 160, 100, 95, 65, 100);
        setMove(new DoubleTeam(), new Thunder(), new FurySwipes(), new IceBeam());
    }

    public Slaking(String name, int level) {
        super(name, level);
    }

}

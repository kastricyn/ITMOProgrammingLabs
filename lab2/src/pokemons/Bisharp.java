package pokemons;

import moves.FocusBlast;
import moves.IronDefense;
import moves.Leer;
import moves.MetalSound;
import ru.ifmo.se.pokemon.*;

public class Bisharp extends Pawniard {

    @Override
    protected void setParametrs() {
        setType(Type.DARK, Type.STEEL);
        setStats(65, 125, 100, 60, 70, 70);
        setMove(new IronDefense(), new Leer(), new MetalSound(), new FocusBlast());
    }

    public Bisharp(String name, int level) {
        super(name, level);
    }
}

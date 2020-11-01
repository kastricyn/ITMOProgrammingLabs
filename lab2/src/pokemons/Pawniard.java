package pokemons;

import moves.IronDefense;
import moves.Leer;
import moves.MetalSound;
import ru.ifmo.se.pokemon.*;

public class Pawniard extends Pokemon {
    private void setParametrs() {
        setType(Type.DARK, Type.STEEL);
        setStats(45, 85, 70, 40, 40, 60);
        setMove(new IronDefense(), new Leer(), new MetalSound());
    }

    public Pawniard(String name, int level) {
        super(name, level);
        setParametrs();
    }

    public Pawniard() {
        super();
        setParametrs();
    }
}

package moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class Astonish extends PhysicalMove {
    public Astonish(){
        super(Type.GHOST, 30, 100);
    }


    protected String describe() {
        return "кричит страшным голосом";
    }
}

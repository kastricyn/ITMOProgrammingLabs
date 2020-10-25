import pokemons.Rotom;
import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Battle b = new Battle();
        Pokemon p1 = new Pokemon("Чужой", 1);
        Pokemon r = new Rotom();
        Pokemon p2 = new Pokemon("Хищник", 1);
        b.addFoe(r);
//        b.addAlly(p1);
        b.addAlly(r);
        b.go();
    }
}

import pokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class BattleSimulation {

    public static void main(String[] args) {
        Battle b = new Battle();
        b.addFoe(new Bisharp("Бишарп1", 45+1));
        b.addFoe(new Pawniard("Паун1",45+1));
        b.addFoe(new Rotom("Ротом1", 25+1));

        b.addAlly(new Slaking("Слакинг2", 1));
        b.addAlly(new Slakoth("Слякоть2",1));
        b.addAlly(new Vigoroth("Виджорот2", 14+1));

        b.go();
    }
}

mkdir lib 2>/dev/null
echo -e "class-path: lib/Pokemon.jar\nmain-class: BattleSimulation" > .mf
javac -encoding UTF-8 -cp lib/* -d bin src/*.java src/moves/*.java src/pokemons/*.java
jar -cmf .mf PokemonBattle.jar -C bin .
rm -rf bin
rm .mf

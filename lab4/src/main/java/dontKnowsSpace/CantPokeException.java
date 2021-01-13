package dontKnowsSpace.exсeptions;

public class CantPokeException extends Exception{
    private Reason reason;

    public CantPokeException(Reason reason){
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "Тычёк невозможен, по причине: " + reason;
    }

    public enum Reason {
        STRONG_OPPONENT("соперник слишком сильный"),
        BAD_MOOD_OPPONENT("у соперника плохой настроение"),
        TODAY_LAZY("сегодня тыкать лениво, тыкнет завтра, может быть");

        String str;

        Reason(String str){this.str = str;}

        @Override
        public String toString() {
            return str;
        }
    }
}

package exceptions;

import java.util.Objects;

public class CantPokeException extends Exception {
    private Reason reason;

    public CantPokeException(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "Тычёк невозможен, по причине: " + reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CantPokeException)) return false;
        CantPokeException that = (CantPokeException) o;
        return getReason() == that.getReason();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReason());
    }

    public enum Reason {
        STRONG_OPPONENT("соперник слишком сильный"),
        BAD_MOOD_OPPONENT("у соперника плохое настроение"),
        TODAY_LAZY("сегодня тыкать лениво, тыкнет завтра, может быть");

        String str;

        Reason(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }
}

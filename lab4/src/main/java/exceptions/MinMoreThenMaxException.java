package exceptions;

public class MinMoreThenMaxException extends RuntimeException {
    private int min, max;

    public MinMoreThenMaxException(int min, int max){
        this.min = min;
        this.max= max;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "Возникла ошибка типа min > max.\n" +
                "Проверьте правильность использования констант. \n" +
                "До исправления полноценность работы программы не гарантируется. " +
                "(min=" + min +
                ", max=" + max +
                ')';
    }
}

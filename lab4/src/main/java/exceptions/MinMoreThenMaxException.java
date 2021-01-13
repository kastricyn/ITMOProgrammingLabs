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
}

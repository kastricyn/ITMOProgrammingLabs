package ru.ifmo.se.kastricyn.lab7.lib.utility;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Иммитирует консоль для работы с пользователем.
 * Предоставляет удобный интерфейс получение данных от пользвателя и реализует его
 */
public class Console {
    private Scanner in = new Scanner(System.in);
    private boolean interactiveMode = true;
    private PrintStream out = System.out;
    private PrintStream err = System.err;

    public Console() {
    }

    /**
     * @param in сканер, откуда стоит читать
     *           По умолчанию <code>shouldPrintHints = true</code> (подсказки будут печататься)
     */
    public Console(Scanner in) {
        this.in = in;
    }

    /**
     * Конструктор Console
     *
     * @param in               сканер, откуда стоит читать
     * @param shouldPrintHints надо ли печатать подсказки в вывод
     */
    public Console(Scanner in, boolean shouldPrintHints) {
        this.in = in;
        this.interactiveMode = shouldPrintHints;
    }

    public Console(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public Console(Scanner in, PrintStream out, PrintStream err) {
        this.in = in;
        this.out = out;
        this.err = err;
    }

    public static @NotNull String getStringFromStream(String before, @NotNull Stream<?> stream) {
        StringBuffer sb = new StringBuffer(1024).append(before).append("\n");
        stream.forEachOrdered(x -> sb.append(x).append("\n"));
        return sb.toString();
    }

    /**
     * Метод получает строку от пользователя необходимого формата
     *
     * @param possibleNull  может ли строка быть null
     * @param possibleEmpty может ли быть пустой
     * @return строку полученную от пользователя необходимого формата
     * @throws NoSuchElementException если пользователь завершил ввод
     */
    public @Nullable String getString(boolean possibleNull, boolean possibleEmpty) throws NoSuchElementException {
        while (true) {
            if (interactiveMode) {
                printHints("Введите строку");
                printHints(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
            }
            String t = nextLine().trim();
            if (t.isEmpty() && possibleEmpty)
                return t;
            if (t.isEmpty() && possibleNull)
                return null;
            if (!t.isEmpty())
                return t;
        }
    }

    /**
     * Метод получает строку от пользователя необходимого формата
     *
     * @param possibleNull может ли строка быть null
     * @return непустую строку или null
     */
    public @Nullable String getString(boolean possibleNull) {
        return getString(possibleNull, false);
    }

    /**
     * Метод получает строку от пользователя необходимого формата
     *
     * @return непустую строку, не null
     */
    public @Nullable String getString() {
        return getString(false, false);
    }

    /**
     * Чтобы получить null пользователю необходимо подать на <code>Scanner</code> пустую строку
     *
     * @param min          минимальное допустимое значение
     * @param max          максимальное допустимое значение
     * @param possibleNull можно ли вернуть null
     * @return null <code>null||</code> объект типа Long
     * @throws NoSuchElementException если пользователь завершил ввод
     */
    public @Nullable Long getLong(long min, long max, boolean possibleNull) throws NoSuchElementException {
        while (true) {
            if (interactiveMode) {
                printHints("Введите целое число в границах [" + min + "; " + max + "]");
                printHints(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
            }
            String t = nextLine().trim();
            if (t.isEmpty() && possibleNull)
                return null;
            try {
                long l = Long.parseLong(t);
                if (l < min || l > max)
                    printHints("Введённые данные не корректны");
                else return l;
            } catch (NumberFormatException e) {
                printHints("Введённые данные не корректны");
            }
        }
    }

    /**
     * Чтобы получить null пользователю необходимо подать на <code>Scanner</code> пустую строку
     *
     * @param possibleNull можно ли вернуть null
     * @return null <code>null||</code> объект типа Long
     */
    public @Nullable Long getLong(boolean possibleNull) {
        return getLong(Long.MIN_VALUE, Long.MAX_VALUE, possibleNull);
    }

    /**
     * @param min минимальное допустимое значение
     * @param max максимальное допустимое значение
     * @return примитив типа long
     */
    public long getLong(long min, long max) {
        return getLong(min, max, false);
    }

    /**
     * @param min минимальное допустимое значение
     * @return примитив типа long
     */
    public long getLong(long min) {
        return getLong(min, Long.MAX_VALUE, false);
    }

    /**
     * @return примитив типа long
     */
    public long getLong() {
        return getLong(Long.MIN_VALUE, Long.MAX_VALUE, false);
    }


    /**
     * Чтобы получить null пользователю необходимо подать на <code>Scanner</code> пустую строку
     *
     * @param min          минимальное допустимое значение
     * @param max          максимальное допустимое значение
     * @param possibleNull можно ли вернуть null
     * @return null <code>null||</code> объект типа Integer
     * @throws NoSuchElementException если пользователь завершил ввод
     */
    public @Nullable Integer getInt(int min, int max, boolean possibleNull) throws NoSuchElementException {
        while (true) {
            if (interactiveMode) {
                printHints("Введите целое число в границах [" + min + "; " + max + "]");
                printHints(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
            }
            String t = nextLine().trim();
            if (t.isEmpty() && possibleNull)
                return null;
            try {
                int l = Integer.parseInt(t);
                if (l < min || l > max)
                    printHints("Введённые данные не корректны");
                else return l;
            } catch (NumberFormatException e) {
                printHints("Введённые данные не корректны");
            }
        }
    }

    /**
     * Чтобы получить null пользователю необходимо подать на <code>Scanner</code> пустую строку
     *
     * @param possibleNull можно ли вернуть null
     * @return null <code>null||</code> объект типа Integer
     */
    public @Nullable Integer getInt(boolean possibleNull) {
        return getInt(Integer.MIN_VALUE, Integer.MAX_VALUE, possibleNull);
    }

    /**
     * @param min минимальное допустимое значение
     * @param max максимальное допустимое значение
     * @return примитив типа int
     */
    public int getInt(int min, int max) {
        return getInt(min, max, false);
    }

    /**
     * @param min минимальное допустимое значение
     * @return примитив типа int
     */
    public int getInt(int min) {
        return getInt(min, Integer.MAX_VALUE, false);
    }

    /**
     * @return примитив типа int
     */
    public int getInt() {
        return getInt(Integer.MIN_VALUE, Integer.MAX_VALUE, false);
    }


    /**
     * Чтобы получить null пользователю необходимо подать на <code>Scanner</code> пустую строку
     *
     * @param min          минимальное допустимое значение
     * @param max          максимальное допустимое значение
     * @param possibleNull можно ли вернуть null
     * @return null <code>null||</code> объект типа Float
     * @throws NoSuchElementException если пользователь завершил ввод
     */
    public @Nullable Float getFloat(float min, float max, boolean possibleNull) throws NoSuchElementException {
        while (true) {
            if (interactiveMode) {
                printHints("Введите число с плавающей точкой в границах [" + min + "; " + max + "]");
                printHints(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
            }
            String t = nextLine().trim();
            if (t.isEmpty() && possibleNull)
                return null;
            try {
                float f = Float.parseFloat(t);
                if (f < min || f > max)
                    printHints("Введённые данные не корректны");
                else return f;
            } catch (NumberFormatException e) {
                printHints("Введённые данные не корректны");
            }
        }
    }

    /**
     * @return примитив типа float
     */
    public float getFloat() {
        return getFloat(Float.MIN_VALUE, Float.MAX_VALUE, false);
    }


    /**
     * Получает от пользователя число в заданном диапазоне
     * Чтобы получить null пользователю необходимо подать на <code>Scanner</code> пустую строку
     *
     * @param min          минимальное допустимое значение
     * @param max          максимальное допустимое значение
     * @param possibleNull можно ли вернуть null
     * @return null <code>null||</code> объект типа Double
     * @throws NoSuchElementException если пользователь завершил ввод
     */
    public @Nullable Double getDouble(double min, double max, boolean possibleNull) throws NoSuchElementException {
        while (true) {
            if (interactiveMode) {
                printHints("Введите число с плавающей точкой в границах [" + min + "; " + max + "]");
                printHints(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
            }
            String t = nextLine().trim();
            if (t.isEmpty() && possibleNull)
                return null;
            try {
                double f = Double.parseDouble(t);
                if (f < min || f > max)
                    printHints("Введённые данные не корректны");
                else return f;
            } catch (NumberFormatException e) {
                printHints("Введённые данные не корректны");
            }
        }

    }

    /**
     * Получает от пользователя число в заданном диапазоне
     *
     * @return примитив типа double
     */
    public double getDouble(double min, double max) {
        return getDouble(min, max, false);
    }

    /**
     * Получает от пользователя объект, из переданного Enum
     *
     * @throws NoSuchElementException если пользователь завершил ввод
     */
    public <T extends Enum<T>> @Nullable T getEnumConstant(@NotNull Class<T> eClass, boolean possibleNull) throws NoSuchElementException {
        if (interactiveMode) {
            String str = Arrays.toString(eClass.getEnumConstants());
            str = str.substring(1, str.length() - 1);
            printHints("Введите одно из: " + str);
            printHints(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
        }
        while (true) {
            String t = nextLine().trim().toUpperCase();
            if (t.isEmpty() && possibleNull)
                return null;
            try {
                return Enum.valueOf(eClass, t);
            } catch (RuntimeException e) {
                printHints("Данные не корректны");
            }
        }
    }

    /**
     * Запрашивает у пользователя подтверждение на вполнение действия
     *
     * @param message сообщение о действии, которое необходимо подтвердить
     * @return true, если пользователь подтверждает своё желание, иначе false
     * @throws NoSuchElementException если пользователь завершил ввод
     */
    public boolean requestConfirmation(String message) throws NoSuchElementException {
        printHints(message);
        printHints("Для подтверждения введите y, для отмены любую другую клавишу");
        String t = "";
        t = nextLine().trim().toUpperCase();

        return !t.isEmpty() && t.charAt(0) == 'Y';
    }

    /**
     * Проверяет в интерактивном ли режиме
     */
    public boolean isInteractiveMode() {
        return interactiveMode;
    }

    /**
     * @return Сканер, откуда считываются данные
     */
    public Scanner getIn() {
        return in;
    }

    /**
     * Вызывает hasNext() у сканера
     * Аналогично getIn().hasNext()
     */
    public boolean hasNext() {
        return in.hasNext();
    }

    /**
     * @return getIn().nextLine()
     */
    public String nextLine() {
        return in.nextLine();
    }

    public void printlnErr(String str) {
        err.println("Error: " + str);
    }

    /**
     * Печатает в коносль, если <code>isInteractiveMode()</code>
     *
     * @param str строка которую надо напечатать
     */
    public void printHints(String str) {
        if (isInteractiveMode()) out.println(str);
    }

    /**
     * печатает в консоль
     *
     * @param str строка которую печатает
     */
    public void println(String str) {
        out.println(str);
    }
}

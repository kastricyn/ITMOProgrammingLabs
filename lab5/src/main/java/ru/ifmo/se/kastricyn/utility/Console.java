package ru.ifmo.se.kastricyn.utility;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {
    private Scanner in;
    private boolean interactiveMode;


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

    /**
     * Конструктор Console
     *
     * @param in сканер, откуда стоит читать
     *           По умолчанию <code>shouldPrintHints = true</code> (подсказки будут печататься)
     */
    public Console(Scanner in) {
        this.in = in;
        interactiveMode = true;
    }

    /**
     * Метод получает строку от пользователя необходимого формата
     *
     * @param possibleNull  может ли строка быть null
     * @param possibleEmpty может ли быть пустой
     * @return строку полученную от пользователя необходимого формата
     */
    public String getString(boolean possibleNull, boolean possibleEmpty) {
        try {
            while (true) {
                if (interactiveMode) {
                    System.out.println("Введите строку");
                    System.out.println(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
                }
                String t = in.nextLine().trim();
                if (t.isEmpty() && possibleEmpty)
                    return t;
                if (t.isEmpty() && possibleNull)
                    return null;
                if (!t.isEmpty())
                    return t;
            }
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
        return null;
    }

    /**
     * Метод получает строку от пользователя необходимого формата
     *
     * @param possibleNull может ли строка быть null
     * @return непустую строку или null
     */
    public String getString(boolean possibleNull) {
        return getString(possibleNull, false);
    }

    /**
     * Метод получает строку от пользователя необходимого формата
     *
     * @return непустую строку, не null
     */
    public String getString() {
        return getString(false, false);
    }

    /**
     * Чтобы получить null пользователю необходимо подать на <code>Scanner</code> пустую строку
     *
     * @param min          минимальное допустимое значение
     * @param max          максимальное допустимое значение
     * @param possibleNull можно ли вернуть null
     * @return null <code>null||</code> объект типа Long
     */
    public Long getLong(long min, long max, boolean possibleNull) {
        try {
            while (true) {
                if (interactiveMode) {
                    System.out.println("Введите целое число в границах [" + min + "; " + max + "]");
                    System.out.println(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
                }
                String t = in.nextLine().trim();
                if (t.isEmpty() && possibleNull)
                    return null;
                try {
                    Long l = Long.parseLong(t);
                    if (l < min || l > max)
                        System.out.println("Введённые данные не корректны");
                    else return l;
                } catch (NumberFormatException e) {
                    System.out.println("Введённые данные не корректны");
                }
            }
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
        return null;
    }

    /**
     * Чтобы получить null пользователю необходимо подать на <code>Scanner</code> пустую строку
     *
     * @param possibleNull можно ли вернуть null
     * @return null <code>null||</code> объект типа Long
     */
    public Long getLong(boolean possibleNull) {
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
     */
    public Integer getInt(int min, int max, boolean possibleNull) {
        try {
            while (true) {
                if (interactiveMode) {
                    System.out.println("Введите целое число в границах [" + min + "; " + max + "]");
                    System.out.println(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
                }
                String t = in.nextLine().trim();
                if (t.isEmpty() && possibleNull)
                    return null;
                try {
                    Integer l = Integer.parseInt(t);
                    if (l < min || l > max)
                        System.out.println("Введённые данные не корректны");
                    else return l;
                } catch (NumberFormatException e) {
                    System.out.println("Введённые данные не корректны");
                }
            }
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
        return null;
    }

    /**
     * Чтобы получить null пользователю необходимо подать на <code>Scanner</code> пустую строку
     *
     * @param possibleNull можно ли вернуть null
     * @return null <code>null||</code> объект типа Integer
     */
    public Integer getInt(boolean possibleNull) {
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
     */
    public Float getFloat(float min, float max, boolean possibleNull) {
        try {
            while (true) {
                if (interactiveMode) {
                    System.out.println("Введите число с плавающей точкой в границах [" + min + "; " + max + "]");
                    System.out.println(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
                }
                String t = in.nextLine().trim();
                if (t.isEmpty() && possibleNull)
                    return null;
                try {
                    Float f = Float.parseFloat(t);
                    if (f < min || f > max)
                        System.out.println("Введённые данные не корректны");
                    else return f;
                } catch (NumberFormatException e) {
                    System.out.println("Введённые данные не корректны");
                }
            }
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
        return null;

    }

    /**
     * @return примитив типа float
     */
    public float getFloat() {
        return getFloat(Float.MIN_VALUE, Float.MAX_VALUE, false);
    }


    /**
     * Чтобы получить null пользователю необходимо подать на <code>Scanner</code> пустую строку
     *
     * @param min          минимальное допустимое значение
     * @param max          максимальное допустимое значение
     * @param possibleNull можно ли вернуть null
     * @return null <code>null||</code> объект типа Double
     */
    public Double getDouble(double min, double max, boolean possibleNull) {
        try {
            while (true) {
                if (interactiveMode) {
                    System.out.println("Введите число с плавающей точкой в границах [" + min + "; " + max + "]");
                    System.out.println(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
                }
                String t = in.nextLine().trim();
                if (t.isEmpty() && possibleNull)
                    return null;
                try {
                    Double f = Double.parseDouble(t);
                    if (f < min || f > max)
                        System.out.println("Введённые данные не корректны");
                    else return f;
                } catch (NumberFormatException e) {
                    System.out.println("Введённые данные не корректны");
                }
            }
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
        return null;

    }

    /**
     * @return примитив типа double
     */
    public double getDouble(double min, double max) {
        return getDouble(min, max, false);
    }

    public <T extends Enum<T>> T getEnumConstant(Class<T> eClass, boolean possibleNull) {
        if (interactiveMode) {
            String str = Arrays.toString(eClass.getEnumConstants());
            str = str.substring(1, str.length() - 1);
            System.out.println("Введите одно из: " + str);
            System.out.println(possibleNull ? "Для получения null введите пустую строку" : "Получить null нельзя.");
        }
        try {
            while (true) {
                String t = in.nextLine().trim().toUpperCase();
                if (t.isEmpty() && possibleNull)
                    return null;
                try {
                    T answ = Enum.valueOf(eClass, t);
                    return answ;
                } catch (RuntimeException e) {
                    System.out.println("Данные не корректны");
                }
            }
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
        return null;
    }


    public boolean requestConfirmation(String message) {
        System.out.println(message);
        System.out.println("Для подтверждения введите y, для отмены любую другую клавишу");
        String t = "";
        try {
            t = in.nextLine().trim().toUpperCase();
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
        return !t.isEmpty() && t.charAt(0) == 'Y';
    }

    public boolean isInteractiveMode() {
        return interactiveMode;
    }

    public Scanner getIn() {
        return in;
    }

    public boolean hasNext() {
        return in.hasNext();
    }

    public String nextLine() {
        return in.nextLine();
    }
}

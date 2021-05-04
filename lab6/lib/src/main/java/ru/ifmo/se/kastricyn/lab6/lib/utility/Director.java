package ru.ifmo.se.kastricyn.lab6.lib.utility;

import ru.ifmo.se.kastricyn.lab6.lib.Command;

import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Иммитирует консоль для работы с пользователем.
 * Предоставляет удобный интерфейс получение данных от пользвателя и реализует его
 */
public class Director {
    private Scanner in;
    private boolean interactiveMode;

    /**
     * Конструктор Console
     *
     * @param in               сканер, откуда стоит читать
     * @param shouldPrintHints надо ли печатать подсказки в вывод
     */
    public Director(Scanner in, boolean shouldPrintHints) {
        this.in = in;
        this.interactiveMode = shouldPrintHints;
    }

    /**
     * @param in сканер, откуда стоит читать
     *           По умолчанию <code>shouldPrintHints = true</code> (подсказки будут печататься)
     */
    public Director(Scanner in) {
        this.in = in;
        interactiveMode = true;
    }

    /**
     * Устанавливает параметры для переданной команды
     * @param command команда которой надо установить параметры
     * @return true если установлено иначе false
     */
    public boolean setParams(Command command){
        ArrayList<Object> params = new ArrayList<>(command.getParamTypes().size());
        //todo get Params в зависимости от консоли и команды или чего-то ещё
        command.setParams(params);
        return true;
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
                String t = nextLine().trim();
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
                String t = nextLine().trim();
                if (t.isEmpty() && possibleNull)
                    return null;
                try {
                    long l = Long.parseLong(t);
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
                String t = nextLine().trim();
                if (t.isEmpty() && possibleNull)
                    return null;
                try {
                    int l = Integer.parseInt(t);
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
                String t = nextLine().trim();
                if (t.isEmpty() && possibleNull)
                    return null;
                try {
                    float f = Float.parseFloat(t);
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
     * Получает от пользователя число в заданном диапазоне
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
                String t = nextLine().trim();
                if (t.isEmpty() && possibleNull)
                    return null;
                try {
                    double f = Double.parseDouble(t);
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
     * Получает от пользователя число в заданном диапазоне
     *
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
                String t = nextLine().trim().toUpperCase();
                if (t.isEmpty() && possibleNull)
                    return null;
                try {
                    return Enum.valueOf(eClass, t);
                } catch (RuntimeException e) {
                    System.out.println("Данные не корректны");
                }
            }
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
        return null;
    }

    /**
     * Запрашивает у пользователя подтверждение на вполнение действия
     *
     * @param message сообщение о действии, которое необходимо подтвердить
     * @return true, если пользователь подтверждает своё желание, иначе false
     */
    public boolean requestConfirmation(String message) {
        System.out.println(message);
        System.out.println("Для подтверждения введите y, для отмены любую другую клавишу");
        String t = "";
        try {
            t = nextLine().trim().toUpperCase();
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
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

    public static void printError(String str) {
        System.out.println("Error: " + str);
    }
}

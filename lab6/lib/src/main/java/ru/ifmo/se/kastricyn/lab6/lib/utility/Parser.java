package ru.ifmo.se.kastricyn.lab6.lib.utility;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Сохраняет объект в файл и восстанавливает его из файла
 */
public class Parser {
    /**
     * Восстанавливает объект из файла
     *
     * @param p путь до файла, в котором записан XML коллекции
     * @return коллекцию, сохранённую до этого в XML файле
     * @throws JAXBException         если файл был изменён некорректным образом
     * @throws AccessDeniedException если файл недоступен для чтения
     */
    public static <E> E get(Path p, Class<E> eClass) throws JAXBException, AccessDeniedException {
        if (!Files.isReadable(p))
            throw new AccessDeniedException(p.toString());
        JAXBContext context = JAXBContext.newInstance(eClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        E collection = (E) unmarshaller.unmarshal(p.toFile());
        return collection;
    }
    /**
     * Восстанавливает объект из InputStream
     *
     * @param p путь до файла, в котором записан XML коллекции
     * @return коллекцию, сохранённую до этого в XML файле
     * @throws JAXBException         если файл был изменён некорректным образом
     * @throws AccessDeniedException если файл недоступен для чтения
     */
    public static <E> E get(Reader p, Class<E> eClass) throws JAXBException, AccessDeniedException {
        JAXBContext context = JAXBContext.newInstance(eClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        E collection = (E) unmarshaller.unmarshal(p);
        return collection;
    }



    /**
     * Создаёт объект, записанный в файл
     *
     * @param p путь до файла в который будет сохраняться коллекция
     * @return пустую коллекцию, связанную с файлом, путь до которого был передан
     */
    public static <E> E create(Path p, Class<E> eClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        E object = eClass.getConstructor(Path.class).newInstance(p);
        try {
            Files.createFile(p);
        } catch (IOException e) {
            System.out.println("Создать файл не удалось. Сохранение не доступно.");
        }
        if (Files.isWritable(p)) {
            write(p, eClass, object);
        }
        return object;
    }

    public static <E> void write(Path p, Class<E> eClass, E object) {
        try {
            JAXBContext context = JAXBContext.newInstance(eClass);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, p.toFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
    public static <E> void write(Writer p, Class<E> eClass, E object) {
        try {
            JAXBContext context = JAXBContext.newInstance(eClass);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.marshal(object, p);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        //todo SOLID principe
    }

}

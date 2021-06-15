package ru.ifmo.se.kastricyn.lab7.server.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab7.lib.User;
import ru.ifmo.se.kastricyn.lab7.lib.data.*;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab7.server.Properties;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Locale;

public class DBManager implements DBTicketsI, DBUserI {
    static final Logger log = LogManager.getLogger(DBManager.class);
    private static final String addTicketQuery = "INSERT INTO tickets " + "(ticketname, ticketcoordinatex, " +
            "ticketcoordinatey, " +
            "ticketprice, ticketdiscount, tickettype, venuename, venuecapacity, venuetype, venueaddress, userid, " +
            "ticketcreationdate) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String updateTicketQuery = "UPDATE tickets\n" +
            "SET ticketname        = ?," +
            "    ticketcoordinatex = ?," +
            "    ticketcoordinatey = ?," +
            "    ticketprice       = ?," +
            "    ticketdiscount    = ?," +
            "    tickettype        = ?," +
            "    venuename         = ?," +
            "    venuecapacity     = ?," +
            "    venuetype         = ?," +
            "    venueaddress      = ?" +
            "WHERE id = ?";
    static Properties properties = Properties.getProperties();

    /**
     * Возвращает соединение с БД по параметрам из конфигурационного файла
     */
    protected @Nullable Connection setConnection() {
        try {
            Class.forName(properties.getDBDriver());
            Connection dbConnection = DriverManager.getConnection(properties.getDBUrl(), properties.getDBLogin(), properties.getDBPass());
            log.info("Соединение установленно с: " + properties.getDBUrl());
            return dbConnection;
        } catch (ClassNotFoundException e) {
            log.error("Не найден класс с драйвером БД: " + e);
        } catch (SQLException throwable) {
            log.error(throwable);
        }
        log.warn("Вместо Connection возвращён null");
        return null;
    }

    /**
     * Возвращает коллецию билетов из БД.
     */
    @Override
    public @NotNull TicketCollection getTicketCollection() {
        createTables();
        TicketCollection ticketCollection = new TicketCollection(this);
        int i = 0;
        try (Connection dbConnection = setConnection()) {
            assert dbConnection != null;
            try (Statement stat = dbConnection.createStatement()) {
                String command = "SELECT * FROM tickets";
                ResultSet rs = stat.executeQuery(command);

                while (rs.next()) {
                    try {
                        Ticket t = new Ticket(
                                rs.getLong("id"),
                                rs.getString("ticketname"),
                                new Coordinates(rs.getLong("ticketcoordinatex"), rs.getFloat("ticketcoordinatey")),
                                rs.getDate("ticketcreationdate").toLocalDate(),
                                rs.getInt("ticketprice"),
                                rs.getDouble("ticketdiscount"),
                                TicketType.valueOf(rs.getString("tickettype").toUpperCase(Locale.ROOT)),
                                new Venue(
                                        rs.getLong("id"),
                                        rs.getString("venuename"),
                                        rs.getInt("venuecapacity"),
                                        VenueType.valueOf(rs.getString("venuetype").toUpperCase(Locale.ROOT)),
                                        new Address(rs.getString("venueaddress"))
                                ),
                                rs.getLong("userid")
                        );
                        ticketCollection.add(t);
                    } catch (RuntimeException e) {
                        i++;
                    }
                }
            }
        } catch (SQLException throwables) {
            log.error(throwables);
        }
        if (i > 0)
            log.warn("В коллекции было нарушено " + i + " элементов.");
        if (ticketCollection.check() || i > 0)
            if (new Console().requestConfirmation("Обнаружены ошибки в БД. Хотите пересоздать таблицы? \n Корректные " +
                    "данные будут сохранены (кроме индексации элементов).")) {
                deleteTables();
                createTables();
                for (Ticket t :
                        ticketCollection) {
                    long id = addTicket(t);
                    t.setId(id);
                    t.getVenue().setId(id);
                }
            }
        return ticketCollection;
    }

    /**
     * Создаёт необходимые таблицы, если они не существуют
     */
    @Override
    public void createTables() {
        try (Connection dbConnection = setConnection()) {
            assert dbConnection != null;
            try (Statement stat = dbConnection.createStatement()) {
                String command = "CREATE TABLE IF NOT EXISTS s311734.users\n" +
                        "(\n" +
                        "    Id bigserial PRIMARY KEY,\n" +
                        "    Name varchar NOT NULL UNIQUE CHECK (Name <> ''),\n" +
                        "    Password varchar,\n" +
                        "    PasswordSalt varchar\n" +
                        ");\n" +
                        "\n" +
                        "CREATE INDEX IF NOT EXISTS Name ON s311734.users (Name);\n" +
                        "\n" +
                        "CREATE TABLE IF NOT EXISTS s311734.tickets (\n" +
                        "    Id bigserial PRIMARY KEY,\n" +
                        "    TicketName varchar NOT NULL CHECK (tickets.TicketName <> ''),\n" +
                        "    TicketCoordinateX bigint NOT NULL CHECK(tickets.TicketCoordinateX>-503),\n" +
                        "    TicketCoordinateY real NOT NULL,\n" +
                        "    TicketCreationDate date NOT NULL DEFAULT current_timestamp,\n" +
                        "    TicketPrice integer CHECK(tickets.TicketPrice >0),\n" +
                        "    TicketDiscount decimal NOT NULL CHECK(TicketDiscount>=0 AND TicketDiscount <=100),\n" +
                        "    TicketType varchar CHECK(TicketType in ('usual', 'budgetary', 'cheap')),\n" +
                        "    VenueName varchar NOT NULL CHECK (tickets.VenueName <> ''),\n" +
                        "    VenueCapacity int NOT NULL CHECK(VenueCapacity>0),\n" +
                        "    VenueType varchar NOT NULL CHECK ( VenueType in ('pub', 'loft', 'open_area', 'cinema', 'stadium') ),\n" +
                        "    VenueAddress varchar CHECK (VenueAddress <> ''),\n" +
                        "    UserId bigint NOT NULL,\n" +
                        "    FOREIGN KEY (UserId) References s311734.users (Id) ON DELETE CASCADE\n" +
                        ");";
                stat.executeUpdate(command);
                log.info("Таблицы созданы или уже были");
            }
        } catch (SQLException throwables) {
            // TODO: разобраться с обработкой SQLException
            log.error(throwables);
        }
    }

    /**
     * Удаляет таблицы, если существуют
     * Используетя для создания своих "правильных"
     */
    @Override
    public void deleteTables() {
        try (Connection dbConnection = setConnection()) {
            assert dbConnection != null;
            try (Statement stat = dbConnection.createStatement()) {
                String command = "DROP TABLE IF EXISTS tickets, users CASCADE";
                stat.executeUpdate(command);
                log.info("Таблицы удалены или их не было");
            }
        } catch (SQLException throwables) {
            log.error(throwables);
        }
    }

    /**
     * Добавляет в БД билет
     *
     * @param t билет, который надо добавить
     * @return id под которым был добавлен билет, -1 если билет не был добавлен
     */
    @Override
    public long addTicket(@NotNull Ticket t) {
        try (Connection dbConnection = setConnection()) {
            assert dbConnection != null;
            try (PreparedStatement stat = dbConnection.prepareStatement(addTicketQuery, Statement.RETURN_GENERATED_KEYS)) {
                stat.setString(1, t.getName());
                stat.setLong(2, t.getCoordinates().getX());
                stat.setFloat(3, t.getCoordinates().getY());
                stat.setInt(4, t.getPrice());
                stat.setDouble(5, t.getDiscount());
                stat.setString(6, t.getType() == null ? null : t.getType().name().toLowerCase(Locale.ROOT));
                stat.setString(7, t.getVenue().getName());
                stat.setInt(8, t.getVenue().getCapacity());
                stat.setString(9, t.getVenue().getType().name().toLowerCase(Locale.ROOT));
                stat.setString(10, t.getVenue().getAddress().getStreet() == null ? null : t.getVenue().getAddress().getStreet().toLowerCase(Locale.ROOT));
                stat.setLong(11, t.getUserId());
                stat.setDate(12, Date.valueOf(t.getCreationDate()));
                stat.executeUpdate();
                ResultSet rs = stat.getResultSet();
                if (rs.next())
                    return rs.getLong(1);
            }
        } catch (SQLException throwables) {
            log.error(throwables);
        }
        return -1;
    }

    /**
     * Обновляет в БД билет
     *
     * @param id номер билетв
     * @param t  новый билет
     * @return true если добавлено, иначе false
     */
    @Override
    public boolean updateTicket(long id, @NotNull Ticket t) {
        try (Connection dbConnection = setConnection()) {
            assert dbConnection != null;
            try (PreparedStatement stat = dbConnection.prepareStatement(updateTicketQuery, Statement.RETURN_GENERATED_KEYS)) {
                stat.setString(1, t.getName());
                stat.setLong(2, t.getCoordinates().getX());
                stat.setFloat(3, t.getCoordinates().getY());
                stat.setInt(4, t.getPrice());
                stat.setDouble(5, t.getDiscount());
                stat.setString(6, t.getType() == null ? null : t.getType().name().toLowerCase(Locale.ROOT));
                stat.setString(7, t.getVenue().getName());
                stat.setInt(8, t.getVenue().getCapacity());
                stat.setString(9, t.getVenue().getType().name().toLowerCase(Locale.ROOT));
                stat.setString(10, t.getVenue().getAddress().getStreet() == null ? null : t.getVenue().getAddress().getStreet().toLowerCase(Locale.ROOT));
                stat.setLong(11, id);
                if (stat.executeUpdate() > 0)
                    return true;
            }
        } catch (SQLException throwables) {
            log.error(throwables);
        }
        return false;
    }

    /**
     * Удаляет в БД билет
     *
     * @param id номер билетв
     * @return true если удалено, иначе false
     */
    @Override
    public boolean deleteTicket(long id) {
        try (Connection dbConnection = setConnection()) {
            assert dbConnection != null;
            try (PreparedStatement stat =
                         dbConnection.prepareStatement("DELETE FROM tickets WHERE id=?")) {
                stat.setLong(1, id);
                stat.executeUpdate();
                return true;
            }
        } catch (SQLException throwables) {
            log.error(throwables);
        }
        return false;
    }


    /**
     * Удаляет все элементы пользователя
     *
     * @param userId
     */
    @Override
    public boolean clear(long userId) {
        try (Connection dbConnection = setConnection()) {
            assert dbConnection != null;
            try (PreparedStatement stat =
                         dbConnection.prepareStatement("DELETE FROM tickets WHERE userid=?")) {
                stat.setLong(1, userId);
                stat.executeUpdate();
                return true;
            }
        } catch (SQLException throwables) {
            log.error(throwables);
        }
        return false;
    }

    /**
     * Авторизует пользователя
     * @param user пользователь, которого необходимо авторизовать
     * @return useer(id, name) пользователя из записи БД, если прошёл авторизацию, иначе null
     */
    @Override
    public @Nullable User auth(@NotNull User user) {
        if (checkPassword(user))
            return new User(getId(user), user.getName());
        else return null;
    }

    /**
     * Регистрирует нового пользователя в БД
     *
     * @param user пользователь
     * @return true если добавлено, иначе false
     */
    @Override
    public boolean registerUser(@NotNull User user) {
        try (Connection connection = setConnection()) {
            assert connection != null;
            try (PreparedStatement statement =
                         connection.prepareStatement("INSERT INTO users (name, password, passwordsalt) values (?, ?, " +
                                 "?)")) {
                statement.setString(1, user.getName());
                String passwordsalt = String.valueOf((int) (Math.random() * 100000));
                statement.setString(2, getStringFromPassword(user.getPassword(), passwordsalt));
                statement.setString(3, passwordsalt);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            log.error(e);
        }
        return false;
    }


    /**
     * Проверяет пароль пользователя на совпадение с данными в БД
     *
     * @param user user(name, password)
     * @return true, если пароль верный; иначе false
     */
    @Override
    public boolean checkPassword(@NotNull User user) {
        try (Connection connection = setConnection()) {
            assert connection != null;
            try (PreparedStatement statement =
                         connection.prepareStatement("SELECT password, passwordsalt FROM users WHERE name=?")) {
                statement.setString(1, user.getName());
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    return rs.getString("password").equals(getStringFromPassword(user.getPassword(),
                            rs.getString("passwordsalt")));
                }
            }
        } catch (SQLException | NoSuchAlgorithmException throwables) {
            log.error(throwables);
        }
        return false;
    }

    /**
     * Возвращает id пользователя
     *
     * @param user пользователь
     * @return id пользователя, или -1 если пользователь не найден в базе
     */
    @Override
    public long getId(@NotNull User user) {
        try (Connection connection = setConnection()) {
            assert connection != null;
            try (PreparedStatement statement =
                         connection.prepareStatement("SELECT id FROM users WHERE name=?")) {
                statement.setString(1, user.getName());
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    return rs.getLong("id");
                }
            }
        } catch (SQLException throwables) {
            log.error(throwables);
        }
        return -1;
    }


}

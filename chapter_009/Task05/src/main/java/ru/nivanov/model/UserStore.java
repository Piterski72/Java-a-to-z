package ru.nivanov.model;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Nikolay Ivanov on 25.10.2017.
 */
public class UserStore {

    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);
    private static final UserStore BASE = new UserStore();
    private Map<Integer, User> userMap = new ConcurrentHashMap<>();
    private BasicDataSource pool;

    /**
     * Private constructor.
     */
    private UserStore() {
        this.pool = new BasicDataSource();
        InputStream io = getClass().getResourceAsStream("/db.properties");
        Properties props = new Properties();
        try {
            props.load(io);
            LOG.info("props loaded!");
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        String url = props.getProperty("baseUrl");
        String username = props.getProperty("username");
        String pass = props.getProperty("password");

        this.pool.setDriverClassName(props.getProperty("driverClass"));
        this.pool.setUrl(url);
        this.pool.setUsername(username);
        this.pool.setPassword(pass);
        this.pool.setInitialSize(5);
    }

    /**
     * Get base instance.
     * @return base object, 1 for class.
     */
    public static UserStore getBase() {
        return BASE;
    }

    /**
     * Add user to database.
     * @param user ..
     */
    public void addUser(User user) {

        try (Connection conn = this.pool.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                     "INSERT INTO users (name, login, email, createdate) VALUES (?, ?, ?, ?)")) {

            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setLong(4, user.getCreateDate());

            pst.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

    }

    /**
     * Update existing user.
     * @param id ..
     * @param user ..
     */
    public void updateUser(String id, User user) {

        try (Connection conn = this.pool.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                     "UPDATE users SET name = ?, login = ?, email = ?, createdate = ? WHERE id = ?")) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setLong(4, System.currentTimeMillis());
            pst.setInt(5, Integer.parseInt(id));

            pst.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Delete user from base.
     * @param id ..
     */
    public void deleteUser(String id) {

        try (Connection conn = this.pool.getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {

            pst.setInt(1, Integer.parseInt(id));
            pst.execute();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Show user list.
     * @return map
     */
    public Map<Integer, User> showUsers() {

        this.userMap.clear();

        try (Connection conn = this.pool.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                Long date = rs.getLong("createDate");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String email = rs.getString("email");
                int id = rs.getInt("id");

                this.userMap.put(id, new User(name, login, email, date));
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return this.userMap;
    }

    /**
     * Closing pool.
     */
    public void shutDownDataSource() {
        try {
            this.pool.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

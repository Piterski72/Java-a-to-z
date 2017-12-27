package ru.nivanov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nivanov.model.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Nikolay Ivanov on 25.10.2017.
 */
class DbaseHandler2 {

    private static final Logger LOG = LoggerFactory.getLogger(DbaseHandler2.class);
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final DbaseHandler2 BASE = new DbaseHandler2();
    private Map<Integer, User> userMap = new ConcurrentHashMap<>();
    private DataSource ds = null;

    /**
     * Private constructor.
     */
    private DbaseHandler2() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            this.ds = (DataSource) envContext.lookup("jdbc/users");
        } catch (NamingException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Get base instance.
     * @return base object, 1 for class.
     */
    static DbaseHandler2 getBase() {
        return BASE;
    }

    /**
     * Add user to database.
     * @param user ..
     */
    void addUser(User user) {

        try (Connection conn = this.ds.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                     "INSERT INTO users (name, login, email, createdate) VALUES (?, ?, ?, ?)")) {

            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(THREE, user.getEmail());
            pst.setLong(FOUR, user.getCreateDate());

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
    void updateUser(String id, User user) {

        try (Connection conn = this.ds.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                     "UPDATE users SET name = ?, login = ?, email = ?, createdate = ? WHERE id = ?")) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(THREE, user.getEmail());
            pst.setLong(FOUR, System.currentTimeMillis());
            pst.setInt(FIVE, Integer.parseInt(id));

            pst.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Delete user from base.
     * @param id ..
     */
    void deleteUser(String id) {

        try (Connection conn = this.ds.getConnection();
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
    Map<Integer, User> showUsers() {

        this.userMap.clear();

        try (Connection conn = this.ds.getConnection();
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

}

package ru.nivanov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nivanov.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Nikolay Ivanov on 25.10.2017.
 */
class DbaseHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DbaseHandler.class);
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private final Properties props = new Properties();
    private Connection conn = null;
    private Map<Integer, User> userMap = new ConcurrentHashMap<>();

    /**
     * Load base properties.
     */
    void loadProps() {
        InputStream io = getClass().getResourceAsStream("/db.properties");
        try {
            this.props.load(io);
            LOG.info("props loaded!");

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Connect to vacancy base.
     */
    void connectToBase() {

        String url = this.props.getProperty("baseUrl");
        String username = this.props.getProperty("username");
        String pass = this.props.getProperty("password");
        try {
            this.conn = DriverManager.getConnection(url, username, pass);
            LOG.info("base connected!");

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Closing connection to database.
     */
    void closeConnection() {
        LOG.info("closing connection");
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        LOG.info("connection closed!");
    }

    /**
     * Add user to database.
     * @param user ..
     */
    void addUser(User user) {

        PreparedStatement pst;

        try {
            pst = this.conn.prepareStatement("INSERT INTO users (name, login, email, createDate) VALUES (?, ?, ?, ?)");
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
    void updateUser(int id, User user) {

        PreparedStatement pst;
        try {
            pst = this.conn.prepareStatement(
                    "UPDATE users SET name = ?, login = ?, email = ?, createDate = ? WHERE id = ?");
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(THREE, user.getEmail());
            pst.setLong(FOUR, System.currentTimeMillis());
            pst.setInt(FIVE, id);

            pst.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Delete user from base.
     * @param id ..
     */
    void deleteUser(int id) {

        PreparedStatement pst;
        try {
            pst = this.conn.prepareStatement("DELETE FROM users WHERE id = ?");
            pst.setInt(1, id);

            pst.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Show user list.
     * @return map
     */
    Map<Integer, User> showUsers() {


        Statement st = null;
        ResultSet rs = null;

        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            st = this.conn.createStatement();
            rs = st.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                Long date = rs.getLong("createDate");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String email = rs.getString("email");
                int id = rs.getInt("id");
                //System.out.println(String.format("user id: %d, name: %s, login: %s, email: %s, date: %s",
                //               id, name, login, email, String.valueOf(new java.util.Date(date))));
                //LOG.info(String.format("user id: %d, name: %s, login: %s, email: %s, date: %s",
                //        id, name, login, email, String.valueOf(new java.util.Date(date))));
                this.userMap.put(id, new User(name, login, email, date));
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
                System.out.println("!!!");
            }
        }
        return this.userMap;
        //this.userMap.put(2, new User("pup", "logpup", "mail", 0));
        //return this.userMap;
    }
}

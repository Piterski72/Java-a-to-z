package ru.nivanov.model;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
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
    private User credentionalUser;

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
    public void addUser(User user, String roleid) {

        try (Connection conn = this.pool.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                     "INSERT INTO newusers (username, userlogin, useremail, roleid, userpass, createdate) VALUES (?, ?, ?, ?, ?, ?)")) {

            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setInt(4, Integer.parseInt(roleid));
            pst.setString(5, user.getPassword());
            pst.setLong(6, user.getCreateDate());

            pst.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

    }

    /**
     * Add new role to database.
     * @param role ..
     */
    public void addRole(Role role) {

        try (Connection conn = this.pool.getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO roles (rolename) VALUES (?)")) {

            pst.setString(1, role.getName());

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
                     "UPDATE newusers SET username = ?, userlogin = ?, useremail = ?, userpass = ?, createdate = ? WHERE id = ?")) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            pst.setLong(5, System.currentTimeMillis());
            pst.setInt(6, Integer.parseInt(id));

            pst.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Update existing role.
     * @param id ..
     * @param role ..
     */
    public void updateRole(String id, Role role) {

        try (Connection conn = this.pool.getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE roles SET rolename = ? WHERE roleid = ?")) {
            pst.setString(1, role.getName());
            pst.setInt(2, Integer.parseInt(id));

            pst.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Update users roles.
     * @param userid ..
     * @param roleid ..
     */
    public void updateUserRole(String userid, String roleid) {

        try (Connection conn = this.pool.getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE newusers SET roleid = ? WHERE id = ?")) {

            pst.setInt(1, Integer.parseInt(roleid));
            pst.setInt(2, Integer.parseInt(userid));

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
             PreparedStatement pst = conn.prepareStatement("DELETE FROM newusers WHERE id = ?")) {

            pst.setInt(1, Integer.parseInt(id));
            pst.execute();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Delete user from base.
     * @param id ..
     */
    public void deleteRole(String id) {

        try (Connection conn = this.pool.getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM roles WHERE roleid = ?")) {

            pst.setInt(1, Integer.parseInt(id));
            pst.execute();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Show user roles.
     * @return roles list.
     */
    public Map<Integer, Role> showRoles() {

        Map<Integer, Role> roleMap = new HashMap<>();
        try (Connection conn = this.pool.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM roles")) {

            while (rs.next()) {

                int roleid = rs.getInt("roleid");
                String rolename = rs.getString("rolename");

                roleMap.put(roleid, new Role(rolename));
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return roleMap;

    }

    /**
     * Check for login and password.
     * @param login ..
     * @param password ..
     * @return result of check.
     */
    public boolean isCredentional(String login, String password) {

        boolean exists = false;
        Collection<User> userlist = this.showUsers().values();

        for (User user : userlist) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                exists = true;
                this.credentionalUser = user;
                break;
            }
        }
        return exists;
    }

    /**
     * Show user list.
     * @return map
     */
    public Map<Integer, User> showUsers() {

        this.userMap.clear();

        try (Connection conn = this.pool.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT u.id, u.username, u.userlogin, u.useremail, \n" + "u.roleid, r.rolename, u.userpass, u.createdate\n" + "FROM newusers AS u\n" + "INNER JOIN roles AS r\n" + "ON u.roleid = r.roleid;")) {

            while (rs.next()) {
                Long date = rs.getLong("createDate");
                String name = rs.getString("username");
                String login = rs.getString("userlogin");
                String email = rs.getString("useremail");
                String rolename = rs.getString("rolename");
                String userpass = rs.getString("userpass");
                int id = rs.getInt("id");

                User currentUser = new User(name, login, email, date);
                currentUser.setPassword(userpass);
                currentUser.setRole(new Role(rolename));

                this.userMap.put(id, currentUser);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return this.userMap;
    }

    /**
     * Getter valid user.
     * @return valid user.
     */
    public User getCredentionalUser() {
        return credentionalUser;
    }

    /**
     * Get user id from base.
     * @param user ..
     * @return id.
     */
    public int getUserId(User user) {

        int foundId = -1;

        try (Connection conn = this.pool.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT u.id, u.username, u.userlogin, u.useremail, \n" + "u.roleid, r.rolename, u.userpass, u.createdate\n" + "FROM newusers AS u\n" + "INNER JOIN roles AS r\n" + "ON u.roleid = r.roleid")) {

            while (rs.next()) {
                String name = rs.getString("username");
                String login = rs.getString("userlogin");
                String email = rs.getString("useremail");
                String userpass = rs.getString("userpass");
                if (user.getLogin().equals(login) && user.getName().equals(name) && user.getEmail().equals(
                        email) && user.getPassword().equals(userpass)) {
                    foundId = rs.getInt("id");
                    break;
                }
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return foundId;
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

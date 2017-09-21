package ru.nivanov.tracker.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * StartUI class.
 * @author nivanov.
 */

class StartUI {

    private static final Logger LOG = LoggerFactory.getLogger(StartUI.class);
    private static final TrackerConfig CONFIG = new TrackerConfig();
    private Connection connection;

    /**
     * main method.
     * @param args input param
     */
    public static void main(String[] args) {

        StartUI start = new StartUI();

        Input input = new ValidateInput();
        Tracker tracker = new Tracker(start.establishConnection());

        start.init(input, tracker);

        start.closeConnection();
    }

    /**
     * Build application. Show menu. User select.
     * @param input ..
     * @param tracker ..
     */
    private void init(Input input, Tracker tracker) {

        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("Select: ", menu.getRange()));
        } while (!"y".equals(input.ask("Exit? (y) ")));

    }

    /**
     * Establish connection to database.
     */
    private void createBase() {
        System.out.println("Creating new database...");

        this.connection = null;
        Statement st = null;

        String url = CONFIG.getDefaultUrl();
        String username = CONFIG.getUserName();
        String password = CONFIG.getPassword();
        String basename = CONFIG.getBaseName();

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            st = this.connection.createStatement();
            st.execute(String.format("CREATE DATABASE %s", basename));

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    LOG.error(e.getMessage(), e);
                }
            }
            if (this.connection != null) {
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        System.out.println("new database created!");
    }

    /**
     * Create tables.
     */
    private void createTables() {

        System.out.println("Creating new tables...");

        Statement st = null;
        this.connection = null;

        String baseUrl = CONFIG.getBaseUrl();
        String username = CONFIG.getUserName();
        String password = CONFIG.getPassword();

        try {
            this.connection = DriverManager.getConnection(baseUrl, username, password);
            st = this.connection.createStatement();
            st.execute(
                    "CREATE TABLE IF NOT EXISTS item (id SERIAL NOT NULL PRIMARY KEY, name TEXT NOT NULL, description TEXT NOT NULL, create_date DATE NOT NULL)");
            st.execute(
                    "CREATE TABLE IF NOT EXISTS comment (id SERIAL NOT NULL PRIMARY KEY, comm_desc TEXT NOT NULL, item_id INTEGER NOT NULL REFERENCES item (id))");

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);

        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        System.out.println("new tables created!");
    }

    /**
     * Closing connection to database.
     */
    private void closeConnection() {
        System.out.println("closing connection");
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage(), e);
            }
        }
        System.out.println("connection closed!");
    }

    /**
     * Creating connection.
     * @return ..
     */
    private Connection establishConnection() {

        createBase();
        createTables();

        return this.connection;
    }
}
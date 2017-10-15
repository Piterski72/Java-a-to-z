package ru.nivanov.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nivanov.model.Vacancy;

import java.sql.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Nikolay Ivanov on 09.10.2017.
 */
public class DBaseManager {

    private static final Logger LOG = LoggerFactory.getLogger(DBaseManager.class);
    private final DBaseConfig config = new DBaseConfig();
    private Connection connection;

    /**
     * Constructor.
     */
    public DBaseManager() {
        this.config.loadProps();
    }

    /**
     * Closing connection to database.
     */
    public void closeConnection() {
        LOG.info("closing connection");
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        LOG.info("connection closed!");
    }

    /**
     * Creating connection.
     */
    public void init() {

        createBase();
        createTables();
    }

    /**
     * Establish connection to database.
     */
    private void createBase() {
        LOG.info("Creating new database...");

        this.connection = null;
        Statement st = null;

        try {
            this.connection = DriverManager.getConnection(this.config.getUrl(), this.config.getUsername(),
                    this.config.getPassword());
            st = this.connection.createStatement();
            st.execute(String.format("CREATE DATABASE %s", this.config.getBasename()));

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            LOG.info("closing connection");
            if (this.connection != null) {
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            LOG.info("connection closed!");
        }
        LOG.info("new database created!");
    }

    /**
     * Create tables.
     */
    private void createTables() {

        LOG.info("Creating new tables...");

        Statement st = null;
        Connection conn = connectToBase();

        try {
            st = conn.createStatement();
            st.execute(
                    "CREATE TABLE IF NOT EXISTS vacancy (id SERIAL NOT NULL PRIMARY KEY, vac_name TEXT NOT NULL, vacancy_url TEXT NOT NULL , author TEXT NOT NULL , author_url TEXT NOT NULL, create_date BIGINT NOT NULL, CONSTRAINT vacuniq UNIQUE (vac_name, author, author_url))");
            st.execute(
                    "CREATE TABLE IF NOT EXISTS launch_rep (id SERIAL NOT NULL PRIMARY KEY, last_start BIGINT NOT NULL, new_vac_found INTEGER)");
            st.execute("INSERT INTO launch_rep (last_start) VALUES (0)");

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);

        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            LOG.info("closing connection");
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            LOG.info("connection closed!");
        }
        LOG.info("new tables created!");
    }

    /**
     * Connect to vacancy base.
     * @return connection.
     */
    private Connection connectToBase() {
        try {
            this.connection = DriverManager.getConnection(this.config.getBaseUrl(), this.config.getUsername(),
                    this.config.getPassword());

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return this.connection;

    }

    /**
     * Insert record into table.
     * @param vacancySet ..
     */
    public void addVacancy(Set<Vacancy> vacancySet) {

        final int three = 3;
        final int four = 4;
        final int five = 5;

        PreparedStatement pst = null;

        try {
            this.connection.setAutoCommit(false);
            pst = this.connection.prepareStatement(
                    "INSERT INTO vacancy (vac_name, vacancy_url, author, author_url, create_date) VALUES (?, ?, ?, ?, ?) ON CONFLICT ON CONSTRAINT vacuniq DO UPDATE SET vacancy_url = EXCLUDED.vacancy_url, create_date = EXCLUDED.create_date");

            for (Vacancy vacancy : vacancySet) {

                pst.setString(1, vacancy.getName());
                pst.setString(2, vacancy.getUrl());
                pst.setString(three, vacancy.getAuthor());
                pst.setString(four, vacancy.getAuthorUrl());
                pst.setLong(five, vacancy.getCreateDate());
                pst.addBatch();
            }
            pst.executeBatch();

            this.connection.commit();
            this.connection.setAutoCommit(true);

            pst = this.connection.prepareStatement("INSERT INTO launch_rep (last_start, new_vac_found) VALUES (?, ?)");
            pst.setLong(1, System.currentTimeMillis());
            pst.setInt(2, vacancySet.size());
            pst.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                assert pst != null;
                pst.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Get last visited date (long).
     * @return ..
     */
    public long getLastVisit() {

        Statement st = null;
        ResultSet rs = null;
        long result = 0;
        Connection conn = connectToBase();

        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT MAX(last_start) AS RESULT FROM launch_rep");

            if (rs.next()) {
                result = rs.getLong("RESULT");
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {

            try {
                assert rs != null;
                rs.close();
                st.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * Show found vacancies.
     */
    public void showVacancies() {

        Statement st = null;
        ResultSet rs = null;
        Connection conn = connectToBase();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM vacancy ORDER BY create_date DESC");

            while (rs.next()) {
                Long date = rs.getLong("create_date");
                String vacname = rs.getString("vac_name");
                String vacurl = rs.getString("vacancy_url");
                String author = rs.getString("author");
                String auuthorUrl = rs.getString("author_url");
                LOG.info(String.format("vacancy desc: %s, vacancy url: %s, author nick: %s, author url: %s, date: %s",
                        vacname, vacurl, author, auuthorUrl, String.valueOf(new Date(date))));
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {

            try {
                assert rs != null;
                rs.close();
                st.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}

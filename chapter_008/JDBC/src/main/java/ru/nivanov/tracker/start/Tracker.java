package ru.nivanov.tracker.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nivanov.tracker.models.Item;

import java.sql.*;

/**
 * Tracker.
 * @author nivanov.
 */

class Tracker {

    private static final Logger LOG = LoggerFactory.getLogger(Tracker.class);
    private final Connection conn;

    /**
     * Constructor.
     * @param conn ..
     */
    Tracker(Connection conn) {
        this.conn = conn;
    }

    /**
     * Add Item.
     * @param item input parameter
     * @return item - add ok
     */
    Item add(Item item) {

        final int three = 3;

        try (PreparedStatement pst = this.conn.prepareStatement(
                "INSERT INTO item (name, description, create_date) VALUES (?, ?, ?)")) {

            pst.setString(1, item.getName());
            pst.setString(2, item.getDescription());
            pst.setDate(three, new Date(System.currentTimeMillis()));
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        }

        return item;
    }

    /**
     * Add Items comment.
     * @param itemId input parameter
     * @param comment input parameter
     */
    void addComment(String itemId, String comment) {

        int id = Integer.parseInt(itemId);

        try (PreparedStatement pst = this.conn.prepareStatement(
                "INSERT INTO comment (comm_desc, item_id) VALUES (?, ?)")) {

            pst.setString(1, comment);
            pst.setInt(2, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Edit2 Items fields.
     * @param itemId input Item Id
     * @param newname input Item Name
     * @param desc input Item description
     */
    void ieditTwo(int itemId, String newname, String desc) {

        final int three = 3;

        try (PreparedStatement pst = this.conn.prepareStatement(
                "UPDATE item SET name = ?, description = ? WHERE item.id = ?")) {

            pst.setString(1, newname);
            pst.setString(2, desc);
            pst.setInt(three, itemId);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Remove Item from list.
     * @param itemId is item id
     */
    void remove(int itemId) {

        try (PreparedStatement pst = this.conn.prepareStatement("DELETE FROM item WHERE item.id = ?")) {

            pst.setInt(1, itemId);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Getting filtered list (all not null).
     */
    void getAll() {

        ResultSet rs = null;

        try (Statement st = this.conn.createStatement()) {
            rs = st.executeQuery(
                    "SELECT item.id, item.name, item.description, comment.comm_desc, item.create_date FROM item LEFT OUTER JOIN comment ON item.id = comment.item_id");
            while (rs.next()) {
                System.out.println(
                        String.format("Id %d name %s desc %s comment %s date %s", rs.getInt(1), rs.getString("name"),
                                rs.getString("description"), rs.getString("comm_desc"), rs.getDate("create_date")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Getting filtered list (by name).
     * @param name input parameter
     */
    void getByName(String name) {
        ResultSet rs = null;

        try (PreparedStatement pst = this.conn.prepareStatement(
                "SELECT item.id, item.name, item.description, comment.comm_desc, item.create_date FROM item LEFT OUTER JOIN comment ON item.id = comment.item_id WHERE item.name = ?")) {

            pst.setString(1, name);
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println(
                        String.format("Id %d name %s desc %s comment %s date %s", rs.getInt("id"), rs.getString("name"),
                                rs.getString("description"), rs.getString("comm_desc"), rs.getDate("create_date")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Getting filtered list (by description).
     * @param desc input parameter
     */
    void getByDesc(String desc) {

        ResultSet rs = null;
        try (PreparedStatement pst = this.conn.prepareStatement(
                "SELECT item.id, item.name, item.description, comment.comm_desc, item.create_date FROM item LEFT OUTER JOIN comment ON item.id = comment.item_id WHERE item.description = ?")) {

            pst.setString(1, desc);
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println(
                        String.format("Id %d name %s desc %s comment %s date %s", rs.getInt("id"), rs.getString("name"),
                                rs.getString("description"), rs.getString("comm_desc"), rs.getDate("create_date")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}
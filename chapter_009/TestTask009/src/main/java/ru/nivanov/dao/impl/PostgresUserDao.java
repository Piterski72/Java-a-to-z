package ru.nivanov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nivanov.baseutils.NoConnectException;
import ru.nivanov.baseutils.PostgresBaseUtils;
import ru.nivanov.dao.UserDao;
import ru.nivanov.entity.User;

import java.sql.*;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Nikolay Ivanov on 17.04.2018.
 */
public class PostgresUserDao implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(PostgresUserDao.class);

    @Override
    public int create(User user) {
        int result = -1;
        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement(
                     "INSERT INTO public.user (username, addressident, roleident) VALUES (?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, user.getName());
            pst.setInt(2, user.getAddressid());
            pst.setInt(3, user.getRoleid());
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    result = rs.getInt("userid");
                    user.setId(result);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        if (user.getMusicType() != null && !user.getMusicType().isEmpty()) {
            this.setMusicType(user);
        } else {
            Set<Integer> musicTypes = new CopyOnWriteArraySet<>();
            user.setMusicTypeList(musicTypes);
        }
        return result;
    }

    /**
     * Update user music types
     * @param user ..
     * @return ..
     */
    private Set<Integer> setMusicType(User user) {
        Set<Integer> musTypes = user.getMusicType();
        int userid = user.getId();
        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM public.usermusik WHERE iduser = ?")) {
            pst.setInt(1, user.getId());
            pst.executeUpdate();

            for (Integer val : musTypes) {
                try (PreparedStatement pst2 = conn.prepareStatement(
                        "INSERT INTO public.usermusik (iduser, idmusik) VALUES (?, ?)")) {
                    pst2.setInt(1, userid);
                    pst2.setInt(2, val);
                    pst2.executeUpdate();
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        return musTypes;
    }

    @Override
    public User update(int id, User user) {

        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement(
                     "UPDATE public.user SET username = ?, addressident = ?, roleident = ? WHERE userid = ?")) {
            pst.setString(1, user.getName());
            pst.setInt(2, user.getAddressid());
            pst.setInt(3, user.getRoleid());
            pst.setInt(4, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        user.setId(id);
        if (user.getMusicType() != null && !user.getMusicType().isEmpty()) {
            this.setMusicType(user);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        int result = -1;
        User user = this.getById(id);
        int addrId = user.getAddressid();

        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM public.user WHERE userid = ?");
             PreparedStatement pst2 = conn.prepareStatement("DELETE FROM public.usermusik WHERE iduser = ?");
             PreparedStatement pst3 = conn.prepareStatement("DELETE FROM public.adress WHERE addrid = ?")) {
            pst.setInt(1, id);
            result = pst.executeUpdate();
            pst2.setInt(1, id);
            pst2.executeUpdate();
            pst3.setInt(1, addrId);
            pst3.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        return result != -1;
    }

    @Override
    public User getById(int id) {
        User foundUser = new User();
        foundUser.setName("empty");
        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM public.user WHERE userid = ?")) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    foundUser = new User();
                    foundUser.setId(rs.getInt("userid"));
                    foundUser.setName(rs.getString("username"));
                    foundUser.setAddressid(rs.getInt("addressident"));
                    foundUser.setRoleid(rs.getInt("roleident"));
                    foundUser.setMusicTypeList(this.getMusikType(id));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        return foundUser;
    }

    /**
     * Get music types by user id.
     * @param userid ..
     * @return ..
     */
    private Set<Integer> getMusikType(int userid) throws NoConnectException {
        Set<Integer> musTypes = new CopyOnWriteArraySet<>();
        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM public.usermusik WHERE iduser = ?")) {
            pst.setInt(1, userid);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    musTypes.add(rs.getInt("idmusik"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return musTypes;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> users = new CopyOnWriteArrayList<>();
        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM public.user")) {
            while (rs.next()) {
                User current = new User();
                current.setId(rs.getInt("userid"));
                current.setName(rs.getString("username"));
                current.setAddressid(rs.getInt("addressident"));
                current.setRoleid(rs.getInt("roleident"));
                current.setMusicTypeList(this.getMusikType(current.getId()));
                users.add(current);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }
}


package ru.nivanov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nivanov.baseutils.NoConnectException;
import ru.nivanov.baseutils.PostgresBaseUtils;
import ru.nivanov.dao.MusicTypeDao;
import ru.nivanov.entity.MusicType;

import java.sql.*;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Nikolay Ivanov on 18.04.2018.
 */
public class PostgresMusicTypeDao implements MusicTypeDao {
    private static final Logger LOG = LoggerFactory.getLogger(PostgresMusicTypeDao.class);

    @Override
    public int create(MusicType entity) {
        int result = -1;
        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO public.musictype (music) VALUES (?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, entity.getType());
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    result = rs.getInt("musid");
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public MusicType update(int id, MusicType entity) {
        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement("UPDATE public.musictype SET music =? WHERE musid=?")) {
            pst.setString(1, entity.getType());
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        return entity;
    }

    @Override
    public boolean delete(int id) {
        int result = -1;
        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement("DELETE  FROM public.musictype WHERE musid=?")) {
            pst.setInt(1, id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        return result != -1;
    }

    @Override
    public Collection<MusicType> getAll() {
        Collection<MusicType> muslist = new CopyOnWriteArrayList<>();
        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM public.musictype");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                MusicType current = new MusicType();
                current.setId(rs.getInt("musid"));
                current.setType(rs.getString("music"));
                muslist.add(current);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        return muslist;
    }

    @Override
    public MusicType getById(int id) {
        MusicType mus = new MusicType();
        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM public.musictype WHERE musid=?")) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    mus.setId(rs.getInt("musid"));
                    mus.setType(rs.getString("music"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        return mus;
    }
}

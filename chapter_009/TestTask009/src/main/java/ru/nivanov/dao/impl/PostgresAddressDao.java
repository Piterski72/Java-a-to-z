package ru.nivanov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nivanov.baseutils.PostgresBaseUtils;
import ru.nivanov.dao.AddressDao;
import ru.nivanov.entity.Address;

import java.sql.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Nikolay Ivanov on 18.04.2018.
 */
public class PostgresAddressDao implements AddressDao {
    private static final Logger LOG = LoggerFactory.getLogger(PostgresAddressDao.class);

    @Override
    public int create(Address entity) {

        int result = -1;
        ResultSet rs;

        try (Connection conn = PostgresBaseUtils.getBase().getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO public.adress (location) VALUES (?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, entity.getLocation());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt("addrid");
            }
            rs.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Address update(int id, Address entity) {

        try (Connection conn = PostgresBaseUtils.getBase().getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE public.adress SET location =? WHERE addrid=?")) {
            pst.setString(1, entity.getLocation());
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return entity;
    }

    @Override
    public boolean delete(int id) {
        int result = -1;
        try (Connection conn = PostgresBaseUtils.getBase().getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE  FROM public.adress WHERE addrid=?")) {
            pst.setInt(1, id);
            result = pst.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result != -1;
    }

    @Override
    public Collection<Address> getAll() {
        List<Address> addrlist = new CopyOnWriteArrayList<>();
        ResultSet rs = null;
        try (Connection conn = PostgresBaseUtils.getBase().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM public.adress")) {
            rs = pst.executeQuery();
            while (rs.next()) {
                Address current = new Address();
                current.setId(rs.getInt("addrid"));
                current.setLocation(rs.getString("location"));
                addrlist.add(current);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                assert rs != null;
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return addrlist;
    }

    @Override
    public Address getById(int id) {
        Address addr = new Address();
        ResultSet rs = null;
        try (Connection conn = PostgresBaseUtils.getBase().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM public.adress WHERE addrid=?")) {
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                addr.setId(rs.getInt("addrid"));
                addr.setLocation(rs.getString("location"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                assert rs != null;
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return addr;
    }

}

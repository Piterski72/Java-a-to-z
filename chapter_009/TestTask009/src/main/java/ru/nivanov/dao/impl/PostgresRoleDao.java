package ru.nivanov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nivanov.baseutils.PostgresBaseUtils;
import ru.nivanov.dao.RoleDao;
import ru.nivanov.entity.Role;

import java.sql.*;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Nikolay Ivanov on 18.04.2018.
 */
public class PostgresRoleDao implements RoleDao {
    private static final Logger LOG = LoggerFactory.getLogger(PostgresRoleDao.class);

    @Override
    public int create(Role entity) {
        int result = -1;
        ResultSet rs;

        try (Connection conn = PostgresBaseUtils.getBase().getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO public.role (rolename) VALUES (?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, entity.getRolename());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt("roleid");
            }
            rs.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Role update(int id, Role entity) {

        try (Connection conn = PostgresBaseUtils.getBase().getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE public.role SET rolename =? WHERE roleid=?")) {
            pst.setString(1, entity.getRolename());
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
             PreparedStatement pst = conn.prepareStatement("DELETE  FROM public.role WHERE roleid=?")) {
            pst.setInt(1, id);
            result = pst.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result != -1;
    }

    @Override
    public Collection<Role> getAll() {
        Collection<Role> roles = new CopyOnWriteArrayList<>();
        ResultSet rs = null;
        try (Connection conn = PostgresBaseUtils.getBase().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM public.role")) {
            rs = pst.executeQuery();
            while (rs.next()) {
                Role current = new Role();
                current.setId(rs.getInt("roleid"));
                current.setRolename(rs.getString("rolename"));
                roles.add(current);
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
        return roles;
    }

    @Override
    public Role getById(int id) {
        Role role = new Role();
        ResultSet rs = null;
        try (Connection conn = PostgresBaseUtils.getBase().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM public.role WHERE roleid=?")) {
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                role.setId(rs.getInt("roleid"));
                role.setRolename(rs.getString("rolename"));
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
        return role;
    }
}

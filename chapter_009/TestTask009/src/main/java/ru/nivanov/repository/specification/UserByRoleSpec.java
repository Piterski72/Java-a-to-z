package ru.nivanov.repository.specification;

import ru.nivanov.entity.Role;

/**
 * Created by Nikolay Ivanov on 07.05.2018.
 */
public class UserByRoleSpec implements SqlSpec {
    private final Role role;

    /**
     * Constructor.
     * @param role ..
     */
    public UserByRoleSpec(Role role) {
        this.role = role;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT %1$s FROM %2$s WHERE %3$s=%4$d", "userid", "public.user", "roleident",
                this.role.getId());
    }
}

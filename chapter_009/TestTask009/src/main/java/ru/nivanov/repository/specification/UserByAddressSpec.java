package ru.nivanov.repository.specification;

import ru.nivanov.entity.Address;

/**
 * Created by Nikolay Ivanov on 04.05.2018.
 */
public class UserByAddressSpec implements SqlSpec {
    private final Address address;

    /**
     * Constructor.
     * @param address ..
     */
    public UserByAddressSpec(Address address) {
        this.address = address;
    }

    @Override
    public String toSqlQuery() {

        return String.format("SELECT %1$s FROM %2$s WHERE %3$s=%4$d", "userid", "public.user", "addressident",
                this.address.getId());
    }
}

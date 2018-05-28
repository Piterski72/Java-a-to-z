package ru.nivanov.repository.mapper;

import ru.nivanov.dao.AddressDao;
import ru.nivanov.dao.factory.DaoFactory;
import ru.nivanov.dao.factory.PostgresDaoFactory;
import ru.nivanov.entity.Address;

import java.util.Collection;

/**
 * Created by Nikolay Ivanov on 17.05.2018.
 */
public class StringToAddressMapper implements Mapper<String, Address> {
    private DaoFactory factory = new PostgresDaoFactory();
    private AddressDao addressDao = factory.getAddressDao();

    @Override
    public Address map(String addr) {
        Address result = null;

        Collection<Address> addresses = this.addressDao.getAll();
        for (Address loc : addresses) {
            if (loc.getLocation().equalsIgnoreCase(addr)) {
                result = loc;
                break;
            }
        }
        if (result == null) {
            result = new Address();
            result.setLocation("empty");
        }
        return result;
    }
}

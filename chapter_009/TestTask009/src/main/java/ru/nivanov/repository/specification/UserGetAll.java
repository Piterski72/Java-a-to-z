package ru.nivanov.repository.specification;

/**
 * Created by Nikolay Ivanov on 11.05.2018.
 */
public class UserGetAll implements SqlSpec {
    @Override
    public String toSqlQuery() {
        return String.format("SELECT %1$s FROM %2$s", "userid", "public.user");
    }
}

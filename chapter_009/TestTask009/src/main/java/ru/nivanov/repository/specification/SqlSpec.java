package ru.nivanov.repository.specification;

/**
 * Created by Nikolay Ivanov on 04.05.2018.
 */
public interface SqlSpec extends Specification {
    String toSqlQuery();
}

package ru.nivanov.repository.mapper;

/**
 * Created by Nikolay Ivanov on 04.05.2018.
 */
public interface Mapper<From, To> {
    To map(From from);
}

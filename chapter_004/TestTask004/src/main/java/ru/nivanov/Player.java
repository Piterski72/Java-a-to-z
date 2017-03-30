package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 28.03.2017.
 */
interface Player {
    /**
     * player move.
     * @return cell ..
     */
    Cell playerMove();

    /**
     * setting player mark.
     * @param mark ..
     */
    void setMark(String mark);

    /**
     * Get player name.
     * @return ..
     */
    String getName();
}

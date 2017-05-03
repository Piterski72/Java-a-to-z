package ru.nivanov.foods;

/**
 * Created by Nikolay Ivanov on 03.03.2017.
 */
public interface GeneralFood {
    /**
     * @return ..
     */
    String getName();

    /**
     * @return ..
     */
    String getExpireDate();

    /**
     * @return ..
     */
    String getCreateDate();

    /**
     * Get shelf life.
     * @param date ..
     * @return ..
     */
    double getShelfLifePercent(String date);

    /**
     * @return ..
     */
    int getDiscount();

    /**
     * @param price ..
     */
    void setNewPrice(double price);

    /**
     * @return ..
     */
    double getPrice();

    /**
     * @return ..
     */
    String toString();

}

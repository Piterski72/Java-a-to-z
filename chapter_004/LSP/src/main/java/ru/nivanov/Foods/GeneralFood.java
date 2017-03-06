package ru.nivanov.Foods;

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
     * @return ..
     */
    double getShelfLifePercent();

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

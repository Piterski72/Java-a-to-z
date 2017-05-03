package ru.nivanov.foods;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public abstract class Food implements GeneralFood {
    private static final int STOODIN = 101;
    private static final int STO = 100;
    private String name;
    private double price;
    private String expireDate;
    private String createDate;

    /**
     * Constructor.
     * @param name ..
     * @param price ..
     * @param createDate ..
     * @param expireDate ..
     */
    Food(String name, double price, String createDate, String expireDate) {
        this.name = name;
        this.price = price;
        this.createDate = createDate;
        this.expireDate = expireDate;
    }

    /**
     * Name getter.
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Expire date getter.
     * @return ..
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * Creation date getter.
     * @return ..
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Calculate shelf life percentage.
     * @param inputDate ..
     * @return ..
     */
    public double getShelfLifePercent(String inputDate) {
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateOne = null;
        Date dateTwo = null;
        Date currentDate = null;

        double shelfLifePercentage;
        try {
            currentDate = dayFormat.parse(inputDate);
            dateOne = dayFormat.parse(getCreateDate());
            dateTwo = dayFormat.parse(getExpireDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert dateTwo != null;
        assert currentDate != null;
        if (currentDate.after(dateTwo)) {
            shelfLifePercentage = STOODIN;
        } else {
            assert dateOne != null;
            double shelfLife = dateTwo.getTime() - dateOne.getTime();
            double currentLife = currentDate.getTime() - dateOne.getTime();
            shelfLifePercentage = currentLife / shelfLife * STO;
        }
        return shelfLifePercentage;
    }

    /**
     * Discount getter.
     * @return ..
     */
    public abstract int getDiscount();

    /**
     * Set new price.
     * @param price ..
     */
    public void setNewPrice(double price) {
        this.price = price;
    }

    /**
     * Price getter.
     * @return ..
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * To string method.
     * @return ..
     */
    @Override
    public String toString() {
        return String.format("name/ %s /price %,.2f /create date %s /expire date %s", getName(), getPrice(),
                getCreateDate(), getExpireDate());
    }

}

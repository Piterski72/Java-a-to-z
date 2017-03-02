package ru.nivanov;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
class Food {
    private static final int TWENTY = 20;
    private static final int STOODIN = 101;
    private static final int STO = 100;
    private String name;
    private double price;
    private String expireDate;
    private String createDate;
    private int discount = TWENTY;

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
    private String getName() {
        return this.name;
    }

    /**
     * Expire date getter.
     * @return ..
     */
    private String getExpireDate() {
        return expireDate;
    }

    /**
     * Creation date getter.
     * @return ..
     */
    private String getCreateDate() {
        return createDate;
    }

    /**
     * Calculate shelf life percentage.
     * @return ..
     */
    public double getShelfLifePercent() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateOne = null;
        Date dateTwo = null;
        Date currentDate = new Date();
        double shelfLifePercentage;
        try {
            dateOne = dayFormat.parse(getCreateDate());
            dateTwo = dayFormat.parse(getExpireDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (currentDate.after(dateTwo)) {
            shelfLifePercentage = STOODIN;
        } else {
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
    public int getDiscount() {
        return this.discount;
    }

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
        String description = String.format("name/ %s /price %,.2f /create date %s /expire date %s", getName(),
                getPrice(), getCreateDate(), getExpireDate());
        return description;
    }

}

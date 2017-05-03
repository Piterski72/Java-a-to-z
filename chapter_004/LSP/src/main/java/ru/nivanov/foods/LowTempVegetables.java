package ru.nivanov.foods;

/**
 * Created by Nikolay Ivanov on 06.03.2017.
 */
public class LowTempVegetables extends Food {
    private static final int DISCOUNT = 20;
    /**
     * This field used for recognizing products for low temp storage.
     */
    private static final String LOW_TEMP = "LOW_TEMP";
    private int discount = DISCOUNT;

    public LowTempVegetables(String name, double price, String createDate, String expireDate) {
        super(name, price, createDate, expireDate);
    }

    /**
     * @return ..
     */
    @Override
    public int getDiscount() {
        return this.discount;
    }

    @Override
    public String toString() {
        return String.format("name/ %s /price %,.2f /create date %s /expire date %s", getName(), getPrice(),
                getCreateDate(), getExpireDate());
    }

    /**
     * Name getter.
     * @return name
     */
    @Override
    public String getName() {
        return super.getName() + "_" + LOW_TEMP;
    }
}

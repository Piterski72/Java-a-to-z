package ru.nivanov.Foods;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class VegetableFood extends Food {
    private static final int DISCOUNT = 10;
    private int discount = DISCOUNT;

    /**
     * Constructor.
     * @param name ..
     * @param price ..
     * @param createDate ..
     * @param expireDate ..
     */
    public VegetableFood(String name, double price, String createDate, String expireDate) {
        super(name, price, createDate, expireDate);
    }

    /**
     * Discount getter.
     * @return ..
     */
    @Override
    public int getDiscount() {
        return this.discount;
    }
}

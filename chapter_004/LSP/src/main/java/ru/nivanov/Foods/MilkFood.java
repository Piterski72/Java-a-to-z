package ru.nivanov.Foods;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class MilkFood extends Food {
    private static final int DISCOUNT = 25;
    private int discount = DISCOUNT;

    /**
     * Constructor.
     * @param name ..
     * @param price ..
     * @param createDate ..
     * @param expireDate ..
     */
    public MilkFood(String name, double price, String createDate, String expireDate) {
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

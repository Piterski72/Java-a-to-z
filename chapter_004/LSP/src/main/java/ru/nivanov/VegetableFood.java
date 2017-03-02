package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
class VegetableFood extends Food {
    private static final int TEN = 10;
    private int discount = TEN;

    /**
     * Constructor.
     * @param name ..
     * @param price ..
     * @param createDate ..
     * @param expireDate ..
     */
    VegetableFood(String name, double price, String createDate, String expireDate) {
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

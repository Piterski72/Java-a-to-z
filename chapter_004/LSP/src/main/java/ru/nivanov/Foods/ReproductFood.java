package ru.nivanov.Foods;

/**
 * Created by Nikolay Ivanov on 03.03.2017.
 */
public class ReproductFood extends Food {
    private static final int DISCOUNT = 30;
    /**
     * This field is used for products witch can be moved to reproducted factory and reproducted.
     */
    private static final String CAN_REPRODUCT = "CAN_REPRODUCT";
    private int discount = DISCOUNT;

    /**
     * @param name ..
     * @param price ..
     * @param createDate ..
     * @param expireDate ..
     */
    public ReproductFood(String name, double price, String createDate, String expireDate) {
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
        return super.getName() + "_" + CAN_REPRODUCT;
    }
}

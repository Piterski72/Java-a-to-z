package ru.nivanov.Foods;

/**
 * Created by Nikolay Ivanov on 06.03.2017.
 */
public class LowTempVegetables extends SpecialFood {
    private static final int DISCOUNT = 20;
    private String lowTemp = "lowTemp";
    private int discount = DISCOUNT;

    public LowTempVegetables(GeneralFood specialfood) {
        super(specialfood);
    }

    /**
     * @return ..
     */
    @Override
    public String getName() {
        return (String.format("%s_%s", super.getName(), lowTemp));
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
}

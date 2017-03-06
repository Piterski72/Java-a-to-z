package ru.nivanov.Foods;

/**
 * Created by Nikolay Ivanov on 03.03.2017.
 */
public class ReproductCakes extends SpecialFood {
    private static final int DISCOUNT = 30;
    private String canReproduct = "canReproduct";
    private int discount = DISCOUNT;

    /**
     * Constructor.
     * @param food ..
     */
    public ReproductCakes(GeneralFood food) {
        super(food);
    }

    /**
     * @return ..
     */
    @Override
    public String getName() {
        return (String.format("%s_%s", super.getName(), canReproduct));
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

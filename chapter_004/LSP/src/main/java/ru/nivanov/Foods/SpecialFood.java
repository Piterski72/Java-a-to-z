package ru.nivanov.Foods;

/**
 * Created by Nikolay Ivanov on 03.03.2017.
 */
public abstract class SpecialFood implements GeneralFood {
    private GeneralFood specialfood;

    SpecialFood(GeneralFood specialfood) {
        this.specialfood = specialfood;
    }

    /**
     * @return ..
     */
    @Override
    public String getName() {
        return this.specialfood.getName();
    }

    /**
     * @return ..
     */
    @Override
    public String getExpireDate() {
        return this.specialfood.getExpireDate();
    }

    /**
     * @return ..
     */
    @Override
    public String getCreateDate() {
        return this.specialfood.getCreateDate();
    }

    /**
     * @return ..
     */
    @Override
    public double getShelfLifePercent() {
        return this.specialfood.getShelfLifePercent();
    }

    /**
     * @return ..
     */
    @Override
    public int getDiscount() {
        return this.specialfood.getDiscount();
    }

    /**
     * @param price ..
     */
    @Override
    public void setNewPrice(double price) {
        this.specialfood.setNewPrice(price);

    }

    /**
     * @return ..
     */
    @Override
    public double getPrice() {
        return this.specialfood.getPrice();
    }
}

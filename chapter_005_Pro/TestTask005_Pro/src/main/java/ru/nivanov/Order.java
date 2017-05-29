package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 26.05.2017.
 */
class Order {
    private String book;
    private String orderType;
    private float price;
    private int volume;

    /**
     * Constructor.
     * @param book ..
     * @param orderType ..
     * @param price ..
     */
    Order(String book, String orderType, float price, int volume) {

        this.book = book;
        this.orderType = orderType;
        this.price = price;
        this.volume = volume;
    }

    /**
     * Book field getter.
     * @return ..
     */
    public String getBook() {
        return book;
    }

    /**
     * Order type getter.
     * @return ..
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Price getter.
     * @return ..
     */
    public float getPrice() {
        return price;
    }

    /**
     * Volume getter.
     * @return ..
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Volume setter.
     * @param volume ..
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

}

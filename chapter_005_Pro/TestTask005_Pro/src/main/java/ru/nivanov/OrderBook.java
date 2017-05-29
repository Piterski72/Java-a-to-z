package ru.nivanov;

import java.util.*;

/**
 * Created by Nikolay Ivanov on 26.05.2017.
 */
class OrderBook {
    private String name;
    private HashMap<Integer, Order> unsorted = new HashMap<>();
    private List<OrderBook> orderBooksList;
    private Set<String> bookNames = new TreeSet<>();
    private TreeMap<Float, Order> buyOrders;
    private TreeMap<Float, Order> sellOrders;
    private String sep = System.getProperty("line.separator");

    /**
     * Constructor.
     */
    OrderBook(String name) {
        this.name = name;
        this.buyOrders = new TreeMap<>(new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
                return -o1.compareTo(o2);
            }
        });
        this.sellOrders = new TreeMap<>(new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
                return o1.compareTo(o2);
            }
        });
    }

    /**
     * New order book generator.
     * @param name ..
     * @return ..
     */
    private OrderBook newOrderBook(String name) {
        return new OrderBook(name);
    }

    /**
     * Getter for unsorted orders.
     * @return ..
     */
    HashMap<Integer, Order> getUnsorted() {
        return unsorted;
    }

    /**
     * Book names set getter.
     * @return ..
     */
    Set<String> getBookNames() {
        return bookNames;
    }

    /**
     * Filling books with sell and buy orders.
     */
    void fillBooks() {
        bookSetup();
        Collection<Order> ordersList = unsorted.values();
        Iterator<Order> iter = ordersList.iterator();
        while (iter.hasNext()) {
            Order current = iter.next();

            for (int i = 0; i < orderBooksList.size(); i++) {
                if (current.getBook().equals(orderBooksList.get(i).name)) {
                    if (current.getOrderType().equals("BUY")) {

                        if (orderBooksList.get(i).buyOrders.containsKey(current.getPrice())) {
                            int newVolume = current.getVolume() + orderBooksList.get(i).buyOrders.get(
                                    current.getPrice()).getVolume();

                            current.setVolume(newVolume);
                            orderBooksList.get(i).buyOrders.put(current.getPrice(), current);
                            break;
                        } else {
                            orderBooksList.get(i).buyOrders.put(current.getPrice(), current);
                            break;
                        }
                    } else {

                        if (orderBooksList.get(i).sellOrders.containsKey(current.getPrice())) {
                            int newVolume = current.getVolume() + orderBooksList.get(i).sellOrders.get(
                                    current.getPrice()).getVolume();
                            current.setVolume(newVolume);
                            orderBooksList.get(i).sellOrders.put(current.getPrice(), current);
                            break;
                        } else {
                            orderBooksList.get(i).sellOrders.put(current.getPrice(), current);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Setting up sell orders and buy ordres collections.
     */
    private void bookSetup() {
        orderBooksList = new ArrayList<>();
        for (String value : bookNames) {
            orderBooksList.add(newOrderBook(value));

        }
    }

    /**
     * Executing matching logic.
     */
    void bookExecute() {

        for (OrderBook book : orderBooksList) {

            Iterator<Map.Entry<Float, Order>> buyIter = book.buyOrders.entrySet().iterator();
            Iterator<Map.Entry<Float, Order>> sellIter = book.sellOrders.entrySet().iterator();

            while (buyIter.hasNext() && sellIter.hasNext()) {

                Map.Entry<Float, Order> currentBuy = buyIter.next();
                Map.Entry<Float, Order> currentSell = sellIter.next();
                while (currentBuy.getKey() >= currentSell.getKey()) {
                    int newVolume = currentBuy.getValue().getVolume() - currentSell.getValue().getVolume();
                    if (newVolume > 0) {
                        currentBuy.getValue().setVolume(newVolume);
                        sellIter.remove();
                        if (sellIter.hasNext()) {
                            currentSell = sellIter.next();
                        } else {
                            break;
                        }
                    } else if (newVolume < 0) {
                        currentSell.getValue().setVolume(-newVolume);
                        buyIter.remove();
                        if (buyIter.hasNext()) {
                            currentBuy = buyIter.next();

                        } else {
                            break;
                        }
                    } else if (newVolume == 0) {
                        buyIter.remove();
                        sellIter.remove();

                        if (buyIter.hasNext() && sellIter.hasNext()) {
                            currentBuy = buyIter.next();
                            currentSell = sellIter.next();
                        } else if (!buyIter.hasNext() || !sellIter.hasNext()) {
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Printing books.
     */
    void printBooks() {
        for (OrderBook book : orderBooksList) {
            Iterator<Map.Entry<Float, Order>> buyIter = book.buyOrders.entrySet().iterator();
            Iterator<Map.Entry<Float, Order>> sellIter = book.sellOrders.entrySet().iterator();
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Order book: %s", book.name));
            sb.append(sep);
            sb.append("BID              ASK");
            sb.append(sep);
            sb.append("Volume@Price - Volume@Price");
            sb.append(sep);

            while (buyIter.hasNext() && sellIter.hasNext()) {
                Map.Entry<Float, Order> currentBuy = buyIter.next();
                Map.Entry<Float, Order> currentSell = sellIter.next();

                sb.append(String.format("%,8d", currentBuy.getValue().getVolume()));
                sb.append("@");
                sb.append(String.format("%5.1f", currentBuy.getKey()));
                sb.append(" - ");
                sb.append(String.format("%,8d", currentSell.getValue().getVolume()));
                sb.append("@");
                sb.append(String.format("%5.1f", currentSell.getKey()));
                sb.append(sep);

            }
            if (buyIter.hasNext()) {
                Map.Entry<Float, Order> currentBuy = buyIter.next();
                while (buyIter.hasNext()) {
                    sb.append(String.format("%,8d", currentBuy.getValue().getVolume()));
                    sb.append("@");
                    sb.append(String.format("%5.1f", currentBuy.getKey()));
                    sb.append(" - ");
                    sb.append("----");
                    sb.append(sep);
                    currentBuy = buyIter.next();
                }
            } else if (sellIter.hasNext()) {
                Map.Entry<Float, Order> currentSell = sellIter.next();
                while (sellIter.hasNext()) {
                    sb.append("----");
                    sb.append(String.format("%,8d", currentSell.getValue().getVolume()));
                    sb.append("@");
                    sb.append(String.format("%5.1f", currentSell.getKey()));
                    sb.append(sep);
                    currentSell = sellIter.next();
                }
            }
            System.out.println(sb);
        }

    }

}


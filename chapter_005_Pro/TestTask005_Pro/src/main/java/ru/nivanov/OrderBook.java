package ru.nivanov;

import java.util.*;

/**
 * Created by Nikolay Ivanov on 26.05.2017.
 */
class OrderBook {
    private String name;
    private TreeMap<Float, Order> buyOrders;
    private TreeMap<Float, Order> sellOrders;
    private String sep = System.getProperty("line.separator");
    private Set<OrderBook> orderBooksSet;

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
     * Filling books with sell and buy orders.
     */
    void fillBooksNew(HashMap<Integer, Order> unsort, Set<String> bookNamesSet) {
        bookSetupNew(bookNamesSet);
        Collection<Order> ordersList = unsort.values();
        Iterator<Order> iter = ordersList.iterator();
        while (iter.hasNext()) {
            Order current = iter.next();
            for (OrderBook currentBook : orderBooksSet) {
                if (current.getBook().equals(currentBook.name)) {
                    if (current.getOrderType().equals("BUY")) {

                        if (currentBook.buyOrders.containsKey(current.getPrice())) {
                            int newVolume = current.getVolume() + currentBook.buyOrders.get(
                                    current.getPrice()).getVolume();

                            current.setVolume(newVolume);
                            currentBook.buyOrders.put(current.getPrice(), current);
                            break;
                        } else {
                            currentBook.buyOrders.put(current.getPrice(), current);
                            break;
                        }
                    } else {

                        if (currentBook.sellOrders.containsKey(current.getPrice())) {
                            int newVolume = current.getVolume() + currentBook.sellOrders.get(
                                    current.getPrice()).getVolume();
                            current.setVolume(newVolume);
                            currentBook.sellOrders.put(current.getPrice(), current);
                            break;
                        } else {
                            currentBook.sellOrders.put(current.getPrice(), current);
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
    private void bookSetupNew(Set<String> bookSet) {
        orderBooksSet = new TreeSet<>(new Comparator<OrderBook>() {
            @Override
            public int compare(OrderBook o1, OrderBook o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        for (String value : bookSet) {
            orderBooksSet.add(newOrderBook(value));
        }
    }

    /**
     * Executing matching logic.
     */
    void bookExecuteNew() {

        for (OrderBook book : orderBooksSet) {

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
    void printBooksNew() {
        for (OrderBook book : orderBooksSet) {
            Iterator<Map.Entry<Float, Order>> buyIter = book.buyOrders.entrySet().iterator();
            Iterator<Map.Entry<Float, Order>> sellIter = book.sellOrders.entrySet().iterator();
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Order book: %s", book.name));
            sb.append(sep);
            sb.append("BID              ASK");
            sb.append(sep);
            sb.append("Volume@Price  -  Volume@Price");
            sb.append(sep);

            while (buyIter.hasNext() && sellIter.hasNext()) {
                Map.Entry<Float, Order> currentBuy = buyIter.next();
                Map.Entry<Float, Order> currentSell = sellIter.next();

                sb.append(String.format("%,9d", currentBuy.getValue().getVolume()));
                sb.append("@");
                sb.append(String.format("%5.1f", currentBuy.getKey()));
                sb.append(" - ");
                sb.append(String.format("%,9d", currentSell.getValue().getVolume()));
                sb.append("@");
                sb.append(String.format("%5.1f", currentSell.getKey()));
                sb.append(sep);

            }
            if (buyIter.hasNext()) {

                while (buyIter.hasNext()) {
                    Map.Entry<Float, Order> currentBuy = buyIter.next();
                    sb.append(String.format("%,9d", currentBuy.getValue().getVolume()));
                    sb.append("@");
                    sb.append(String.format("%5.1f", currentBuy.getKey()));
                    sb.append(" - ");
                    sb.append(String.format("%13s", "------"));
                    sb.append(sep);

                }
            } else if (sellIter.hasNext()) {
                while (sellIter.hasNext()) {
                    Map.Entry<Float, Order> currentSell = sellIter.next();
                    sb.append(String.format("%18s", "------  "));
                    sb.append(String.format("%,9d", currentSell.getValue().getVolume()));
                    sb.append("@");
                    sb.append(String.format("%5.1f", currentSell.getKey()));
                    sb.append(sep);
                }
            }
            System.out.println(sb);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderBook orderBook = (OrderBook) o;

        return name.equals(orderBook.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}


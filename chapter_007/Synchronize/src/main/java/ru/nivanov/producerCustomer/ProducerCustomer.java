package ru.nivanov.producerCustomer;

/**
 * Created by Nikolay Ivanov on 12.07.2017.
 */
public class ProducerCustomer {

    private static final int TASKS_NUMBER = 10;

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {

        TaskOrder<String> taskOrder = new TaskOrder<>();
        Producer<String> producer = new Producer<>(taskOrder, "producer", TASKS_NUMBER);
        Customer<String> customer = new Customer<>(taskOrder, "consumer", TASKS_NUMBER);

        Thread one = new Thread(producer);
        Thread two = new Thread(customer);

        two.start();
        one.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

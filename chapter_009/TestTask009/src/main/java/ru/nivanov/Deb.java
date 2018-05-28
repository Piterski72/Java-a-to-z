package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 13.05.2018.
 */
public class Deb {
    public static void main(String[] args) {
        String raw = "rap ,   rock and roll  ,  metall";

        String res2 = raw.replaceAll("[\\s]*,[\\s]*", ",");
        String[] items = res2.split(",");

        System.out.println(res2);
        for (String x : items) {
            System.out.println(x);
        }

    }
}

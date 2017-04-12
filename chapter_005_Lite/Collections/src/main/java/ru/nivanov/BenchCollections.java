package ru.nivanov;

import com.google.common.base.Joiner;

import java.util.*;

/**
 * Created by Nikolay Ivanov on 11.04.2017.
 */
public class BenchCollections {
    private static final String FOR_TEST = "forTestingCollections";
    private static final int ITERATIONS_ADD = 100000;
    private static final int ITERATIONS_DEL = 25000;
    private static final Collection<String> ONE_LINKED = new LinkedList<>();
    private static final Collection<String> TWO_ARRAY = new ArrayList<>();
    private static final Collection<String> TREE_SET = new TreeSet<>();

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {
        BenchCollections bench = new BenchCollections();
        long resultAddOne = bench.add(ONE_LINKED, FOR_TEST, ITERATIONS_ADD);
        long resultAddTwo = bench.add(TWO_ARRAY, FOR_TEST, ITERATIONS_ADD);
        long resultAddThree = bench.add(TREE_SET, FOR_TEST, ITERATIONS_ADD);

        long resultRemoveOne = bench.remove(ONE_LINKED, ITERATIONS_DEL);
        long resultRemoveTwo = bench.remove(TWO_ARRAY, ITERATIONS_DEL);
        long resultRemoveThree = bench.remove(TREE_SET, ITERATIONS_DEL);

        System.out.println(Joiner.on(System.getProperty("line.separator")).join(
                String.format("LinkedList results, add: %d remove: %d", resultAddOne, resultRemoveOne),
                String.format("ArrayList results, add: %d remove: %d", resultAddTwo, resultRemoveTwo),
                String.format("TreeSet results, add: %d remove: %d", resultAddThree, resultRemoveThree)));
    }

    /**
     * Benchmark for adding elements in collection.
     * @param collection ..
     * @param line ..
     * @param amount ..
     * @return time in milis.
     */
    long add(Collection<String> collection, String line, int amount) {
        String[] testStrings = stringGenerator(line, amount);
        long start = System.currentTimeMillis();
        collection.addAll(Arrays.asList(testStrings).subList(0, amount));
        long result = System.currentTimeMillis();
        return (result - start);
    }

    /**
     * Benchmark for removing "count" elements in collection.
     * @param collection ..
     * @param count ..
     * @return time in milis.
     */
    long remove(Collection<String> collection, int count) {
        Iterator<String> iterator = collection.iterator();
        int step = 0;
        long start = System.currentTimeMillis();
        while (iterator.hasNext() && step < count) {
            iterator.next();
            iterator.remove();
            step++;
        }
        long result = System.currentTimeMillis();
        return (result - start);
    }

    /**
     * Sting generator.
     * @param line ..
     * @param count ..
     * @return string massive.
     */
    String[] stringGenerator(String line, int count) {
        ArrayList<String> rndArray = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            rndArray.add(String.valueOf(line.charAt(i)));
        }
        String[] result = new String[count];
        for (int i = 0; i < count; i++) {
            Collections.shuffle(rndArray);
            result[i] = rndArray.toString();
        }
        return result;
    }

}

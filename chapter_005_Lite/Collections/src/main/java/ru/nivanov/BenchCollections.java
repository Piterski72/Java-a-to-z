package ru.nivanov;

import com.google.common.base.Joiner;

import java.util.*;

/**
 * Created by Nikolay Ivanov on 11.04.2017.
 */
public class BenchCollections {
    private static final String FOR_TEST = "forTestingCollections";
    private static final int ITERATIONS_ADD = 20000;
    private static final int ITERATIONS_DEL = 6000;
    private static Collection<String> oneLinked = new LinkedList<>();
    private static Collection<String> twoArray = new ArrayList<>();
    private static Collection<String> threeTree = new TreeSet<>();

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {
        BenchCollections bench = new BenchCollections();
        long resultAddOne = bench.add(oneLinked, FOR_TEST, ITERATIONS_ADD);
        long resultAddTwo = bench.add(twoArray, FOR_TEST, ITERATIONS_ADD);
        long resultAddThree = bench.add(threeTree, FOR_TEST, ITERATIONS_ADD);

        long resultRemoveOne = bench.delete(oneLinked, ITERATIONS_DEL);
        long resultRemoveTwo = bench.delete(twoArray, ITERATIONS_DEL);
        long resultRemoveThree = bench.delete(threeTree, ITERATIONS_DEL);

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
    long delete(Collection<String> collection, int count) {
        String[] toArray = collection.toArray(new String[collection.size()]);
        String[] foRemove = Arrays.copyOf(toArray, count);
        long start = System.currentTimeMillis();
        collection.removeAll(Arrays.asList(foRemove));
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

package ru.nivanov.subUnitReference;

import java.io.File;

/**
 * Created by Nikolay Ivanov on 07.06.2017.
 */
public class StartSorting {
    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {

        File input = new File("c:\\java\\subUnits.txt");

        SubUnitTree underTest = new SubUnitTree();
        Node.setSortOrder(1);
        underTest.exec(input);
        System.out.println("direct order: ");
        underTest.printTree(underTest.getRoot());

        System.out.println();

        SubUnitTree underTest2 = new SubUnitTree();
        Node.setSortOrder(-1);
        underTest2.exec(input);
        System.out.println("reverse order: ");
        underTest2.printTree(underTest2.getRoot());


    }

}

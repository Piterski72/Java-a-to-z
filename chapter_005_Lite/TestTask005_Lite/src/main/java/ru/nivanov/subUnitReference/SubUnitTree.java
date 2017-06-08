package ru.nivanov.subUnitReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Created by Nikolay Ivanov on 06.06.2017.
 * @param <E> ..
 */
class SubUnitTree<E> {
    private Node<E> root;

    /**
     * Constructor.
     */
    SubUnitTree() {
        this.root = new Node<>("", null);
    }

    /**
     * root getter.
     * @return ..
     */
    Node<E> getRoot() {
        return root;
    }

    /**
     * Processing file with sub unit id`s.
     * @param input ..
     */
    void exec(File input) {

        try (FileReader fileReader = new FileReader(input);
             BufferedReader br = new BufferedReader(fileReader)) {

            String lexem;

            while ((lexem = br.readLine()) != null) {

                Node<E> currentRoot = this.root;

                if (lexem.contains("\\")) {
                    StringTokenizer stkn = new StringTokenizer(lexem, "\\");
                    while (stkn.hasMoreTokens()) {
                        String currentId = stkn.nextToken();
                        if (!currentRoot.getChildren().contains(new Node<>(currentId, currentRoot))) {
                            currentRoot.getChildren().add(new Node<>(currentId, currentRoot));
                            currentRoot = currentRoot.getChildren().last();
                        } else {
                            for (Node<E> value : currentRoot.getChildren()) {
                                if (value.compareTo(new Node<>(currentId, currentRoot)) == 0) {
                                    currentRoot = value;
                                    break;

                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Printing tree.
     * @param node ..
     */
    void printTree(Node<E> node) {

        for (Node<E> value : node.getChildren()) {

            String forPrint = value.getSubUnitId();
            if (value.getParent() != root) {

                Node<E> currentParent = value.getParent();
                LinkedList<String> list = new LinkedList<>();
                StringBuilder stringBuilder = new StringBuilder();

                while (currentParent != root) {
                    String id = currentParent.getSubUnitId();
                    list.add(id);
                    currentParent = currentParent.getParent();
                }
                while (list.size() != 0) {
                    stringBuilder.append(list.removeLast());
                    stringBuilder.append("\\");
                }
                stringBuilder.append(forPrint);

                System.out.println(stringBuilder);
            } else {
                System.out.println(forPrint);
            }
            if (value.getChildren() != null) {
                printTree(value);
            }

        }

    }

}

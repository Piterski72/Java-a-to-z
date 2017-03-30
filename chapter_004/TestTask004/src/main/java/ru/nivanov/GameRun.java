package ru.nivanov;

import ru.nivanov.Input.ConsoleInput;
import ru.nivanov.Input.Input;
import ru.nivanov.Output.ConsoleOutput;
import ru.nivanov.Output.Output;

/**
 * Created by Nikolay Ivanov on 28.03.2017.
 */
public class GameRun {
    /**
     * Main method.
     * @param args String[] args
     */
    public static void main(String[] args) {
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        out.write("Start the game...");
        out.write("This is simple tic-tac-toe game.");

        SetUpGame setNew = new SetUpGame(in, out);

        int size = setNew.getGameFieldSize();

        Cell[][] cells = new Cell[size][size];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].setCellContent("");
            }
        }
        GameBoard board = new GameBoard(cells);

        Player human1 = new PlayerHuman(in, out, board, "human1");
        //Player human2 = new PlayerHuman(in, out, board, "human2");

        Player comp = new PlayerComputer(out, board, "comp");

        Game ticTacToe = new Game(board, human1, comp, out);

        boolean userStart = setNew.getUserStartTheGame();
        ticTacToe.init(userStart);
    }

}

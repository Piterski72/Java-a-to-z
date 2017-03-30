package ru.nivanov;

import ru.nivanov.Output.Output;

/**
 * Created by Nikolay Ivanov on 27.03.2017.
 */
public class PlayerComputer implements Player {

    private GameBoard field;
    private Output out;
    private String computerMark;
    private String name;

    /**
     * Creates new Contender instance.
     * @param field GameArea to play on.
     */
    public PlayerComputer(Output out, GameBoard field, String name) {
        this.field = field;
        this.out = out;
        this.name = name;
    }

    /**
     * Returns new Point to make move.
     * @return Point to make move to.
     */
    private Cell getCellForMove() {
        for (int i = 0; i < this.field.getSize(); i++) {
            for (int j = 0; j < this.field.getSize(); j++) {
                if (this.field.verifyEmpty(i, j)) {
                    return this.field.getCell(i, j);
                }
            }
        }
        throw new RuntimeException("Now free cells left");
    }

    /**
     * Tries to mark cell(x, y) with Sign.
     * @param cell is cell to verify.
     * @param mark String to use into as a sign.
     */
    private void makeMove(Cell cell, String mark) {
        int x = cell.getX();
        int y = cell.getY();
        if (x >= this.field.getSize() || y >= this.field.getSize() || x < 0 || y < 0) {
            throw new CoordinatesException("Invalid move: out of field");
        }
        if (this.field.verifyEmpty(x, y)) {
            this.field.setCell(x, y, mark);
        } else {
            throw new CoordinatesException("Invalid move: occupied cell");
        }


    }

    /**
     * Computer move.
     * @return cell ..
     */
    public Cell playerMove() {
        Cell move = getCellForMove();
        this.out.write(String.format("player %s move is %d, %d", getName(), move.getX(), move.getY()));
        makeMove(move, this.computerMark);
        this.out.write(this.field.toString());
        return move;
    }

    /**
     * Mark setter.
     * @param mark ..
     */
    public void setMark(String mark) {
        this.computerMark = mark;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

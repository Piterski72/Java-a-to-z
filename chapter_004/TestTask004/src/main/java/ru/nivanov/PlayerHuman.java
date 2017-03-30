package ru.nivanov;

import ru.nivanov.Input.Input;
import ru.nivanov.Output.Output;

/**
 * Created by Nikolay Ivanov on 28.03.2017.
 */
public class PlayerHuman implements Player {
    private Input in;
    private Output out;
    private GameBoard field;
    private String playerMark;
    private String name;

    /**
     * Constructor.
     * @param in ..
     * @param out ..
     */
    public PlayerHuman(Input in, Output out, GameBoard field, String name) {
        this.in = in;
        this.out = out;
        this.field = field;
        this.name = name;
    }

    /**
     * Reads X and Y values to make user move.
     * @return cell ..
     */
    public Cell playerMove() {
        boolean valid = false;
        String xCoordinateStr = "";
        String yCoordinateStr = "";
        while (!valid) {
            this.out.write(String.format("player %s, please type the X coordinate: ", getName()));
            xCoordinateStr = in.read();
            this.out.write("Please type the Y coordinate: ");
            yCoordinateStr = in.read();
            try {
                if (Integer.parseInt(xCoordinateStr) >= this.field.getSize() || Integer.parseInt(
                        yCoordinateStr) >= this.field.getSize()) {
                    valid = false;
                    throw new CoordinatesException("invalid coordinates out of board size");
                }
                this.makeMove(this.field.getCell(Integer.parseInt(xCoordinateStr), Integer.parseInt(yCoordinateStr)),
                        this.playerMark);
                valid = true;
            } catch (NumberFormatException nfe) {
                this.out.write(
                        xCoordinateStr + ":" + yCoordinateStr + " coordinates are invalid. should be positive numbers less then field size.");
            } catch (CoordinatesException ce) {
                ce.printStackTrace();
            }
        }
        this.out.write(String.format("player %s move is %s, %s", getName(), xCoordinateStr, yCoordinateStr));
        this.out.write(this.field.toString());
        return this.field.getCell(Integer.parseInt(xCoordinateStr), Integer.parseInt(yCoordinateStr));
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

    public void setMark(String mark) {
        this.playerMark = mark;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

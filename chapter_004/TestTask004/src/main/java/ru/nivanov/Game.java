package ru.nivanov;

import ru.nivanov.output.Output;

/**
 * Created by Nikolay Ivanov on 27.03.2017.
 */
class Game {
    private GameBoard field;
    private Player playerOne;
    private Player playerTwo;
    private Output out;

    /**
     * Constructor.
     * @param board ..
     * @param playerOne ..
     * @param playerTwo ..
     * @param out ..
     */
    Game(GameBoard board, Player playerOne, Player playerTwo, Output out) {
        this.field = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.out = out;

    }

    /**
     * Starts the game.
     * @param userStart checks who is moving first.
     */
    void init(boolean userStart) {
        if (userStart) {
            this.playerTwo.setMark("X");
            this.playerOne.setMark("O");
            this.playerTwo.playerMove();
        } else {
            this.playerTwo.setMark("O");
            this.playerOne.setMark("X");
        }
        while (!this.field.isFull()) {
            Cell playerOneChoice = this.playerOne.playerMove();
            if (this.field.isWinner(playerOneChoice)) {
                this.out.write(String.format("player %s is winner", playerOne.getName()));
                break;
            }
            Cell playerTwoChoice = this.playerTwo.playerMove();
            if (this.field.isWinner(playerTwoChoice)) {
                this.out.write(String.format("player %s is winner", playerTwo.getName()));
                break;
            }
        }
    }


}

package ru.nivanov;

import ru.nivanov.input.Input;
import ru.nivanov.output.Output;

/**
 * Created by Nikolay Ivanov on 28.03.2017.
 */
class SetUpGame {
    private final int defaultBoardSize = 3;
    private Input in;
    private Output out;

    /**
     * Constructor.
     * @param in ..
     * @param out ..
     */
    SetUpGame(Input in, Output out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Returns GameArea size.
     * @return GameArea object based om user input.
     * @throws NumberFormatException ..
     * @throws BoardSizeException ..
     */
    int getGameFieldSize() throws NumberFormatException, BoardSizeException {
        boolean validSize = false;
        int size = 0;
        this.out.write("Please type field size (leave blanc and enter for 3 by default): ");
        while (!validSize) {
            String fieldSize = this.in.read().trim();
            if (!fieldSize.trim().isEmpty()) {
                try {
                    size = Integer.parseInt(fieldSize);
                    if (size < defaultBoardSize) {
                        throw new BoardSizeException("invalid board size. Should be an odd number greater or equal 3");
                    } else if (size >= defaultBoardSize && size % 2 == 1) {
                        validSize = true;
                    } else {
                        validSize = false;
                        throw new BoardSizeException("should be an odd number");
                    }
                } catch (NumberFormatException | BoardSizeException nfe) {
                    nfe.printStackTrace();
                }
            } else {
                size = defaultBoardSize;
                validSize = true;
            }
        }
        return size;
    }

    /**
     * Returns true if user should make first move.
     * @return true is user should make first move.
     */
    boolean getUserStartTheGame() {
        this.out.write(
                "Who will start the game? (Leave field blank and press 'Enter' to start game by player2. Type something and press 'Enter' to player1 start the game): ");
        return this.in.read().trim().isEmpty();
    }
}

/**
 * Solution code for Comp24011 Reversi lab
 *
 * @author USERNAME
 */

public class MoveChooserAlphaBeta extends MoveChooser {
    /**
     * MoveChooser implementation MoveChooserAlphaBeta(int)
     *
     * @param   searchLimit The requested depth for minimax search
     */
    public MoveChooserAlphaBeta(int searchLimit) {
        // Add object initialisation code...
        super("AlphaBeta",searchLimit);
    }

    /**
     * Need to implement chooseMove(BoardState,Move)
     *
     * @param   boardState  The current state of the game board
     *
     * @param   hint        Skip move or board location clicked on the UI
     *                      This parameter should be ignored!
     *
     * @return  The move chosen by alpha-beta pruning as discussed in the course
     */
    public Move chooseMove(BoardState boardState, Move hint) {
        // Add alpha-beta pruning code...
        Move ans= null;
        return ans;
    }

    /**
     * Need to implement boardEval(BoardState)
     *
     * @param   boardState  The current state of the game board
     *
     * @return  The value of the board using Norvig's weighting of squares
     */
    public int boardEval(BoardState boardState) {
        // Add board evaluation code...
        int ans= -1;
        return ans;
    }
}

/* vim:set et ts=4 sw=4: */

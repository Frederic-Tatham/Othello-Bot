/**
 * Solution code for Comp24011 Reversi lab
 *
 * @author USERNAME
 */

import java.util.ArrayList;

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

    record ScoreMove(double actionScore, Move bestMove) {}

    int [][] SPACESCORE = 
            {{120, -20, 20, 5, 5, 20, -20, 120},
            { -20, -40, -5, -5, -5, -5, -40, -20},
            {20, -5, 15, 3, 3, 15, -5, 20},
            {5, -5, 3, 3, 3, 3, -5, 5},
            {5, -5, 3, 3, 3, 3, -5, 5},
            {20, -5, 15, 3, 3, 15, -5, 20},
            {-20, -40, -5, -5, -5, -5, -40, -20},
            {120, -20, 20, 5, 5, 20, -20, 120}}; 

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
        int depth = 0;
        ScoreMove result;
        if (boardState.colour == 1) {
            result = maxChooseMove(boardState, -9999, 9999, null, 0);
        } else {
            result = minChooseMove(boardState, -9999, 9999, null, 0);
        }

        return result.bestMove();
    }

    public ScoreMove maxChooseMove(BoardState boardState, double a, double b, Move lastAction, int depth) {
       
        if (boardState.gameOver()) {
            int[] totals= boardState.getTotals();
            int white= totals[0];
            int black= totals[1];
            if (white > black)
                return new ScoreMove(1, lastAction);
            else if (white < black)
                return new ScoreMove(-1, lastAction);
            else
                return new ScoreMove(0, lastAction);
        }

        if (depth >= searchLimit) {
            double utility = boardEval(boardState);
            utility = utility / 1176.0;
            return new ScoreMove(utility, lastAction);
        }
        
        double actionScore = -9999;
        Move bestMove = null;
        ArrayList<Move> moves = boardState.getLegalMoves();
        for (Move action : moves) {
            BoardState boardCopy = boardState.deepCopy();
            boardCopy.makeLegalMove(action);
            ScoreMove result = minChooseMove(boardCopy, a, b, action, depth + 1);
            double actionScore2 = result.actionScore();

            if (actionScore2 > actionScore) {
                actionScore = actionScore2;
                bestMove = action;
                a = Math.max(a, actionScore);
            }
            if (actionScore >= b) {
                return new ScoreMove(actionScore, bestMove); 
            }
        }

        return new ScoreMove(actionScore, bestMove);
    }

    public ScoreMove minChooseMove(BoardState boardState, double a, double b, Move lastAction, int depth) {
        if (boardState.gameOver()) {
            int[] totals= boardState.getTotals();
            int white= totals[0];
            int black= totals[1];
            if (white > black)
                return new ScoreMove(1, lastAction);
            else if (white < black)
                return new ScoreMove(-1, lastAction);
            else
                return new ScoreMove(0, lastAction);
        }

        if (depth >= searchLimit) {
            double utility = boardEval(boardState);
            utility = utility / 1176.0;
            return new ScoreMove(utility, lastAction);
        }

        double actionScore = 9999;
        Move bestMove = null;
        ArrayList<Move> moves = boardState.getLegalMoves();
        for (Move action : moves) {
            BoardState boardCopy = boardState.deepCopy();
            boardCopy.makeLegalMove(action);
            ScoreMove result = maxChooseMove(boardCopy, a, b, action, depth + 1);
            double actionScore2 = result.actionScore();
        
            if (actionScore2 < actionScore) {
                actionScore = actionScore2;
                bestMove = action;
                b = Math.min(b, actionScore);
            }
            if (actionScore <= a) {
                return new ScoreMove(actionScore, bestMove); 
            }
        }

        return new ScoreMove(actionScore, bestMove);
    }

    /**
     * Need to implement boardEval(BoardState)
     *
     * @param   boardState  The current state of the game board
     *
     * @return  The value of the board using Norvig's weighting of squares
     */
    public int boardEval(BoardState boardState) {
        int score = 0;
        //double normDenominator = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                score += SPACESCORE[i][j] * boardState.getContents(i, j);
                //normDenominator += Math.abs(SPACESCORE[i][j]);
            }
        }        
        //normalise result
        //score = score / normDenominator;
        return score;
    }
}

/* vim:set et ts=4 sw=4: */

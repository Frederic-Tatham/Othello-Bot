/**
 * Java code for Comp24011 Reversi lab
 *
 * @author Mbassip2
 * @author a21674fl
 *
 * Copyright 2023; please do not distribute!
 */

import java.util.ArrayList;
import java.util.Random;

public class MoveChooserMonteCarlo extends MoveChooser {
    private Random rand;

    // Record requested time limit
    public MoveChooserMonteCarlo(int mctsSeconds) {
        super("MonteCarlo", mctsSeconds);
        rand= new Random();
    }

    // Choose the best move using MCTS. Assume boardState is not final
    public Move chooseMove(BoardState boardState, Move hint) {
        // Create the MCTS tree, starting with a single root
        BoardTreeVertex root= new BoardTreeSolution(boardState, null, null);
        // Change these lines to limit number of iterations rather than time.
        long timeEnd= System.currentTimeMillis() + searchLimit*1000;
        while (System.currentTimeMillis() < timeEnd) {
            BoardTreeVertex exploreNode;
            int result;
            // Navigate tree until a node is reached that needs exploring
            exploreNode= chooseNodeToExplore(root);
            // Do a rollout from that node and get the result (-1, 0 or 1)
            result= rollout(exploreNode.currentBoardState);
            // Backpropagate the win/played statistics up to root of tree
            exploreNode.backpropagate(result);
        }
        // Having created the MCTS tree, Get list of best moves.
        // This means the move which gives the corresponding child
        // (under control of the oponent) the smallest proportion of wins.
        ArrayList<Move> bestMoves= new ArrayList<Move>();
        double bestVal= Double.MAX_VALUE;
        for (BoardTreeVertex child: root.children) {
            // Get value of current child Wins/Games ratio.
            double val= ((double) child.wins)/child.games;
            if (val < bestVal)
                bestMoves.clear();
            if (val <= bestVal) {
                bestMoves.add(child.moveFromParent);
                bestVal= val;
            }
        }
        // select randomly from the best moves
        int i= rand.nextInt(bestMoves.size());
        return bestMoves.get(i);
    }

    // Choose node to explore in tree staring with vtx.
    // Returned vertex may represent a game-over state.
    public static BoardTreeVertex chooseNodeToExplore(BoardTreeVertex vtx) {
        BoardTreeVertex currentVtx= vtx;
        while (!currentVtx.currentBoardState.gameOver()) { // Until you reach end of the game
            if (currentVtx.unconsideredMoves.isEmpty()) {  // All children have been added to tree
                currentVtx= currentVtx.getBestChild();     // Navigate tree according to MCTS
            } else {
                Move move;
                BoardState newBoardState;
                BoardTreeVertex newVtx;
                // Not all children added to tree. Construct next child
                move= currentVtx.unconsideredMoves.get(0);
                newBoardState= currentVtx.currentBoardState.deepCopy();
                newBoardState.makeLegalMove(move);
                newVtx= new BoardTreeSolution(newBoardState, currentVtx, move);
                // Modify tree to record new child
                currentVtx.children.add(newVtx);
                currentVtx.unconsideredMoves.remove(move);
                // Break out of while; return new child
                return newVtx;
            }
        }
        // Got to game-over state
        return currentVtx;
    }

    public int rollout(BoardState boardState){
        ArrayList<Move> moves= null;
        int r= 0;
        // Make a copy of the current board state, so that it is not changed.
        BoardState currentBoardState= boardState.deepCopy();
        while (!currentBoardState.gameOver()) {
            // Get legal moves: must be non-empty
            moves= currentBoardState.getLegalMoves();
            // Update board-state, choosing a move at random
            r = rand.nextInt(moves.size());
            currentBoardState.makeLegalMove(moves.get(r));
        }
        return Integer.signum(currentBoardState.getScore());
   }

    // Dummy implementation of board evaluation
    public int boardEval(BoardState boardState) {
        return -1;
    }
}

/* vim:set et ts=4 sw=4: */

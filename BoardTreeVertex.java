/**
 * Java code for Comp24011 Reversi lab
 *
 * @author Mbassip2
 * @author a21674fl
 *
 * Copyright 2025; please do not distribute!
 */

import java.util.ArrayList;
import java.util.Random;

public abstract class BoardTreeVertex {
    // The exploration term is idependent of colour
    public static final double explorationConstant= Math.sqrt(2);

    private Random rand;
    BoardTreeVertex parent;
    Move moveFromParent;  // Null if root
    BoardState currentBoardState;
    ArrayList<BoardTreeVertex> children; // Children already created
    ArrayList<Move> unconsideredMoves; // Moves not yet explored
    int wins;
    int games;

    public BoardTreeVertex(BoardState boardState, BoardTreeVertex prnt, Move moveFromPrnt) {
        rand= new Random();
        parent= prnt;
        moveFromParent= moveFromPrnt;
        currentBoardState= boardState;
        children= new ArrayList<BoardTreeVertex>();
        unconsideredMoves= boardState.getLegalMoves();  // Initially, no moves considered
        wins= 0;
        games= 0;
    }

    public BoardTreeVertex getBestChild() {
        // Should never happen. Should only be called when all children created.
        if (children.isEmpty())
            throw new IllegalArgumentException("getBestChild called with empty list");
        // Get list of best children,
        // i.e. those whose win rate is worst, since these are oponents
        ArrayList<BoardTreeVertex> bestChildren= new ArrayList<BoardTreeVertex>();
        double bestVal= Double.MAX_VALUE;
        for (BoardTreeVertex child: children) {
            double val = vertexEval(child);
            if (val < bestVal)
                bestChildren.clear();
            if (val <= bestVal) {
                bestChildren.add(child);
                bestVal= val;
            }
        }
        //select randomly from the best children
        int i= rand.nextInt(bestChildren.size());
        return bestChildren.get(i);
    }

    /**
     * Concrete classes need to implement vertexEval(BoardTreeVertex)
     *
     * @param   childVertex  The vertex of current child
     *
     * @return  The MCTS value of current child
     */
    abstract public double vertexEval(BoardTreeVertex childVertex);

    // Propagate the result reslt up to the root
    public void backpropagate(int reslt) {
        // Increment wins if reslt is same as colour
        wins+= Math.max(0,currentBoardState.colour * reslt);
        // Increment number of games played
        games++;
        // Recurse to top of tree
        if (parent != null)
            parent.backpropagate(reslt);
    }
}

/* vim:set et ts=4 sw=4: */
